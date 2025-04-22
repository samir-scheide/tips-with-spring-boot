package dev.samir.tips;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Service class for managing tips.
 * This class is responsible for business logic related to tips.
 * It interacts with the TipRepository to perform database operations.
 */
@Service
public class TipService {

	/**
	 * Repository for accessing tip data.
	 * This class is used to interact with the database and perform
	 * operations related to tips.
	 */
	private TipRepository tipRepository;
	
	/**
	 * Constructor for TipService.
	 * This constructor is used to inject the TipRepository dependency.
	 * @param tipRepository the TipRepository instance
	 */
	public TipService(TipRepository tipRepository) {
		this.tipRepository = tipRepository;
	}
	
	/**
	 * Method to find a random tip.
	 * This method uses the TipRepository to retrieve a random tip from the database.
	 * It is scheduled to run every 3 seconds.
	 * @return a random Tip object
	 */
	@Scheduled(fixedRate = 3, timeUnit = TimeUnit.SECONDS)
	public Tip findRandomTip() {
		Tip tip = tipRepository.findRandomTip();
		Logger.getGlobal().info("Tip #" + tip.getId() + " [" + tip.getMessage() + "]");
		return tip;
	}
	
	/**
	 * Get all tips from the database.
	 * This method retrieves all tips from the database using the TipRepository.
	 * @return a list of Tip objects
	 */
	public List<Tip> getAllTips() {
		return StreamSupport
			.stream(tipRepository.findAll().spliterator(), true)
			.collect(Collectors.toList());
	}
	
	/**
	 * Get a tip by its ID.
	 * This method retrieves a tip from the database using its ID.
	 * @param id the ID of the tip to retrieve
	 * @return the Tip object with the specified ID, or null if not found
	 */
	public Tip getTipById(Long id) {
		return tipRepository.findById(id).orElse(null);
	}
	
	/**
	 * This method saves a new Tip object to the database, or updates an existing one.
	 * @param tip the Tip object to save or update
	 * @return the saved or updated Tip object
	 */
	public Tip upsertTip(Tip tip) {
		return tipRepository.save(tip);
	}
	
}
