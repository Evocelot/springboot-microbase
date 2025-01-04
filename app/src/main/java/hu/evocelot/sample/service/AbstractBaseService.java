package hu.evocelot.sample.service;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.evocelot.sample.model.AbstractIdentifiedAuditEntity;

/**
 * Base class for all of the services.
 * 
 * @author mark.danisovszky
 */
public abstract class AbstractBaseService<T extends AbstractIdentifiedAuditEntity> {

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
    public T save(T entity) {
        entity.setVersion(entity.getVersion().add(BigInteger.valueOf(1)));
        entity.setModDate(OffsetDateTime.now());
        entity.setModUser("unknown"); // TODO: Get current user.

        return (T) getRepository().save(entity);
    }

    /**
     * Finds the {@link T} based on the id.
     * 
     * @param id - the id of the entity to find.
     * @return - with {@link Optional<AbstractIdentifiedAuditEntity>}.
     */
    public Optional<T> findById(String id) {
        return getRepository().findById(id);
    }

    /**
     * Deletes the {@link T} based on the id.
     * 
     * @param id - the id of the entity to delete.
     */
    public void deleteById(String id) {
        getRepository().delete(id);
    }

    /**
     * Deletes the {@link T} entity.
     * 
     * @param entity - the entity to delete.
     */
    public void delete(T entity) {
        getRepository().delete(entity);
    }
}
