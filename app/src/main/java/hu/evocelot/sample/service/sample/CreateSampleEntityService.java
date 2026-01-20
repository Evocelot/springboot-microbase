package hu.evocelot.sample.service.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hu.evocelot.sample.accessor.SampleEntityAccessor;
import hu.evocelot.sample.converter.SampleEntityConverter;
import hu.evocelot.sample.converter.SampleEntityWithIdConverter;
import hu.evocelot.sample.dto.SampleEntityDto;
import hu.evocelot.sample.dto.SampleEntityWithIdDto;
import hu.evocelot.sample.kafka.KafkaMessageProducer;
import hu.evocelot.sample.kafka.KafkaTopics;
import hu.evocelot.sample.model.SampleEntity;
import hu.evocelot.sample.properties.KafkaProperties;

/**
 * Sample service class for creating sample entities.
 * 
 * @author mark.danisovszky
 */
@Service
public class CreateSampleEntityService {

    private SampleEntityConverter sampleEntityConverter;
    private SampleEntityWithIdConverter sampleEntityWithIdConverter;
    private SampleEntityAccessor sampleService;
    private KafkaProperties kafkaProperties;
    private ObjectMapper objectMapper;

    @Autowired(required = false)
    private KafkaMessageProducer kafkaMessageProducer;

    public CreateSampleEntityService(SampleEntityConverter sampleEntityConverter,
            SampleEntityWithIdConverter sampleEntityWithIdConverter,
            SampleEntityAccessor sampleService,
            KafkaProperties kafkaProperties,
            ObjectMapper objectMapper) {
        this.sampleEntityConverter = sampleEntityConverter;
        this.sampleEntityWithIdConverter = sampleEntityWithIdConverter;
        this.sampleService = sampleService;
        this.kafkaProperties = kafkaProperties;
        this.objectMapper = objectMapper;
    }

    /**
     * Creates a new sample entity.
     *
     * @param sampleEntityDto - Data for the new sample.
     * @return - with {@link ResponseEntity} that contains the
     *         {@link SampleEntityWithIdDto}.
     * @throws JsonProcessingException
     */
    public ResponseEntity<SampleEntityWithIdDto> createSampleEntity(SampleEntityDto sampleEntityDto)
            throws JsonProcessingException {
        // Create entity based on the DTO.
        SampleEntity sampleEntity = sampleEntityConverter.convert(sampleEntityDto);
        sampleEntity = sampleService.save(sampleEntity);

        if (kafkaProperties.getKafkaEnabled().equals("true")) {
            String json = objectMapper.writeValueAsString(sampleEntity);
            kafkaMessageProducer.sendMessage(KafkaTopics.SAMPLE_SAVED, json);
        }

        // Create the response DTO.
        SampleEntityWithIdDto response = sampleEntityWithIdConverter.convert(sampleEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
