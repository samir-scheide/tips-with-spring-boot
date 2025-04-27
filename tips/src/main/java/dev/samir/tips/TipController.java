package dev.samir.tips;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling requests related to tips.
 * This class is responsible for processing incoming HTTP requests
 * and returning the appropriate responses.	
 */
@RestController
@RequestMapping("/api")
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
	 * This method handles GET requests to the "/tip/random" URL.
	 * @return a random Tip object from the database.
	 */
	@GetMapping("/tip/random")
	public Tip getRandomTip() {
		return tipService.findRandomTip();
	}
	
	/**
	 * Endpoint to get all tips.
	 * This method handles GET requests to the "/tip" URL.
	 * @return a list of all Tip objects from the database.
	 */
	@GetMapping("/tip")
	public List<Tip> getAllTips() {
		return tipService.getAllTips();
	}
	
	/**
	 * Endpoint to get a tip by its ID.
	 * This method handles GET requests to the "/tip/{id}" URL.
	 * @param id the ID of the tip to retrieve
	 * @return the Tip object with the specified ID
	 * @throws TipNotFoundException 
	 */
	@GetMapping("/tip/{id}")
	public Tip getTipById(@PathVariable Long id) throws TipNotFoundException {
		return tipService.findById(id);
	}
	
	/**
	 * Endpoint to insert a new tip.
	 * This method handles POST requests to the "/tip" URL.
	 * @param tip the Tip object to insert
	 * @return the inserted Tip object
	 */
	@PostMapping("/tip")
	public Tip insert(@RequestBody Tip tip) {
		return tipService.insert(tip);
	}
	
	/**
	 * Endpoint to update an existing tip.
	 * This method handles PATCH requests to the "/tip" URL.
	 * @param tip the Tip object to update
	 * @return the updated Tip object
	 * @throws ResourceNotFoundException if the tip with the given ID does not exist
	 */
	@PatchMapping("/tip")
	public ResponseEntity<Tip> updateTip(@RequestBody Tip tip) {
		try {
			return ResponseEntity.ok(tipService.update(tip));
		} catch (TipNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
