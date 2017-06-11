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
     * V�rifier si le nombre d'enregistrement de type 110 est diff�rent au nombre d'enregistrement 110 renseigne dans
     * l'entit� 990 CF-RG_NB_LIGNES SFD.
     *
     * @param indicateurPs
     * @param entite110
     * @return
     */
    public static boolean nbLigne110Invalid(final IndicateurPs indicateurPs, final Entite entite110) {
        return entite110.getNombreLigne() != indicateurPs.getCptNbLigne110();
    }

    /**
     * V�rifier la validit� du num�rateur et du d�nominateur d'un indicateur PS, voir RG_TYPE_NUM_DENOM,
     * RG_NUM_DENOM_NEGATIF et RG_TYPE_NUM_DENOM SFD
     * Sachant qu'on a r�cup�r� une valeur sur 6 caract�res
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
                    "--- Les types du num�rateur ou du d�nominateurs du PS num�ro : {} ne sont pas valides. ---",
                    indicateurPs.getNumPs());
            return false;
        }
        return true;
    }

    /**
     * V�rifier si l'ensemble des dates dans un PS est valide, voir RG_TYPE_DATE SFD.
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
        LOGGER.info("--- Au moins une des dates du PS num�ro : {} est non valide. ---", indicateurPs.getNumPs());
        return false;
    }

    /**
     * V�rifier si une date respecte le format YYYYMMDD
     *
     * @param date la date � valider
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
