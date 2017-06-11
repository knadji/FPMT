package fr.cnamts.fpmt.model;

import java.util.Date;

import org.springframework.batch.item.ResourceAware;
import org.springframework.core.io.Resource;

public class LigneCSPSEligibiliteBO implements ResourceAware {

    /**
     * LigneCSPSEligibiliteBO est une représentation d'une ligne de PS ou CS.
     */

    /**
     * CentresdeSante correspond au type de fichier CS
     * format : <ReFo>_Extraction_<Type>_<Date_de_référence>_<Date_traitement>.csv
     */
    private static final String CENTRES_DE_SANTE = "CentresdeSante";

    /**
     * Medecins correspond au type de fichier PS
     * format : <ReFo>_Extraction_<Type>_<Date_de_référence>_<Date_traitement>.csv
     */
    private static final String MEDECINS = "Medecins";

    /**
     * La source du fichier (CS ou PS)
     */
    private String fileParentSource;

    /**
     * Numéro assurance maladie.
     */
    private String numAM;

    /**
     *
     */
    private String cpam;

    /**
     * Date de début d'activité libérale
     */
    private String dateInstallation;

    /**
     * Catégorié d'établissement
     */
    private int categorieEtablissement;

    /**
     * Civilité
     */
    private String civ;

    /**
     * Le nom du personnel de santé
     */
    private String nomPS;

    /**
     * Le prénom du personnel de santé
     */
    private String prenomPS;

    /**
     * La raison sociale du centre de santé
     */
    private String raisonSocialeCS;

    /**
     * Numéro dans la voie
     */
    private String numVoie;

    /**
     * Complément au numéro dans la voie
     */
    private String cpltNumvoie;

    /**
     * Type de voie
     */
    private String typeVoie;

    /**
     * Libellé de voie
     */
    private String libVoie;

    /**
     * Complément d'adresse
     */
    private String cpltAdresse;

    /**
     * Libellé du lieu-dit
     */
    private String lieudit;

    /**
     * Code postal
     */
    private String codePostal;

    /**
     * Libellé de la commune
     */
    private String libCommune;

    /**
     * Code département de la commune
     */
    private String dptCommune;

    /**
     * Code commune
     */
    private String codeCommune;

    /**
     * Libellé du pays
     */
    private String pays;

    /**
     * Le numéro finess
     */
    private String numFiness;

    /**
     * Date de mise à jour Assurance Maladie par la CPG-COD gestionnaire du PFS
     */
    private String dateMAJ;

    /**
     * Nature d'exercice
     */
    private String codeNatureExercice;

    /**
     * Date d'effet nature exercice
     */
    private Date dateEffetNatExe;

    /**
     * Code motif nature exercice
     */
    private String codeMotifNatExe;

    /**
     * Code convention
     */
    private String codeConvention;

    /**
     * Code du contrat d'accès aux soins
     */
    private String contratAccesSoins;

    /**
     * Date d'effet du contrat d'accès aux soins
     */
    private String dateEffetContratAccesSoins;

    /**
     * Motif de sortie de l'option conventionnelle précédente
     */
    private String motifSortieContratAccesSoins;

    /**
     * Code spécialité médecin
     */
    private String codeSpecialite;

    /**
     * Date de début spécialité
     */
    private String dateDebutSpecialite;

    /**
     * Date de fin spécialité
     */
    private String dateFinSpecialite;

    /**
     * Nombre de cabinets secondaires
     */
    private String nbrCabSec;

    /**
     * Liste des éventuels cabinets secondaires des médecins et cabinets principaux
     */
    private String cabSecondMed;

    /**
     * Le numéro du Répertoire Partagé des Professionnels de Santé
     */
    private String numRPPS;

    /**
     * Numéro d'immatriculation
     */
    private String nir;

    /**
     * Date d'installation dans le département
     */
    private String dateInstallationdep;

    /**
     * Date d'effet de la convention
     */
    private String dateEffetConvention;

    /**
     * Code convention précédent
     */
    private String codeConventionPrec;

    /**
     * Date d'effet de la convention précédnete
     */
    private String dateEffetConventionPrec;

    /**
     * Zone tarifaire
     */
    private String zoneTarif;

    /**
     * État d'éligibilité du numAM
     */
    private boolean eligiblePS;

    /**
     * Le Motif de non éligibilité
     */
    private String motifPS;

    public String getNumAM() {
        return numAM;
    }

    public void setNumAM(final String numAM) {
        this.numAM = numAM;
    }

    public String getCpam() {
        return cpam;
    }

    public void setCpam(final String cpam) {
        this.cpam = cpam;
    }

    public String getDateInstallation() {
        return dateInstallation;
    }

    public void setDateInstallation(final String dateInstallation) {
        this.dateInstallation = dateInstallation;
    }

    public int getCategorieEtablissement() {
        return categorieEtablissement;
    }

