package dev.samir.tips;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

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
	 * @return a random Tip object
	 */
	@Scheduled(fixedRate = 3, timeUnit = TimeUnit.SECONDS)
	public Tip findRandomTip() {
		Tip tip = tipRepository.findRandomTip();
		Logger.getGlobal().info("Tip #" + tip.getId() + " [" + tip.getMessage() + "]");
		return tip;
	}
	
}
