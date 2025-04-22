package dev.samir.tips;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling requests related to tips.
 * This class is responsible for processing incoming HTTP requests
 * and returning the appropriate responses.	
 */
@RestController
public class TipController {

	/**
	 * Service class for managing tips.
	 * This class is used to interact with the database and perform
	 * operations related to tips.
	 */
	private TipService tipService;
	
	/**
	 * Constructor for TipController.
	 * @param tipService
	 */
	public TipController(TipService tipService) {
		this.tipService = tipService;
	}
	
	/**
	 * Endpoint to get a random tip.
	 * This method handles GET requests to the "/tip" URL.
	 * @return a random Tip object from the database.
	 */
	@GetMapping("/tip")
	public Tip getTip() {
		return tipService.findRandomTip();
	}
	
}
