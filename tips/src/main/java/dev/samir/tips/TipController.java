package dev.samir.tips;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<TipResponse> getRandomTip() {
		Tip tip = tipService.findRandomTip();
		return ResponseEntity.ok(new TipResponse(tip.getId(), tip.getMessage()));
	}
	
	/**
	 * Endpoint to get all tips.
	 * This method handles GET requests to the "/tip" URL.
	 * @return a list of all Tip objects from the database.
	 */
	@GetMapping("/tip")
	public ResponseEntity<List<TipResponse>> getAllTips() {
		List<TipResponse> tips = tipService.getAllTips().stream()
			.map(tip -> new TipResponse(tip.getId(), tip.getMessage()))
			.toList();
		return ResponseEntity.ok(tips);
	}
	
	/**
	 * Endpoint to get a tip by its ID.
	 * This method handles GET requests to the "/tip/{id}" URL.
	 * @param id the ID of the tip to retrieve
	 * @return the Tip object with the specified ID
	 * @throws TipNotFoundException 
	 */
	@GetMapping("/tip/{id}")
	public ResponseEntity<TipResponse> getTipById(@PathVariable Long id) throws TipNotFoundException {
		try {
			Tip tip = tipService.findById(id);
			return ResponseEntity.ok(new TipResponse(tip.getId(), tip.getMessage()));
		} catch (TipNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * Endpoint to insert a new tip.
	 * This method handles POST requests to the "/tip" URL.
	 * @param tip the Tip object to insert
	 * @return the inserted Tip object
	 */
	@PostMapping("/tip")
	public ResponseEntity<TipResponse> insert(@RequestBody TipRequest tip) {
		Tip insertedTip = tipService.insert(new Tip(tip.message()));
		return ResponseEntity.ok(new TipResponse(insertedTip.getId(), insertedTip.getMessage()));
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
	
	/**
	 * Endpoint to delete a tip by its ID.
	 * This method handles DELETE requests to the "/tip/{id}" URL.
	 * @param id the ID of the tip to delete
	 * @return a ResponseEntity indicating the result of the operation
	 */
	@DeleteMapping("/tip/{id}")
	public ResponseEntity<Void> deleteTip(@PathVariable Long id) {
		try {
			tipService.delete(id);
		} catch (TipNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
}
