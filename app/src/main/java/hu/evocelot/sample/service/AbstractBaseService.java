package hu.evocelot.sample.service;

import java.math.BigInteger;
import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.evocelot.sample.model.AbstractIdentifiedAuditEntity;

/**
 * Base class for all of the services.
 * 
 * @author mark.danisovszky
 */
public abstract class AbstractBaseService {

    /**
     * Defines the JpaRepository to use when managing the entities.
     * 
     * @return - the relevant JpaRepository.
     */
    protected abstract JpaRepository getRepository();

    /**
     * Updates or creates the entity. Manages the audit fields.
     * 
     * @param entity - the entity to save or update.
     * @return - with the updated entity.
     */
    public AbstractIdentifiedAuditEntity save(AbstractIdentifiedAuditEntity entity) {
        entity.setVersion(entity.getVersion().add(BigInteger.valueOf(1)));
        entity.setModDate(OffsetDateTime.now());
        entity.setModUser("unknown"); // TODO: Get current user.

        return (AbstractIdentifiedAuditEntity) getRepository().save(entity);
    }
}
