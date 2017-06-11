package fr.cnamts.fpmt.utils;

import static fr.cnamts.fpmt.utils.ValidationPS.allDatePsValide;
import static fr.cnamts.fpmt.utils.ValidationPS.dateValide;
import static fr.cnamts.fpmt.utils.ValidationPS.nbLigne110Invalid;
import static fr.cnamts.fpmt.utils.ValidationPS.numerateurDenominateurValide;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.cnamts.fpmt.model.Entite;
import fr.cnamts.fpmt.model.IndicateurPs;

public class ValidationPSTest {

    @Test
    public void should_return_true_when_number_of_lines_110_is_invalid() {

        IndicateurPs indicateurPs = new IndicateurPs(null, null, null, null, null, null, null, null, 7);
        Entite entite110 = new Entite();

        entite110.setNombreLigne(3);

        assertTrue("Le r�sultat de comparaison doit �tre vrai", nbLigne110Invalid(indicateurPs, entite110));
    }

    @Test
    public void should_return_false_when_number_of_lines_110_is_valid() {

        IndicateurPs indicateurPs = new IndicateurPs(null, null, null, null, null, null, null, null, 9);
        Entite entite110 = new Entite();

        entite110.setNombreLigne(9);

        assertFalse("Le r�sultat de comparaison doit �tre faux", nbLigne110Invalid(indicateurPs, entite110));
    }

    @Test
    public void should_return_true_when_numerateur_and_denominateur_is_valid() {

        IndicateurPs indicateurPs = new IndicateurPs(null, null, null, null, "-94521", "955499", null, null, 0);

        assertTrue("Le num�rateur et le d�nominateur doivent �tre valide", numerateurDenominateurValide(indicateurPs));
    }

    @Test
    public void should_return_false_when_numerateur_and_denominateur_is_invalid() {

        IndicateurPs indicateurPs = new IndicateurPs(null, null, null, null, "98 5 1", "K55499", null, null, 0);

        assertFalse("Le num�rateur et le d�nominateur ne devrait pas �tre valide",
                numerateurDenominateurValide(indicateurPs));
    }

    @Test
    public void should_return_true_date_is_valid() {
        assertTrue("La date devrait �tre valide", dateValide("20170505"));
    }

    @Test
    public void should_return_false_date_day_is_invalid() {
        assertFalse("La date ne devrait pas �tre valide", dateValide("00001245"));
    }

    @Test
    public void should_return_false_date_month_is_invalid() {
        assertFalse("La date ne devrait pas �tre valide", dateValide("20179902"));
    }

    @Test
    public void should_return_false_date_year_is_invalid() {
        assertFalse("La date ne devrait pas �tre valide", dateValide("00001216"));
    }

    @Test
    public void should_return_false_date_content_a_space() {
        assertFalse("La date ne devrait pas �tre valide", dateValide("201705 5"));
    }

    @Test
    public void should_return_false_date_length_is_invalid() {
        assertFalse("La date ne devrait pas �tre valide", dateValide("20175"));
    }

    @Test
    public void should_return_true_all_date_PS_is_valid() {

        IndicateurPs indicateurPs = new IndicateurPs(null, null, "20170505", "20170512", null, null, "20170503",
                "20170531", 0);

        assertTrue("L'ensembles des dates du PS devrait �tre valide", allDatePsValide(indicateurPs));
    }

    @Test
    public void should_return_false_all_date_PS_is_invalid() {

        IndicateurPs indicateurPs = new IndicateurPs(null, null, "00000503", "201 3513", null, null, "00100503",
                "20170545", 0);

        assertFalse("L'ensembles des dates du PS ne devrait pas �tre valide", allDatePsValide(indicateurPs));
    }
}
