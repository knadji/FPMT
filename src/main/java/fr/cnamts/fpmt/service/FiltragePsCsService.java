package fr.cnamts.fpmt.service;

import java.util.Date;
import java.util.Set;

import fr.cnamts.fpmt.model.LigneCSPSEligibiliteBO;

public class FiltragePsCsService {

    private static final String MODE_PAIEMENT_1 = "P1";

    private static final String MODE_PAIEMENT_2 = "P2";

    private static final String MODE_PAIEMENT_3 = "P3";

    private static final String MODE_PAIEMENT_4 = "P4";

    /**
     * LigneCSPSEligibiliteBO � traiter
     */
    private final LigneCSPSEligibiliteBO ligneCSPSEligibiliteBO;

    /**
     * La date de r�f�rence passe dans le script shell
     */
    private final Date dateDeReference;

    /**
     * Le mode de traitement du batch
     */
    private final String modeDeTraitement;

    /**
     * Liste des indicateurs PS
     */
    private final Set<String> listePsIndicateur;

    public FiltragePsCsService(final LigneCSPSEligibiliteBO ligneCSPSEligibiliteBO, final Set<String> listePsIndicateur,
            final Date dateDeReference, final String modeDeTraitement) {
        this.ligneCSPSEligibiliteBO = ligneCSPSEligibiliteBO;
        this.listePsIndicateur = listePsIndicateur;
        this.dateDeReference = dateDeReference;
        this.modeDeTraitement = modeDeTraitement;
    }

    /**
     * Filtrage des PS CS en fonction du mode de calcule du batch.
     *
     * @return un ligneCSPSEligibiliteBO
     */
    public LigneCSPSEligibiliteBO traitementPsCs() {
        if (verifeRgEligibiliteIndicateur()) {
            if (verifeRgEligibiliteDate()) {
                verifeRgEligibiliteCS();
            }
        }
        return ligneCSPSEligibiliteBO;
    }

    /**
     * Application du premier contr�le sur le PS/CS (r�gle de gestion CF.RG_ELIGIBILITE_IND)
     *
     * @return si le PS/CS est eligible
     */
    private boolean verifeRgEligibiliteIndicateur() {
        if (listePsIndicateur.contains(ligneCSPSEligibiliteBO.getNumAM())) {
            ligneCSPSEligibiliteBO.setEligiblePS(true);
        } else {
            if (modePaiement()) {
                ligneCSPSEligibiliteBO.setMotifNonEligible("Absence indicateur");
            }
            ligneCSPSEligibiliteBO.setEligiblePS(false);
        }
        return ligneCSPSEligibiliteBO.isEligiblePS();
    }

    /**
     * Application du deuxi�me contr�le sur le PS/CS (r�gle de gestion CF.RG_ELIGIBILITE_DATE)
     *
     * @return si le PS/CS est eligible
     */
    private boolean verifeRgEligibiliteDate() {
        if (codeNatureExercice07()) {
            if (dateAnterieure()) {
                if (modePaiement()) {
                    ligneCSPSEligibiliteBO.setMotifNonEligible("PS / CS inactif � la date de r�f�rence");
                }
                ligneCSPSEligibiliteBO.setEligiblePS(false);
            }
        }
        return ligneCSPSEligibiliteBO.isEligiblePS();
    }

    /**
     * Application de la r�gle de gestion CF.RG_ELIGIBILITE_CS
     */
    private void verifeRgEligibiliteCS() {

        if (ligneCS()) {
            if (modePaiement()) {
                if (!csEligible()) {
                    ligneCSPSEligibiliteBO.setEligiblePS(false);
                    ligneCSPSEligibiliteBO.setMotifNonEligible("Cat�gorie d'�tablissement non retenue");
                }
            }
        }
    }

    /**
     * V�rifier si un CS est �ligible CF.RG_ELIGIBILITE_CS
     *
     * @return le CS est eligible
     */
    private boolean csEligible() {
        return "0".charAt(0) == ligneCSPSEligibiliteBO.getNumFiness().charAt(2)
                && ligneCSPSEligibiliteBO.getCategorieEtablissement() == 124;
    }

    /**
     * V�rifier si l'objet ligneCSPSEligibiliteBO est un CS
     *
     * @return la ligneCSPSEligibiliteBO est un CS
     */
    private boolean ligneCS() {
        return "CS".equals(ligneCSPSEligibiliteBO.getFileParentSource());
    }

    /**
     * V�rifier si la date de fin d'activit� est ant�rieure � la date de r�f�rence.
     *
     * @return
     */
    private boolean dateAnterieure() {
        return ligneCSPSEligibiliteBO.getDateEffetNatExe().before(dateDeReference);
    }

    /**
     * V�rifier si le code nature exercice est �gale � 07
     *
     * @return
     */
    private boolean codeNatureExercice07() {
        return "07".equals(ligneCSPSEligibiliteBO.getCodeNatureExercice());
    }

    /**
     * V�rifier si le mode de calcule est paiement
     *
     * @return le mode de calcule est paiement
     */
    private boolean modePaiement() {
        return MODE_PAIEMENT_1.equals(modeDeTraitement) || MODE_PAIEMENT_2.equals(modeDeTraitement)
                || MODE_PAIEMENT_3.equals(modeDeTraitement) || MODE_PAIEMENT_4.equals(modeDeTraitement);
    }
}
