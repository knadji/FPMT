package fr.cnamts.fpmt.batch.processor;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.cnamts.fpmt.batch.exception.EnteteInvalidException;
import fr.cnamts.fpmt.model.LigneCSPSEligibiliteBO;
import fr.cnamts.fpmt.service.FiltragePsCsService;

@Component
@StepScope
public class PsProcessor implements ItemProcessor<LigneCSPSEligibiliteBO, LigneCSPSEligibiliteBO> {

    /**
     * La date de référence passe dans le script shell
     */
    private Date dateDeReference;

    /**
     * Le mode de traitement du batch (Calcule ou Paiement)
     */
    private String modeDeTraitement;

    /**
     * Le nom de la liste qui contient les numPS en mémoire
     */
    private final String listePsKey;

    private JobExecution jobExecution;

    @Autowired
    public PsProcessor(final String listePsKey) {
        this.listePsKey = listePsKey;
    }

    @Override
    public LigneCSPSEligibiliteBO process(final LigneCSPSEligibiliteBO ligneCSPSEligibiliteBO)
            throws EnteteInvalidException, ParseException {

        // Récupération de la liste des indicateurs
        Set<String> listePsIndicateur = (Set<String>) jobExecution.getExecutionContext().get(listePsKey);

        // Filtrage des PS/CS
        FiltragePsCsService filtragePsCsService = new FiltragePsCsService(ligneCSPSEligibiliteBO, listePsIndicateur,
                dateDeReference, modeDeTraitement);

        return filtragePsCsService.traitementPsCs();
    }

    /**
     * Récupération du job execution
     *
     * @param stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        jobExecution = stepExecution.getJobExecution();
        modeDeTraitement = jobExecution.getJobParameters().getString("modeDeTraitement");
        dateDeReference = jobExecution.getJobParameters().getDate("dateDeReference");
    }
}
