package hu.evocelot.sample.accessor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import hu.evocelot.sample.model.SampleEntity;
import hu.evocelot.sample.repository.SampleRepository;

/**
 * Sample accessor for accessing the {@link SampleEntity}.
 * 
 * @author mark.danisovszky
 */
@Service
public class SampleEntityAccessor extends AbstractEntityAccessor<SampleEntity> {

    private SampleRepository sampleRepository;

    public SampleEntityAccessor(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Override
    protected JpaRepository getRepository() {
        return sampleRepository;
    }
}
