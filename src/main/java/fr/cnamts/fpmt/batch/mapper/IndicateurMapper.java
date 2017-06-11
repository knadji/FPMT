package fr.cnamts.fpmt.batch.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import fr.cnamts.fpmt.model.IndicateurPs;
import fr.cnamts.fpmt.model.TypeEntiteEnum;

public class IndicateurMapper implements FieldSetMapper<IndicateurPs> {

    @Override
    public IndicateurPs mapFieldSet(final FieldSet fieldSet) throws BindException {

        int cptNbEntite110 = 0;

        if (fieldSet == null) {
            return null;
        }

        // Setter le compteur du nombre d'enregistrement de type 110 que pour l'entite 990 sinon null
        if (TypeEntiteEnum.ENTITE_990.getValeurEntite()
                .equals(fieldSet.getProperties().getProperty("typeEnregistrement"))) {
            cptNbEntite110 = fieldSet.readInt("compteurNombreEnreg110");
        }

        return new IndicateurPs(fieldSet.readString("typeEnregistrement"), fieldSet.readString("numPS"),
                fieldSet.readString("dateIndicateur"), fieldSet.readString("dateCalcul"),
                fieldSet.readString("numerateur"), fieldSet.readString("denominateur"),
                fieldSet.readString("dateDebutObservationTaux"), fieldSet.readString("dateFinObservationTaux"),
                cptNbEntite110);
    }
}
