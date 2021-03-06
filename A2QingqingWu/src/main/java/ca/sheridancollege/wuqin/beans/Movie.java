/**
 * 
 * Qingqing Wu
 * 991500423
 * Feb 25th, 2021
 * Movie class that declares all properties of movie and has the table relationship define
 * 
 */

package ca.sheridancollege.wuqin.beans;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Positive
	private Long id;
	private String title;
	private Integer year;

	private Double rate;
	private String streaming;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="MOVIE_REVIEW", joinColumns=@JoinColumn(name="MOVIE_ID"), inverseJoinColumns=@JoinColumn(name="REVIEW_ID"))
	private List<Review> reviewList;

}
