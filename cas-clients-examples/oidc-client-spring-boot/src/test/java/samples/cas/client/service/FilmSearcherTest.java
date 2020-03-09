package samples.cas.client.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class FilmSearcherTest {
	
	@MockBean
    private FilmsRepository 			filmsRepository;
		
	/**
	 * Given existing id when search get one film.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void given_existing_id_whenSearch_getOneFilm() throws Exception {
		// Given
		Long batmanId = 1L;
						
		// When
		Film batmanFilm = Film.builder()    			
    			.title("Batman")
    			.year("2019")
    			.imdbScore("7.5")
    			.build();
		when (filmsRepository.search(batmanId)).thenReturn(Optional.of(batmanFilm));
		
		FilmSearcher filmSearcher = new FilmSearcher(filmsRepository);
		Optional<Film> filmFound = filmSearcher.search(batmanId);
		// Then
		assertEquals (Optional.of(batmanFilm), filmFound);		
	}
	
	/**
	 * Given not existing id when search get empty.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void given_not_existing_id_whenSearch_getEmpty() throws Exception {
		// Given
		Long nonExistingFilmId = 1L;				
		
		// When
		when (filmsRepository.search(nonExistingFilmId)).thenReturn(Optional.empty());
		FilmSearcher filmSearcher = new FilmSearcher(filmsRepository);
		Optional<Film> filmNotFound = filmSearcher.search(nonExistingFilmId);
		
		// Then
		assertEquals ( Optional.empty(), filmNotFound);	
	}		
}



