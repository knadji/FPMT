package fr.cnamts.fpmt.batch.writer;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import fr.cnamts.fpmt.model.LigneCSPSEligibiliteBO;

@Component
@StepScope
public class EligiblesWriter implements ItemWriter<LigneCSPSEligibiliteBO> {

    @Override
    public void write(final List<? extends LigneCSPSEligibiliteBO> items) throws Exception {
        // TODO Auto-generated method stub

    }

}
