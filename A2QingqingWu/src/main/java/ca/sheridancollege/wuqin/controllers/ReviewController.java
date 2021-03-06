/**
 * 
 * Qingqing Wu
 * 991500423
 * Feb 25th, 2021
 * Review controller controls all form actions
 * 
 */

package ca.sheridancollege.wuqin.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.wuqin.beans.Movie;
import ca.sheridancollege.wuqin.beans.Review;
import ca.sheridancollege.wuqin.repositories.MovieRepository;
import ca.sheridancollege.wuqin.repositories.ReviewRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ReviewController {
	
	private ReviewRepository reviewRepository;
	private MovieRepository movieRepository;
	
	// Retrieve review list of a specific movie
	@PostMapping("/updateReviewsWithMovie")
	public String updateReviewsWithMovie(Model model, @RequestParam String title) {

		Movie movie = reviewRepository.retrieveIgnoringCase(title);
		List<Review> reviewList = movie.getReviewList();
		
		List<Movie> movieList = reviewRepository.retrieveAllMovies();
		model.addAttribute("movie", movie);
		model.addAttribute("movieList", movieList);
		model.addAttribute("reviewList", reviewList);
		
		return "index";
	}
	
	@GetMapping("/addReview")
	public String addReview(Model model) {

		List<Movie> movieList = reviewRepository.retrieveAllMovies();

		model.addAttribute(new Review());
		model.addAttribute("movieList", movieList);
		
		return "addReview";
	}
	
	// Add review to an specific movie 
	@PostMapping("/addReview")
	public String addReview(Model model, 
			@RequestParam(value="movieId") String movieId,
			@RequestParam String content,
			@RequestParam String author,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

		// Store passing movie ID
		Long id = Long.parseLong(movieId);
		
		// Retrieve a specific movie obj
		Movie movie = movieRepository.findById(id).get();
		
		// Create new review obj and save data into it
		Review review = new Review();
		review.setContent(content);
		review.setAuthor(author);
		review.setDate(date);review = reviewRepository.save(review);
		
		
		// add review to movie reviewList
		movie.getReviewList().add(review);
		movieRepository.save(movie);
		
		List<Movie> movieList = movieRepository.findAll();

		model.addAttribute(new Review());
		model.addAttribute("movieList", movieList);
		
		return "index";
	}
	
	// Find review list by author and also display that which movies the author wrote to
	@PostMapping("/findByAuthor")
	public String findByAuthor(Model model, @RequestParam String author) {

		// Key value set for store value
		HashMap<String, Review> movieReview = new HashMap<String, Review>();
		
		List<Long> IdList = reviewRepository.retrieveIdsByAuthor(author);
		
		Review review = new Review();
		
		// Iterate through what are the movie ids that user wrote to and 
		// retrieve the review obj by that specific id
		// and finally, add ralated movie title and review obj to the hashmap
		for(int a = 0; a < IdList.size(); a++) {
			review = reviewRepository.retrieveReviewsByMovieId(IdList.get(a), author);
			movieReview.put(reviewRepository.retrieveTitleById(IdList.get(a)), review);
		}
		
		List<Movie> movieList = reviewRepository.retrieveAllMovies();
		
		model.addAttribute(new Movie());
		model.addAttribute("movieList", movieList);
		model.addAttribute("movieReview", movieReview);
		
		return "index";
	}


}
