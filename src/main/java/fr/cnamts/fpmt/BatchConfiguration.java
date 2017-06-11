package fr.cnamts.fpmt;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import fr.cnamts.fpmt.batch.listener.JobListener;
import fr.cnamts.fpmt.batch.processor.HeaderIndicateurProcessor;
import fr.cnamts.fpmt.batch.processor.IndicateurProcessor;
import fr.cnamts.fpmt.batch.processor.PsProcessor;
import fr.cnamts.fpmt.model.Entite;
import fr.cnamts.fpmt.model.HeaderFichierIndicateur;
import fr.cnamts.fpmt.model.IndicateurPs;
import fr.cnamts.fpmt.model.LigneCSPSEligibiliteBO;

@Configuration
@EnableBatchProcessing
@PropertySource("application.properties")
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public JobListener jobListener() {
        return new JobListener();
    }

    @Bean
    public Entite entite100() {
        return new Entite();
    }

    @Bean
    public Entite entite110() {
        return new Entite();
    }

    @Bean
    public Entite entite990() {
        return new Entite();
    }

    @Bean
    public Entite entite999() {
        return new Entite();
    }

    /**
     * Le nom de la liste des PS
     *
     * @return
     */
    @Bean
    public String listePsKey() {
        return "listePS";
    }

    @Bean
    public Job traitementPsCsJob(final JobExecutionListener jobListener, final Step chargementHeaderIndicateur,
            final Step chargementIndicateur, final Step chargementFichierPsCs) {
        return jobBuilderFactory.get("traitementPsCsJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .start(chargementHeaderIndicateur)
                .next(chargementIndicateur)
                .next(chargementFichierPsCs)
                .build();
    }

    @Bean
    public Step chargementHeaderIndicateur(final ItemReader<HeaderFichierIndicateur> headerReader,
            final HeaderIndicateurProcessor headerIndicateurProcessor) {
        return stepBuilderFactory.get("chargementHeaderIndicateur")
                .<HeaderFichierIndicateur, HeaderFichierIndicateur> chunk(100)
                .reader(headerReader)
                .processor(headerIndicateurProcessor)
                .build();
    }

    @Bean
    public Step chargementIndicateur(final ItemReader<IndicateurPs> readerIndicateur,
            final IndicateurProcessor indicateurProcessor) {
        return stepBuilderFactory.get("chargementPsIndicateur")
                .<IndicateurPs, IndicateurPs> chunk(100)
                .reader(readerIndicateur)
                .processor(indicateurProcessor)
                .build();
    }

    @Bean
    public Step chargementFichierPsCs(final MultiResourceItemReader<LigneCSPSEligibiliteBO> psCsReader,
            final PsProcessor psCsProcessor) {
        return stepBuilderFactory.get("chargementFichierPsCs")
                .<LigneCSPSEligibiliteBO, LigneCSPSEligibiliteBO> chunk(100)
                .reader(psCsReader)
                .processor(psCsProcessor)
                .build();
    }
}