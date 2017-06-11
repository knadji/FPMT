package fr.cnamts.fpmt.model;

public class Entite {

    /**
     * Entite est une repr�sentation d'une entit� (100, 110, 990, 999) du fichier indicateur.
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
     * V�rifier s'il existe plusieurs enregistrement de l'entit�.
     *
     * @return true | false
     */
    public boolean existePlusieursEnreg() {
        return nombreLigne != 1;
    }
}
