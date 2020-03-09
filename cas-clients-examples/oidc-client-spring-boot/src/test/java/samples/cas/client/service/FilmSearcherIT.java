package samples.cas.client.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import samples.cas.client.model.Film;
import samples.cas.client.repository.FilmsRepository;
import samples.cas.client.service.FilmSearcher;

/**
 * The Class FilmSearcherTest.
 *
 * @author XXXX
 */    
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmSearcherIT {
	
	@Autowired
    private FilmsRepository 			filmsRepository;
		
	/**
	 * Given existing id when search get one film.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void given_existing_id_whenSearch_getOneFilm() throws Exception {
		// Given
		Long id = 1L;
						
		// When		
		FilmSearcher filmSearcher = new FilmSearcher(filmsRepository);
		Optional<Film> filmFound = filmSearcher.search(id);
		
		// Then
		Film filmSearched = Film.builder()    			
    			.title("Superman")
    			.year("1976")
    			.imdbScore("8.4")
    			.build();
				
		assertEquals (Optional.of(filmSearched), filmFound);		
	}
	
	@Test
	public void given_not_existing_id_whenSearch_getEmptyFilm() throws Exception {
		// Given
		Long id = 1254300L;
						
		// When		
		FilmSearcher filmSearcher = new FilmSearcher(filmsRepository);
		Optional<Film> filmFound = filmSearcher.search(id);
		
		// Then	
				
		assertEquals (Optional.empty(), filmFound);		
	}
}



