package hu.evocelot.sample.dto;

import java.time.OffsetDateTime;

import hu.evocelot.sample.model.SampleEntity;

/**
 * The sample DTO.
 * 
 * @author mark.danisovszky
 */
public class SampleDto {

    public SampleDto() {

    }

    public SampleDto(SampleEntity sampleEntity) {
        this.setSampleValue(sampleEntity.getSampleValue());
        this.setSampleDate(sampleEntity.getSampleDate());
    }

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
