package samples.cas.client.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Film {
	
	/** The title. */
	private final String title;
	
	/** The year. */
	private final String year;
	
	private final String imdbScore;
}
