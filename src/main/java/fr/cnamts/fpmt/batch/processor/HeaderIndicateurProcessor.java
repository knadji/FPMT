package fr.cnamts.fpmt.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import fr.cnamts.fpmt.batch.exception.EnteteInvalidException;
import fr.cnamts.fpmt.model.HeaderFichierIndicateur;
import fr.cnamts.fpmt.model.TypeEntiteEnum;

@Component
public class HeaderIndicateurProcessor implements ItemProcessor<HeaderFichierIndicateur, HeaderFichierIndicateur> {

    private static final String TYPE_NORME_ECHANGE = "PI";
    private static final int NOMBRE_ESPACE_FILLER_1 = 78;
    private static final int NOMBRE_ESPACE_FILLER_2 = 59;

    /**
     * HeaderIndicateurProcessor vérifier la validité de l'en-tête du fichier indicateur patientèle.
     */

    /**
     * Comparaison du header avec les valeurs de la norme
     *
     * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
     */
    @Override
    public HeaderFichierIndicateur process(final HeaderFichierIndicateur enteteFichierIndicateur)
            throws EnteteInvalidException {

        if (enteteInvalide(enteteFichierIndicateur)) {
            throw new EnteteInvalidException("indicateurs");
        }
        return enteteFichierIndicateur;
    }

    /**
     * Vérifier si l'en-tête du fichier indicateur est valide.
     *
     * @param enteteFichierIndicateur
     * @return true | false
     */
    private boolean enteteInvalide(final HeaderFichierIndicateur enteteFichierIndicateur) {
        return !(TypeEntiteEnum.ENTITE_000.getValeurEntite().equals(enteteFichierIndicateur.getTypeEnregistrement())
                && TYPE_NORME_ECHANGE.equals(enteteFichierIndicateur.getTypeNormeEchange())
                && NOMBRE_ESPACE_FILLER_1 == enteteFichierIndicateur.getLongeurFiller1()
                && NOMBRE_ESPACE_FILLER_2 == enteteFichierIndicateur.getLongeurFiller2());
    }
}
