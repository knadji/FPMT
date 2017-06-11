package fr.cnamts.fpmt.batch.listener;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobListener extends JobExecutionListenerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobListener.class);

    @Override
    public void beforeJob(final JobExecution jobExecution) {
        LOGGER.info("---  ---");
        LOGGER.info(" --- JOB STARTED --- ");
        LOGGER.info("---  ---");
    }

    @Override
    public void afterJob(final JobExecution jobExecution) {

        LOGGER.info("---  ---");
        if (BatchStatus.COMPLETED.equals(jobExecution.getStatus())) {

            HashSet<String> listePS = (HashSet<String>) jobExecution.getExecutionContext().get("listePS");

            if (listePS != null) {
                LOGGER.info("Affichage de la liste des PS : ");
                for (String ps : listePS) {
                    LOGGER.info("--- NumPS : {} ---", ps);
                }
                LOGGER.info("--- Total PS : {} ----", listePS.size());
            }
            LOGGER.info("---  ---");
            LOGGER.info(" --- JOB FINISHED --- ");

        } else if (BatchStatus.FAILED.equals(jobExecution.getStatus())) {
            LOGGER.info(" --- JOB FAILED --- ");
        }
        LOGGER.info("---  ---");
    }
}
