package fr.cnamts.fpmt;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineCallbackHandler;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import fr.cnamts.fpmt.batch.exception.EnteteInvalidException;
import fr.cnamts.fpmt.batch.mapper.PsMapper;
import fr.cnamts.fpmt.model.LigneCSPSEligibiliteBO;

@Configuration
@PropertySource("file.properties")
public class ReaderPsCsConfiguration {

    private static final String FILE_FORMAT_B = "fileFormatB";

    private static final String FILE_FORMAT_C = "fileFormatC";

    /**
     * Le chemin vers le dossier des Ps
     */
    @Value("${file.path.ps}")
    public String filePathPs;

    @Value("${entete.file.ps.cs.formatB}")
    public String enteteFilePsCsFormatB;

    @Value("${entete.file.ps.cs.formatC}")
    public String enteteFilePsCsFormatC;

    /**
     * Le format du fichier qui est en cour de lecture
     */
    public String fileFormat;

    /**
     * ReaderPsCsConfiguration permet de configure le reader du fichier Ps et Cs.
     */

    /**
     * Reader des numéro de Ps à partir du fichier des indicateurs.
     *
     * @return un reader
     * @throws FileException
     * @throws IOException
     */
    @Bean
    public FlatFileItemReader<LigneCSPSEligibiliteBO> reader() throws IOException {

        final FlatFileItemReader<LigneCSPSEligibiliteBO> reader = new FlatFileItemReader<LigneCSPSEligibiliteBO>();
        reader.setLinesToSkip(1);
        reader.setSkippedLinesCallback(new LineCallbackHandler() {
            @Override
            public void handleLine(final String enteteFichierPS) throws EnteteInvalidException {
                if (enteteFilePsCsFormatB.equals(enteteFichierPS)) {
                    fileFormat = FILE_FORMAT_B;
                } else if (enteteFilePsCsFormatC.equals(enteteFichierPS)) {
                    fileFormat = FILE_FORMAT_C;
                } else {
                    throw new EnteteInvalidException("PS");
                }
                reader.setLineMapper(psMapper(fileFormat));
            }
        });
        reader.setLineMapper(new DefaultLineMapper<LigneCSPSEligibiliteBO>());
        return reader;
    }

    /**
     * Reader multiple des fihciers Cs et Ps
     *
     * @return
     * @throws IOException
     */
    @Bean
    public MultiResourceItemReader<LigneCSPSEligibiliteBO> readerPsCs() throws IOException{
        MultiResourceItemReader<LigneCSPSEligibiliteBO> multiReaderPsCs = new MultiResourceItemReader<LigneCSPSEligibiliteBO>();
        multiReaderPsCs.setResources(getFileNamePsCs());
        multiReaderPsCs.setDelegate(reader());
        return multiReaderPsCs;
    }

    /**
     * Mapping d'une ligne du fichier en objet IndicateurPs
     *
     * @param fileFormat le format du fichier
     * @return
     */
    private LineMapper<LigneCSPSEligibiliteBO> psMapper(final String fileFormat) {
        DefaultLineMapper<LigneCSPSEligibiliteBO> mapper = new DefaultLineMapper<LigneCSPSEligibiliteBO>();
        mapper.setLineTokenizer(psTokenizer(fileFormat));
        mapper.setFieldSetMapper(new PsMapper());
        return mapper;
    }

    /**
     * Position des PS dans le fichier
     *
     * @param fileFormat le format du fichier
     * @return
     */
    private LineTokenizer psTokenizer(final String fileFormat) {

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        if (FILE_FORMAT_B.equals(fileFormat)) {
            lineTokenizer.setNames(getColumnNames(enteteFilePsCsFormatB));
        } else {
            lineTokenizer.setNames(getColumnNames(enteteFilePsCsFormatC));
        }
        lineTokenizer.setStrict(false);
        return lineTokenizer;
    }

    /**
     * Récupérer les noms des colonnes à partir de l'en-tête du fichier
     *
     * @param enteteFilePsCs l'entete au format (B ou C)
     * @return un tableau de String
     */
    private String[] getColumnNames(final String enteteFilePsCs) {
        return Arrays.asList(enteteFilePsCs.split(";")).toArray(new String[] {});
    }

    /**
     * Récupérer le chemin complet du fichier indicateur
     *
     * @return
     * @throws FileException
     */
    private Resource[] getFileNamePsCs() throws IOException {
        return new PathMatchingResourcePatternResolver().getResources("file:"
                + filePathPs + "*.csv");
    }
}