    public void setCategorieEtablissement(final int categorieEtablissement) {
        this.categorieEtablissement = categorieEtablissement;
    }

    public String getCiv() {
        return civ;
    }

    public void setCiv(final String civ) {
        this.civ = civ;
    }

    public String getNomPS() {
        return nomPS;
    }

    public void setNomPS(final String nomPS) {
        this.nomPS = nomPS;
    }

    public String getPrenomPS() {
        return prenomPS;
    }

    public void setPrenomPS(final String prenomPS) {
        this.prenomPS = prenomPS;
    }

    public String getRaisonSocialeCS() {
        return raisonSocialeCS;
    }

    public void setRaisonSocialeCS(final String raisonSocialeCS) {
        this.raisonSocialeCS = raisonSocialeCS;
    }

    public String getNumVoie() {
        return numVoie;
    }

    public void setNumVoie(final String numVoie) {
        this.numVoie = numVoie;
    }

    public String getCpltNumvoie() {
        return cpltNumvoie;
    }

    public void setCpltNumvoie(final String cpltNumvoie) {
        this.cpltNumvoie = cpltNumvoie;
    }

    public String getTypeVoie() {
        return typeVoie;
    }

    public void setTypeVoie(final String typeVoie) {
        this.typeVoie = typeVoie;
    }

    public String getLibVoie() {
        return libVoie;
    }

    public void setLibVoie(final String libVoie) {
        this.libVoie = libVoie;
    }

    public String getCpltAdresse() {
        return cpltAdresse;
    }

    public void setCpltAdresse(final String cpltAdresse) {
        this.cpltAdresse = cpltAdresse;
    }

    public String getLieudit() {
        return lieudit;
    }

