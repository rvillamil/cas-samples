package samples.cas.client.service;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import samples.cas.client.model.Film;
import samples.cas.client.repository.FilmsRepository;
import samples.cas.client.service.FilmCreator;

/**
 * The Class FilmSearcherTest.
 *
 * @author XXXX
 */    
@RunWith(SpringRunner.class)
public class FilmCreatorTest {
		
	/** The service. */
    @MockBean
    private FilmsRepository 			filmsRepository;

    
	@Test
	public void given_film_should_create_new_film() {
  	    // Given		
		Film film = Film.builder()    			
    			.title("First film")
    			.year("2000")
    			.imdbScore("4.5")
    			.build();    		
		
		// When
		FilmCreator filmCreator = new FilmCreator(filmsRepository);
		filmCreator.createFilm(film);		
		
		// Then
		verify(filmsRepository).save(film);
	}	
}



