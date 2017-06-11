package fr.cnamts.fpmt.model;

public class HeaderFichierIndicateur {

    /**
     * EnteteFichierIndicateur est une repr�sentation de l'en-t�te du fichier des indicateurs patientèle.
     */

    /**
     * Le type de l'enregistrement en-t�te
     */
    private final String typeEnregistrement;

    /**
     * S�parateur des donn�es
     */
    private final String filler1;

    /**
     * Le type de la norme d'Echanges
     */
    private final String typeNormeEchange;

    /**
     * S�parateur des donn�es
     */
    private final String filler2;

    public HeaderFichierIndicateur(final String typeEnregistrement, final String filler1, final String typeNormeEchange,
            final String filler2) {
        this.typeEnregistrement = typeEnregistrement;
        this.filler1 = filler1;
        this.typeNormeEchange = typeNormeEchange;
        this.filler2 = filler2;
    }

    public String getTypeEnregistrement() {
        return typeEnregistrement;
    }

    public String getFiller1() {
        return filler1;
    }

    public int getLongeurFiller1() {
        return filler1.length();
    }

    public String getTypeNormeEchange() {
        return typeNormeEchange;
    }

    public String getFiller2() {
        return filler2;
    }

    public int getLongeurFiller2() {
        return filler2.length();
    }

    @Override
    public String toString() {
        return "EnteteFichierIndicateur [typeEnregistrement=" + typeEnregistrement + ", filler1=" + filler1
                + ", typeNormeEchange=" + typeNormeEchange + ", filler2=" + filler2 + "]";
    }

}
