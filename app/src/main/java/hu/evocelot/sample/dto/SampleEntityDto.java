package hu.evocelot.sample.dto;

import java.time.OffsetDateTime;

import hu.evocelot.sample.model.SampleEntity;

/**
 * DTO class for {@link SampleEntity} base details.
 * 
 * @author mark.danisovszky
 */
public class SampleEntityDto {

    private String sampleValue;
    private OffsetDateTime sampleDate;

    public String getSampleValue() {
        return sampleValue;
    }

    public void setSampleValue(String sampleValue) {
        this.sampleValue = sampleValue;
    }

    public OffsetDateTime getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(OffsetDateTime sampleDate) {
        this.sampleDate = sampleDate;
    }
}
