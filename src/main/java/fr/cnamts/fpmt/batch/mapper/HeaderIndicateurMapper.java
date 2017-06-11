package fr.cnamts.fpmt.batch.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import fr.cnamts.fpmt.model.HeaderFichierIndicateur;

public class HeaderIndicateurMapper implements FieldSetMapper<HeaderFichierIndicateur> {

    @Override
    public HeaderFichierIndicateur mapFieldSet(final FieldSet fieldSet) throws BindException {

        if (fieldSet == null) {
            return null;
        }

        return new HeaderFichierIndicateur(fieldSet.readRawString("typeEnregistrement"),
                fieldSet.readRawString("filler1"), fieldSet.readRawString("typeNormeEchange"),
                fieldSet.readRawString("filler2"));
    }
}
