package samples.cas.client.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="SHOW_ENTITY")
public class ShowEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
	private String title;
	@Column
	private String year;
	@Column
	private String imdbScore;
	
	
	
	/**
	 * Instantiates a new account.
	 */
	public ShowEntity() { // jpa only
	}
}
