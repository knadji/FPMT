package fr.cnamts.fpmt.batch.processor;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.cnamts.fpmt.batch.exception.NbLigneInvalidException;
import fr.cnamts.fpmt.batch.exception.TypeEnregistrementInvalideException;
import fr.cnamts.fpmt.model.Entite;
import fr.cnamts.fpmt.model.IndicateurPs;
import fr.cnamts.fpmt.model.TypeEntiteEnum;
import fr.cnamts.fpmt.utils.ValidationPS;

@Component
public class IndicateurProcessor implements ItemProcessor<IndicateurPs, IndicateurPs> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicateurProcessor.class);

    private final Entite entite100;

    private final Entite entite110;

    private final Entite entite990;

    private final Entite entite999;

    private JobExecution jobExecution;

    /**
     * Le nom de la liste qui contient les numPS en mémoire
     */
    private final String listePsKey;

    @Autowired
    public IndicateurProcessor(final String listePsKey, final Entite entite100, final Entite entite110,
            final Entite entite990, final Entite entite999) {
        this.listePsKey = listePsKey;
        this.entite100 = entite100;
        this.entite110 = entite110;
        this.entite990 = entite990;
        this.entite999 = entite999;
    }

    /**
     * Le processor permet de vérifier la validité des entités (suivant les règles des SFD) et de récupérer les numéros
     * de PS de type entité 110 dans une liste sans doublants, la liste se trouve dans le context jobExecution.
     *
     *
     * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
     */
    @Override
    public IndicateurPs process(final IndicateurPs item)
            throws Exception, NbLigneInvalidException, TypeEnregistrementInvalideException {

        if (jobExecution.getExecutionContext().get(listePsKey) == null) {
            jobExecution.getExecutionContext().put(listePsKey, new HashSet<String>());
        }

        String typeEntite = item.getTypeEnregistrement();
        Set<String> listePs = (Set<String>) jobExecution.getExecutionContext().get(listePsKey);

        if (TypeEntiteEnum.ENTITE_100.getValeurEntite().equals(typeEntite)) {
            entite100.incrementNbLigne();

        } else if (TypeEntiteEnum.ENTITE_110.getValeurEntite().equals(typeEntite)) {
            traitementEntite110(item, listePs);

        } else if (TypeEntiteEnum.ENTITE_990.getValeurEntite().equals(typeEntite)) {
            traitementEntite990(item);

        } else if (TypeEntiteEnum.ENTITE_999.getValeurEntite().equals(typeEntite)) {
            traitementEntite999();
        }

        return null;
    }

    /**
     * Application des règles de validité RG_VALIDATION_ENREGIST, RG_CODE_IND_DUPLIQUE et remplissage de la liste des PS
     * sans doublant.
     *
     * @param indicateurPs
     * @param listePs
     */
    private void traitementEntite110(final IndicateurPs indicateurPs, final Set<String> listePs) {

        // RG_VALIDATION_ENREGIST SFD_FPMT
        if (entite100.existePlusieursEnreg()) {
            throw new TypeEnregistrementInvalideException();
        }
        entite110.incrementNbLigne();
        if (ValidationPS.allDatePsValide(indicateurPs) && ValidationPS.numerateurDenominateurValide(indicateurPs)) {
            // RG_CODE_IND_DUPLIQUE
            if (!listePs.add(indicateurPs.getNumPs())) {
                LOGGER.info("--- Le code indicateur {} est dupliqué dans le fichier des indicateurs. ---",
                        indicateurPs.getNumPs());
            }
        }
    }

    /**
     * Application de la règle de validité RG_VALIDATION_ENREGIST, RG_NB_LIGNES
     *
     * @param indicateurPs
     */
    private void traitementEntite990(final IndicateurPs indicateurPs) {
        entite990.incrementNbLigne();
        if (entite990.existePlusieursEnreg()) {
            throw new TypeEnregistrementInvalideException();
        }
        if (ValidationPS.nbLigne110Invalid(indicateurPs, entite110)) {
            throw new NbLigneInvalidException();
        }
    }

    /**
     * Application de la règle de validité RG_VALIDATION_ENREGIST
     */
    private void traitementEntite999() {
        entite999.incrementNbLigne();
        if (entite999.existePlusieursEnreg() || entite990.existePlusieursEnreg()) {
            throw new TypeEnregistrementInvalideException();
        }
    }

    /**
     * Récupération du job execution
     *
     * @param stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        jobExecution = stepExecution.getJobExecution();
    }
}