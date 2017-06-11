package fr.cnamts.fpmt.batch.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import fr.cnamts.fpmt.model.LigneCSPSEligibiliteBO;

public class PsMapper implements FieldSetMapper<LigneCSPSEligibiliteBO> {

    @Override
    public LigneCSPSEligibiliteBO mapFieldSet(final FieldSet fieldSet) throws BindException {

        if (fieldSet == null) {
            return null;
        }

        LigneCSPSEligibiliteBO ligneCSPSEligibiliteBO = new LigneCSPSEligibiliteBO();

        ligneCSPSEligibiliteBO.setNumAM(fieldSet.readString("NumAM"));
        ligneCSPSEligibiliteBO.setCpam(fieldSet.readString("Cpam"));
        ligneCSPSEligibiliteBO.setDateInstallation(fieldSet.readString("DateInstallation"));
        ligneCSPSEligibiliteBO.setCategorieEtablissement(fieldSet.readInt("Categorie"));
        ligneCSPSEligibiliteBO.setCiv(fieldSet.readString("Civ"));
        ligneCSPSEligibiliteBO.setNomPS(fieldSet.readString("Nom"));
        ligneCSPSEligibiliteBO.setPrenomPS(fieldSet.readString("Prenom"));
        ligneCSPSEligibiliteBO.setRaisonSocialeCS(fieldSet.readString("RaisonSociale"));
        ligneCSPSEligibiliteBO.setNumVoie(fieldSet.readString("NumVoie"));
        ligneCSPSEligibiliteBO.setCpltNumvoie(fieldSet.readString("CpltNumvoie"));
        ligneCSPSEligibiliteBO.setTypeVoie(fieldSet.readString("TypeVoie"));
        ligneCSPSEligibiliteBO.setLibVoie(fieldSet.readString("LibVoie"));
        ligneCSPSEligibiliteBO.setCpltAdresse(fieldSet.readString("CpltAdresse"));
        ligneCSPSEligibiliteBO.setLieudit(fieldSet.readString("Lieudit"));
        ligneCSPSEligibiliteBO.setCodePostal(fieldSet.readString("CodePostal"));
        ligneCSPSEligibiliteBO.setLibCommune(fieldSet.readString("LibCommune"));
        ligneCSPSEligibiliteBO.setDptCommune(fieldSet.readString("DptCommune"));
        ligneCSPSEligibiliteBO.setCodeCommune(fieldSet.readString("CodeCommune"));
        ligneCSPSEligibiliteBO.setPays(fieldSet.readString("Pays"));
        ligneCSPSEligibiliteBO.setNumFiness(fieldSet.readString("NumFINESS"));
        ligneCSPSEligibiliteBO.setDateMAJ(fieldSet.readString("DateMAJ"));
        ligneCSPSEligibiliteBO.setCodeNatureExercice(fieldSet.readString("CodeNatureExercice"));
        ligneCSPSEligibiliteBO.setDateEffetNatExe(fieldSet.readDate("DateEffetNatExe","yyyyMMdd"));
        ligneCSPSEligibiliteBO.setCodeMotifNatExe(fieldSet.readString("CodeMotifNatExe"));
        ligneCSPSEligibiliteBO.setCodeConvention(fieldSet.readString("CodeConvention"));
        ligneCSPSEligibiliteBO.setContratAccesSoins(fieldSet.readString("ContratAccesSoins"));
        ligneCSPSEligibiliteBO.setDateEffetContratAccesSoins(fieldSet.readString("DateEffetContratAccesSoins"));
        ligneCSPSEligibiliteBO.setMotifSortieContratAccesSoins(fieldSet.readString("MotifSortieContratAccesSoins"));
        ligneCSPSEligibiliteBO.setCodeSpecialite(fieldSet.readString("CodeSpecialite"));
        ligneCSPSEligibiliteBO.setDateDebutSpecialite(fieldSet.readString("DateDebutSpecialite"));
        ligneCSPSEligibiliteBO.setDateFinSpecialite(fieldSet.readString("DateFinSpecialite"));
        ligneCSPSEligibiliteBO.setNbrCabSec(fieldSet.readString("NbrCabSec"));
        ligneCSPSEligibiliteBO.setCabSecondMed(fieldSet.readString("CabSecondMed"));
        ligneCSPSEligibiliteBO.setNumRPPS(fieldSet.readString("NumRPPS"));
        ligneCSPSEligibiliteBO.setNir(fieldSet.readString("Nir"));

        // Le cas ou le fihcier est au format C, la présence obligatoire de la colenne ZoneTarif
        if (fieldSet.getProperties().containsKey("ZoneTarif")) {
            ligneCSPSEligibiliteBO.setDateInstallationdep(fieldSet.readString("DateInstallationdep"));
            ligneCSPSEligibiliteBO.setDateEffetConvention(fieldSet.readString("DateEffetConvention"));
            ligneCSPSEligibiliteBO.setCodeConventionPrec(fieldSet.readString("CodeConventionPrec"));
            ligneCSPSEligibiliteBO.setDateEffetConventionPrec(fieldSet.readString("DateEffetConventionPrec"));
            ligneCSPSEligibiliteBO.setZoneTarif(fieldSet.readString("ZoneTarif"));
        } else {
            // Fichier au format B
            ligneCSPSEligibiliteBO.setDateInstallationdep(null);
            ligneCSPSEligibiliteBO.setDateEffetConvention(null);
            ligneCSPSEligibiliteBO.setCodeConventionPrec(null);
            ligneCSPSEligibiliteBO.setDateEffetConventionPrec(null);
            ligneCSPSEligibiliteBO.setZoneTarif(null);
        }

        return ligneCSPSEligibiliteBO;
    }
}
