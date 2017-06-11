package fr.cnamts.fpmt.utils;

import org.joda.time.LocalDate;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.cnamts.fpmt.model.Entite;
import fr.cnamts.fpmt.model.IndicateurPs;

public class ValidationPS {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationPS.class);

    private ValidationPS() {
    }

    /**
     * Vérifier si le nombre d'enregistrement de type 110 est différent au nombre d'enregistrement 110 renseigne dans
     * l'entité 990 CF-RG_NB_LIGNES SFD.
     *
     * @param indicateurPs
     * @param entite110
     * @return
     */
    public static boolean nbLigne110Invalid(final IndicateurPs indicateurPs, final Entite entite110) {
        return entite110.getNombreLigne() != indicateurPs.getCptNbLigne110();
    }

    /**
     * Vérifier la validité du numérateur et du dénominateur d'un indicateur PS, voir RG_TYPE_NUM_DENOM,
     * RG_NUM_DENOM_NEGATIF et RG_TYPE_NUM_DENOM SFD
     * Sachant qu'on a récupéré une valeur sur 6 caractères
     *
     * @param indicateurPs
     * @return true si le numerateur et denominateur respecte le FORMAT_NUM_DENOM
     */
    public static boolean numerateurDenominateurValide(final IndicateurPs indicateurPs) {

        try {
            Integer.valueOf(indicateurPs.getNumerateur());
            Integer.valueOf(indicateurPs.getDenominateur());
        } catch (Exception e) {
            LOGGER.info(
                    "--- Les types du numérateur ou du dénominateurs du PS numéro : {} ne sont pas valides. ---",
                    indicateurPs.getNumPs());
            return false;
        }
        return true;
    }

    /**
     * Vérifier si l'ensemble des dates dans un PS est valide, voir RG_TYPE_DATE SFD.
     *
     * @param item un indicateur PS
     * @return true | false
     */
    public static boolean allDatePsValide(final IndicateurPs indicateurPs) {

        if (dateValide(indicateurPs.getDateIndicateur()) && dateValide(indicateurPs.getDateCalcul())
                && dateValide(indicateurPs.getDateDebutObservationTaux())
                && dateValide(indicateurPs.getDateFinObservationTaux())) {
            return true;
        }
        LOGGER.info("--- Au moins une des dates du PS numéro : {} est non valide. ---", indicateurPs.getNumPs());
        return false;
    }

    /**
     * Vérifier si une date respecte le format YYYYMMDD
     *
     * @param date la date à valider
     * @return true si la date respecte le format
     */
    public static boolean dateValide(final String date) {

        try {
            LocalDate.parse(date, ISODateTimeFormat.basicDate());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
