package hu.evocelot.sample.service.sample;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.evocelot.sample.accessor.SampleEntityAccessor;
import hu.evocelot.sample.converter.SampleEntityWithIdConverter;
import hu.evocelot.sample.dto.SampleEntityWithIdDto;
import hu.evocelot.sample.exception.BaseException;
import hu.evocelot.sample.exception.ExceptionType;
import hu.evocelot.sample.model.SampleEntity;

/**
 * Sample service class for deleting sample entities.
 * 
 * @author mark.danisovszky
 */
@Service
public class DeleteSampleEntityService {

    private SampleEntityWithIdConverter sampleEntityWithIdConverter;
    private SampleEntityAccessor sampleService;

    public DeleteSampleEntityService(SampleEntityWithIdConverter sampleEntityWithIdConverter,
            SampleEntityAccessor sampleService) {
        this.sampleEntityWithIdConverter = sampleEntityWithIdConverter;
        this.sampleService = sampleService;
    }

    /**
     * Deletes a sample entity by ID.
     *
     * @param id - ID of the sample to delete.
     * @return - with {@link ResponseEntity} that contains the
     *         {@link SampleEntityWithIdDto}.
     */
    public ResponseEntity<SampleEntityWithIdDto> deleteSampleEntity(String id) throws BaseException {
        // Find the entity to delete.
        Optional<SampleEntity> optionalSampleEntity = sampleService.findById(id);
        if (optionalSampleEntity.isEmpty()) {
            throw new BaseException(HttpStatus.NOT_FOUND, ExceptionType.SAMPLE_ENTITY_NOT_FOUND,
                    "Cannot find sample entity with id :" + id);
        }

        // Store the details for the response.
        SampleEntity sampleEntity = optionalSampleEntity.get();
        SampleEntityWithIdDto response = sampleEntityWithIdConverter.convert(sampleEntity);

        // Delete the entity.
        sampleService.delete(sampleEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
