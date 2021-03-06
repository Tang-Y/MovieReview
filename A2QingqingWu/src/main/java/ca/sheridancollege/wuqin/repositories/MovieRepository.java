/**
 * 
 * Qingqing Wu
 * 991500423
 * Feb 25th, 2021
 * Movie Repository for CRUD operations with database
 * 
 */

package ca.sheridancollege.wuqin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.wuqin.beans.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	// Retrieve movie list ordered by title
	public List<Movie> findByOrderByTitle();
	
	// Retrieve movie list ordered by year
	public List<Movie> findByOrderByYear();
	
	// Retrieve movie list ordered by rating from high to low
	public List<Movie> findByOrderByRateDesc();
	
	// Retrieve movie list by year before
	public List<Movie> findByYearBefore(Integer year);
	
	// Retrieve movie list by rate greater than
	public List<Movie> findByRateGreaterThan(Double rate);
	
	// Retrieve movie list that reviewList is null
	public List<Movie> findByReviewListIsNull();	

}
