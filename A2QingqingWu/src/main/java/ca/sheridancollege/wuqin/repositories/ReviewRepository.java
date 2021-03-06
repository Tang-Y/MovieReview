/**
 * 
 * Qingqing Wu
 * 991500423
 * Feb 25th, 2021
 * Review Repository for CRUD operations with database
 * 
 */

package ca.sheridancollege.wuqin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.sheridancollege.wuqin.beans.Movie;
import ca.sheridancollege.wuqin.beans.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	// Query to retrieve review model list by specific movie id and review author
	@Query("SELECT r FROM Movie m JOIN m.reviewList r WHERE m.id = :id AND LOWER(r.author) = LOWER(:author)")
	public Review retrieveReviewsByMovieId(@Param("id") Long Id, @Param("author") String author);
	
	// Query to retrieve all movie ids by specific review author
	@Query("SELECT m.id FROM Movie m JOIN m.reviewList r WHERE LOWER(r.author) = LOWER(:author)")
	public List<Long> retrieveIdsByAuthor(@Param("author") String author);
	
	// Query to retrieve movie title list by specific movie id 
	@Query("SELECT m.title FROM Movie m JOIN m.reviewList r WHERE m.id = :id")
	public String retrieveTitleById(@Param("id") Long Id);
	
	// Query to retrieve movie model list by specific title
	@Query("SELECT m FROM Movie m JOIN m.reviewList r WHERE LOWER(m.title) = LOWER(:title)")
	public Movie retrieveIgnoringCase(@Param("title") String name);
	
	// Query to retrieve all movies
	@Query("SELECT m FROM Movie m")
	public List<Movie> retrieveAllMovies();
	
	

	
}
