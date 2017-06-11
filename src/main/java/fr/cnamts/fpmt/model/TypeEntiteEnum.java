package fr.cnamts.fpmt.model;

public enum TypeEntiteEnum {

    // Les entité du fichier des indicateurs.

    ENTITE_000("000"), ENTITE_100("100"), ENTITE_110("110"), ENTITE_990("990"), ENTITE_999("999");

    private String nomEntite;

    TypeEntiteEnum(final String nomEntite) {
        this.nomEntite = nomEntite;
    }

    public String getValeurEntite() {
        return nomEntite;
    }

}