    public void setLieudit(final String lieudit) {
        this.lieudit = lieudit;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(final String codePostal) {
        this.codePostal = codePostal;
    }

    public String getLibCommune() {
        return libCommune;
    }

    public void setLibCommune(final String libCommune) {
        this.libCommune = libCommune;
    }

    public String getDptCommune() {
        return dptCommune;
    }

    public void setDptCommune(final String dptCommune) {
        this.dptCommune = dptCommune;
    }

    public String getCodeCommune() {
        return codeCommune;
    }

    public void setCodeCommune(final String codeCommune) {
        this.codeCommune = codeCommune;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(final String pays) {
        this.pays = pays;
    }

    public String getNumFiness() {
        return numFiness;
    }

    public void setNumFiness(final String numFiness) {
        this.numFiness = numFiness;
    }

    public String getDateMAJ() {
        return dateMAJ;
    }

    public void setDateMAJ(final String dateMAJ) {
        this.dateMAJ = dateMAJ;
    }

    public String getCodeNatureExercice() {
        return codeNatureExercice;
    }

    public void setCodeNatureExercice(final String codeNatureExercice) {
        this.codeNatureExercice = codeNatureExercice;
    }

    public Date getDateEffetNatExe() {
        return dateEffetNatExe;
    }

    public void setDateEffetNatExe(final Date dateEffetNatExe) {
        this.dateEffetNatExe = dateEffetNatExe;
    }

    public String getCodeMotifNatExe() {
        return codeMotifNatExe;
    }

    public void setCodeMotifNatExe(final String codeMotifNatExe) {
        this.codeMotifNatExe = codeMotifNatExe;
    }

    public String getCodeConvention() {
        return codeConvention;
    }

    public void setCodeConvention(final String codeConvention) {
        this.codeConvention = codeConvention;
    }

    public String getContratAccesSoins() {
        return contratAccesSoins;
    }

    public void setContratAccesSoins(final String contratAccesSoins) {
        this.contratAccesSoins = contratAccesSoins;
    }

    public String getDateEffetContratAccesSoins() {
        return dateEffetContratAccesSoins;
    }

    public void setDateEffetContratAccesSoins(final String dateEffetContratAccesSoins) {
        this.dateEffetContratAccesSoins = dateEffetContratAccesSoins;
    }

    public String getMotifSortieContratAccesSoins() {
        return motifSortieContratAccesSoins;
    }

    public void setMotifSortieContratAccesSoins(final String motifSortieContratAccesSoins) {
        this.motifSortieContratAccesSoins = motifSortieContratAccesSoins;
    }

    public String getCodeSpecialite() {
        return codeSpecialite;
    }

    public void setCodeSpecialite(final String codeSpecialite) {
        this.codeSpecialite = codeSpecialite;
    }

    public String getDateDebutSpecialite() {
        return dateDebutSpecialite;
    }

    public void setDateDebutSpecialite(final String dateDebutSpecialite) {
        this.dateDebutSpecialite = dateDebutSpecialite;
    }

    public String getDateFinSpecialite() {
        return dateFinSpecialite;
    }

    public void setDateFinSpecialite(final String dateFinSpecialite) {
        this.dateFinSpecialite = dateFinSpecialite;
    }

    public String getNbrCabSec() {
        return nbrCabSec;
    }

    public void setNbrCabSec(final String nbrCabSec) {
        this.nbrCabSec = nbrCabSec;
    }

    public String getCabSecondMed() {
        return cabSecondMed;
    }

    public void setCabSecondMed(final String cabSecondMed) {
        this.cabSecondMed = cabSecondMed;
    }

    public String getNumRPPS() {
        return numRPPS;
    }

    public void setNumRPPS(final String numRPPS) {
        this.numRPPS = numRPPS;
    }

    public String getNir() {
        return nir;
    }

    public void setNir(final String nir) {
        this.nir = nir;
    }

    public String getDateInstallationdep() {
        return dateInstallationdep;
    }

    public void setDateInstallationdep(final String dateInstallationdep) {
        this.dateInstallationdep = dateInstallationdep;
    }

    public String getDateEffetConvention() {
        return dateEffetConvention;
    }

    public void setDateEffetConvention(final String dateEffetConvention) {
        this.dateEffetConvention = dateEffetConvention;
    }

    public String getCodeConventionPrec() {
        return codeConventionPrec;
    }

    public void setCodeConventionPrec(final String codeConventionPrec) {
        this.codeConventionPrec = codeConventionPrec;
    }

    public String getDateEffetConventionPrec() {
        return dateEffetConventionPrec;
    }

    public void setDateEffetConventionPrec(final String dateEffetConventionPrec) {
        this.dateEffetConventionPrec = dateEffetConventionPrec;
    }

    public String getZoneTarif() {
        return zoneTarif;
    }

    public void setZoneTarif(final String zoneTarif) {
        this.zoneTarif = zoneTarif;
    }

    public boolean isEligiblePS() {
        return eligiblePS;
    }

    public void setEligiblePS(final boolean eligiblePS) {
        this.eligiblePS = eligiblePS;
    }

    public String getMotifNonEligible() {
        return motifPS;
    }

    public void setMotifNonEligible(final String motifPS) {
        this.motifPS = motifPS;
    }

    /**
     * Récupérer le nom du fichier source (PS ou CS)
     *
     * @return nom du fichier
     */
    public String getFileParentSource() {
        return fileParentSource;
    }

    @Override
    public void setResource(final Resource fileParentSource) {

        // format : <ReFo>_Extraction_<Type>_<Date_de_référence>_<Date_traitement>.csv
        String[] parts = fileParentSource.getFilename().split("_");
        if (CENTRES_DE_SANTE.equals(parts[2])) {
            this.fileParentSource = "CS";
        } else if (MEDECINS.equals(parts[2])) {
            this.fileParentSource = "PS";
        }
    }

    @Override
    public String toString() {
        return "LigneCSPSEligibiliteBO [fileParentSource=" + fileParentSource + ", numAM=" + numAM + ", cpam=" + cpam
                + ", dateInstallation=" + dateInstallation + ", categorieEtablissement=" + categorieEtablissement
                + ", civ=" + civ + ", nomPS=" + nomPS + ", prenomPS=" + prenomPS + ", raisonSocialeCS="
                + raisonSocialeCS + ", numVoie=" + numVoie + ", cpltNumvoie=" + cpltNumvoie + ", typeVoie=" + typeVoie
                + ", libVoie=" + libVoie + ", cpltAdresse=" + cpltAdresse + ", lieudit=" + lieudit + ", codePostal="
                + codePostal + ", libCommune=" + libCommune + ", dptCommune=" + dptCommune + ", codeCommune="
                + codeCommune + ", pays=" + pays + ", numFiness=" + numFiness + ", dateMAJ=" + dateMAJ
                + ", codeNatureExercice=" + codeNatureExercice + ", dateEffetNatExe=" + dateEffetNatExe
                + ", codeMotifNatExe=" + codeMotifNatExe + ", codeConvention=" + codeConvention + ", contratAccesSoins="
                + contratAccesSoins + ", dateEffetContratAccesSoins=" + dateEffetContratAccesSoins
                + ", motifSortieContratAccesSoins=" + motifSortieContratAccesSoins + ", codeSpecialite="
                + codeSpecialite + ", dateDebutSpecialite=" + dateDebutSpecialite + ", dateFinSpecialite="
                + dateFinSpecialite + ", nbrCabSec=" + nbrCabSec + ", cabSecondMed=" + cabSecondMed + ", numRPPS="
                + numRPPS + ", nir=" + nir + ", dateInstallationdep=" + dateInstallationdep + ", dateEffetConvention="
                + dateEffetConvention + ", codeConventionPrec=" + codeConventionPrec + ", dateEffetConventionPrec="
                + dateEffetConventionPrec + ", zoneTarif=" + zoneTarif + ", eligiblePS=" + eligiblePS + ", motifPS="
                + motifPS + "]";
    }
}
