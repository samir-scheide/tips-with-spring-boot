package dev.samir.tips;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for managing Tip entities.
 * This interface extends CrudRepository to provide CRUD operations
 * for the Tip entity.
 */
public interface TipRepository extends CrudRepository<Tip, Long> {
	
	/**
	 * Method to find a random tip from the database.
	 * This method uses a native SQL query with "random()" funcion to select a random tip.
	 * @return a random Tip object from the database.
	 */
	@Query(value = "select * from tb01_tips order by random() limit 1", nativeQuery = true)
	public Tip findRandomTip();
	
	/**
	 * Find all tips associated with a given UUID.
	 * @param uuid the session identifier
	 * @return a list of tips associated with the given UUID
	 */
	@Query(value = "select * from tb01_tips where uuid = ?1", nativeQuery = true)
	public List<Tip> findAllByUuid(String uuid); 
	
}
