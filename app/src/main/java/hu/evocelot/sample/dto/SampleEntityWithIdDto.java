package hu.evocelot.sample.dto;

import hu.evocelot.sample.model.SampleEntity;

/**
 * DTO class for {@link SampleEntity} base details with id.
 * 
 * @author mark.danisovszky
 */
public class SampleEntityWithIdDto extends SampleEntityDto {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
