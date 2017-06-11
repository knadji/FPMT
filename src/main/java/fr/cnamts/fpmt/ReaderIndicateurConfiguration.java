package fr.cnamts.fpmt;

import java.io.IOException;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import fr.cnamts.fpmt.batch.mapper.HeaderIndicateurMapper;
import fr.cnamts.fpmt.batch.mapper.IndicateurMapper;
import fr.cnamts.fpmt.model.HeaderFichierIndicateur;
import fr.cnamts.fpmt.model.IndicateurPs;

@Configuration
@PropertySource("file.properties")
public class ReaderIndicateurConfiguration {

    /**
     * Le chemin vers le dossier des indicateurs
     */
    @Value("${file.path.indicateurs}")
    public String filePathIndicateurs;

    /**
     * ReaderIndicateurConfiguration permet de configure les readers du fichier indicateur patientèle.
     */

    /**
     * Reader de la première ligne du fichier des indicateurs
     *
     * @return un reader
     * @throws FileException
     * @throws IOException
     */
    @Bean
    public ItemReader<HeaderFichierIndicateur> headerReader() throws IOException {

        FlatFileItemReader<HeaderFichierIndicateur> reader = new FlatFileItemReader<HeaderFichierIndicateur>();
        reader.setResource(new FileSystemResource(filePathIndicateurs + getFileNameIndicateur()));
        reader.setLineMapper(headerMapper());
        reader.setMaxItemCount(1);
        return reader;
    }

    /**
     * Reader des numéro de Ps à partir du fichier des indicateurs.
     *
     * @return un reader
     * @throws FileException
     * @throws IOException
     */
    @Bean
    public ItemReader<IndicateurPs> indicateurReader() throws IOException {

        FlatFileItemReader<IndicateurPs> reader = new FlatFileItemReader<IndicateurPs>();
        reader.setResource(new FileSystemResource(filePathIndicateurs + getFileNameIndicateur()));
        reader.setLineMapper(indicateurMapper());
        reader.setLinesToSkip(1);
        return reader;
    }

    /**
     * Permet de faire le mapping d'une ligne en objet HeaderFichierIndicateur
     *
     * @return
     */
    private static LineMapper<HeaderFichierIndicateur> headerMapper() {
        DefaultLineMapper<HeaderFichierIndicateur> mapper = new DefaultLineMapper<HeaderFichierIndicateur>();
        mapper.setLineTokenizer(headerTokenizer());
        mapper.setFieldSetMapper(new HeaderIndicateurMapper());
        return mapper;
    }

    /**
     * Mapping d'une ligne du fichier en objet IndicateurPs
     *
     * @return
     */
    private static LineMapper<IndicateurPs> indicateurMapper() {
        DefaultLineMapper<IndicateurPs> mapper = new DefaultLineMapper<IndicateurPs>();
        mapper.setLineTokenizer(indicateurTokenizer());
        mapper.setFieldSetMapper(new IndicateurMapper());
        return mapper;
    }

    /**
     * Position des élements du header cf.SFD
     *
     * @return
     */
    private static LineTokenizer headerTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setColumns(new Range[] { new Range(1, 3), new Range(4, 81), new Range(82, 83), new Range(84, 142) });
        tokenizer.setStrict(false);
        tokenizer.setNames(new String[] { "typeEnregistrement", "filler1", "typeNormeEchange", "filler2" });
        return tokenizer;
    }

    /**
     * Position des PS dans le fichier
     *
     * @return
     */
    private static LineTokenizer indicateurTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setColumns(new Range[] { new Range(1, 3), new Range(6, 14), new Range(18, 25), new Range(26, 33),
                new Range(34, 39), new Range(40, 45), new Range(46, 53), new Range(54, 61), new Range(21, 28) });
        tokenizer.setStrict(false);
        tokenizer.setNames(new String[] { "typeEnregistrement", "numPS", "dateIndicateur", "dateCalcul", "numerateur",
                "denominateur", "dateDebutObservationTaux", "dateFinObservationTaux", "compteurNombreEnreg110" });
        return tokenizer;
    }

    /**
     * Récupérer le chemin complet du fichier indicateur
     *
     * @return
     * @throws FileException
     */
    private String getFileNameIndicateur() throws IOException {

        Resource[] allResources = new PathMatchingResourcePatternResolver()
                .getResources("file:" + filePathIndicateurs + "*.txt");

        // Il existe qu'un seul fichier de ressource
        return allResources[0].getFilename();
    }
}
