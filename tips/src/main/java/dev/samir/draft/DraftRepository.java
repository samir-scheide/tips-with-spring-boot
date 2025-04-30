package dev.samir.draft;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for managing Draft entities.
 * This interface extends the Spring Data Repository interface,
 * providing CRUD operations for Draft entities.
 */
public interface DraftRepository extends CrudRepository<Draft, String> {}
