package fr.cnamts.fpmt.model;

public class Entite {

    /**
     * Entite est une représentation d'une entité (100, 110, 990, 999) du fichier indicateur.
     */

    /**
     * Le nombre de ligne de l'entite dans le fichier indicateur
     */
    private int nombreLigne;

    public int getNombreLigne() {
        return nombreLigne;
    }

    public void setNombreLigne(final int nombreLigne) {
        this.nombreLigne = nombreLigne;
    }

    public void incrementNbLigne() {
        this.nombreLigne++;
    }

    /**
     * Vérifier s'il existe plusieurs enregistrement de l'entité.
     *
     * @return true | false
     */
    public boolean existePlusieursEnreg() {
        return nombreLigne != 1;
    }
}
