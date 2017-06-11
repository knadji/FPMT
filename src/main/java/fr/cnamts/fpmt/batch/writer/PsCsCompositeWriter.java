package fr.cnamts.fpmt.batch.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.cnamts.fpmt.model.LigneCSPSEligibiliteBO;

@Component
@StepScope
public class PsCsCompositeWriter implements ItemWriter<LigneCSPSEligibiliteBO> {

    final ItemWriter<LigneCSPSEligibiliteBO> eligiblesWriter;
    final ItemWriter<LigneCSPSEligibiliteBO> nonEligiblesWriter;

    @Autowired
    public PsCsCompositeWriter(final ItemWriter<LigneCSPSEligibiliteBO> writerEligibles,
            final ItemWriter<LigneCSPSEligibiliteBO> writerNonEligibles) {
        this.eligiblesWriter = writerEligibles;
        this.nonEligiblesWriter = writerNonEligibles;
    }

    @Override
    public void write(final List<? extends LigneCSPSEligibiliteBO> items) throws Exception {
        List<LigneCSPSEligibiliteBO> eligiblesPsCs = new ArrayList<LigneCSPSEligibiliteBO>();
        List<LigneCSPSEligibiliteBO> nonEligiblesPsCs = new ArrayList<LigneCSPSEligibiliteBO>(items);

        for (LigneCSPSEligibiliteBO ligne : items) {
            if (ligne.isEligiblePS()) {
                nonEligiblesPsCs.remove(ligne);
                eligiblesPsCs.add(ligne);
            }
        }

        eligiblesWriter.write(eligiblesPsCs);
        nonEligiblesWriter.write(nonEligiblesPsCs);
    }
}
