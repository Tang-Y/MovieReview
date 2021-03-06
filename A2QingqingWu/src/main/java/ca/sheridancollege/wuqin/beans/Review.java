/**
 * 
 * Qingqing Wu
 * 991500423
 * Feb 25th, 2021
 * Review class that declares all properties of review 
 * 
 */

package ca.sheridancollege.wuqin.beans;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Positive
	private Long id;
	private String content;
	private String author;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
}
