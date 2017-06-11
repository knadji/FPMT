package fr.cnamts.fpmt.model;

public class IndicateurPs {

    /**
     * IndicateurPs est une repr�sentation de l'entit� 110 cf.SFD.
     */

    /**
     * Le type de l'enregistrement en-t�te
     */
    private final String typeEnregistrement;

    /**
     * Le num�ro prodessionnel de sant�
     */
    private final String numPs;

    /**
     * Date pour laquelle le taux a �t� calcul�
     */
    private final String dateIndicateur;

    /**
     * Date � laquelle le d�nombrement a �t� calcul�
     */
    private final String dateCalcul;

    /**
     * Contient le d�nombrement calcul�
     */
    private final String numerateur;

    /**
     * Denominateur
     */
    private final String denominateur;

    /**
     * Date d�but observation du taux
     */
    private final String dateDebutObservationTaux;

    /**
     * Date fin observation du taux
     */
    private final String dateFinObservationTaux;

    /**
     * Le nombre de ligne 110 indiqu� dans l'entit� 990
     */
    private final int cptNbLigne110;

    public IndicateurPs(final String typeEnregistrement, final String numPs, final String dateIndicateur,
            final String dateCalcul, final String numerateur, final String denominateur,
            final String dateDebutObservationTaux, final String dateFinObservationTaux,
            final int cptNbLigne110) {
        this.typeEnregistrement = typeEnregistrement;
        this.numPs = numPs;
        this.dateIndicateur = dateIndicateur;
        this.dateCalcul = dateCalcul;
        this.numerateur = numerateur;
        this.denominateur = denominateur;
        this.dateDebutObservationTaux = dateDebutObservationTaux;
        this.dateFinObservationTaux = dateFinObservationTaux;
        this.cptNbLigne110 = cptNbLigne110;
    }

    public String getTypeEnregistrement() {
        return typeEnregistrement;
    }

    public String getNumPs() {
        return numPs;
    }

    public String getDateIndicateur() {
        return dateIndicateur;
    }

    public String getDateCalcul() {
        return dateCalcul;
    }

    public String getNumerateur() {
        return numerateur;
    }

    public String getDenominateur() {
        return denominateur;
    }

    public String getDateDebutObservationTaux() {
        return dateDebutObservationTaux;
    }

    public String getDateFinObservationTaux() {
        return dateFinObservationTaux;
    }

    public int getCptNbLigne110() {
        return cptNbLigne110;
    }
}
