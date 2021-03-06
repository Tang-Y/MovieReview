/**
 * 
 * Qingqing Wu
 * 991500423
 * Feb 25th, 2021
 * Movie controller controls all form actions and movie list
 * 
 */


package ca.sheridancollege.wuqin.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.wuqin.beans.Movie;
import ca.sheridancollege.wuqin.beans.Review;
import ca.sheridancollege.wuqin.repositories.MovieRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MovieController {
	
	private MovieRepository movieRepository;
	
	@GetMapping("/")
	public String index(Model model) {
		
		List<Movie> movieList = movieRepository.findAll();
		
		model.addAttribute("movieList", movieList);
		
		return "index";
	}

	@GetMapping("/backToHomePage")
	public String backToHomePage(Model model) {
		
		List<Movie> movieList = movieRepository.findAll();
		
		model.addAttribute("movieList", movieList);
		
		return "index";
	}
	
	@GetMapping("/addMovie")
	public String addMovie(Model model) {
		
		// Create a blank Movie instance to the Model as an attribute
		model.addAttribute(new Movie());
		
		return "addMovie";
	}
	
	// Add movie and continue add review to the movie, no matter which movie user wanna add review to
	@PostMapping("/addMovie")
	public String addMovie(Model model, @ModelAttribute Movie movie) {
		
		// Retrieve kid obj and save it to the repository
		movieRepository.save(movie);
		
		List<Movie> movieList = movieRepository.findAll();
		
		model.addAttribute(new Review());
		model.addAttribute("movieList", movieList);
		
		return "addReview";
	}
	
	// Retrieve movie list ordered by title
	@GetMapping("/orderByTitle")
	public String findMovieOrderByTitle(Model model) {
		
		List<Movie> movieList = movieRepository.findByOrderByTitle();
		
		model.addAttribute("movieList", movieList);
		
		return "index";
	}
	
	// Retrieve movie list ordered by year
	@GetMapping("/orderByYear")
	public String findMovieOrderByYear(Model model) {
		
		List<Movie> movieList = movieRepository.findByOrderByYear();
		
		model.addAttribute("movieList", movieList);
		
		return "index";
	}
	
	// Retrieve movie list ordered by rate from high to low
	@GetMapping("/orderByRate")
	public String findMovieOrderByRate(Model model) {
		
		List<Movie> movieList = movieRepository.findByOrderByRateDesc();
		
		model.addAttribute("movieList", movieList);
		
		return "index";
	}
	
	// Retrieve movie list by rate greater than
	@PostMapping("/findByRateGreaterThan")
	public String findByRateGreaterThan(Model model, @RequestParam Double rate) {

		
		List<Movie> movieList = movieRepository.findByRateGreaterThan(rate);
		
		model.addAttribute(new Review());
		model.addAttribute("movieList", movieList);
		
		return "index";
	}
	
	// Retrieve movie list by year before
	@PostMapping("/findByYearBefore")
	public String findByYearBefore(Model model, @RequestParam Integer year) {

		
		List<Movie> movieList = movieRepository.findByYearBefore(year);
		
		model.addAttribute(new Review());
		model.addAttribute("movieList", movieList);
		
		return "index";
	}
	
	// Retrieve movie list by review is null
	@PostMapping("/findByReviewListIsNull")
	public String findByReviewListIsNull(Model model) {

		
		List<Movie> movieList = movieRepository.findByReviewListIsNull();
		
		model.addAttribute(new Review());
		model.addAttribute("movieList", movieList);
		
		return "index";
	}
	
	
	
	
}
