package hu.evocelot.sample.action;

import java.time.OffsetDateTime;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.EntityResponse;

import hu.evocelot.sample.controller.SampleController;
import hu.evocelot.sample.dto.SampleDto;
import hu.evocelot.sample.model.SampleEntity;
import hu.evocelot.sample.service.SampleService;

/**
 * Sample action class for creating sample entities.
 * 
 * @author mark.danisovszky
 */
@Component
public class SampleAction {

    private static final Logger LOG = LogManager.getLogger(SampleController.class);

    @Autowired
    private SampleService sampleService;

    /**
     * Creates a {@link SampleEntity}.
     * 
     * @return - with the "created" string.
     */
    public ResponseEntity<SampleDto> sampleAction() {
        LOG.log(Level.INFO, "sample action triggered");

        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setSampleDate(OffsetDateTime.now().plusDays(2));
        sampleEntity.setSampleValue("Sample");

        sampleService.save(sampleEntity);

        SampleDto sampleDto = new SampleDto(sampleEntity);

        return new ResponseEntity<>(sampleDto, HttpStatus.OK);
    }
}
