package dev.samir.draft;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Service for Redis operations.
 * This class is used to interact with Redis for caching or other purposes.
 * Currently being used to save draft tips
 */
@Service
public class DraftService {

	/**
	 * Repository for accessing draft data.
	 */
	private DraftRepository draftRepository;
	
	/**
	 * Constructor for DraftService.
	 * @param draftRepository the draft repository
	 */
	public DraftService(DraftRepository draftRepository) {
		this.draftRepository = draftRepository;
	}
	
	/**
	 * Saves a draft to the repository.
	 * @param draft the draft to save
	 * @return the saved draft
	 */
	@CacheEvict(key = "#draft.id")
	public Draft upsert(Draft draft) {
		return draftRepository.save(draft);
	} 
	
	/**
	 * Gets all drafts from the repository for a given UUID.
	 * @param uuid draft identifier
	 * @return list of drafts
	 */
	@Cacheable(key = "#draft.id")
	public List<Draft> getAll(String uuid) {
		return StreamSupport.stream(draftRepository.findAll().spliterator(), true)
			.filter(draft -> draft.uuid().equals(uuid))
			.toList();
	}
	
}
