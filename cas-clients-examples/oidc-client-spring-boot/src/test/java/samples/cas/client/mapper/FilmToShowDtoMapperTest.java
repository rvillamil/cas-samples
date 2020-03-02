package samples.cas.client.mapper;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import samples.cas.client.mapper.FilmToShowDtoMapper;
import samples.cas.client.model.Film;
import samples.cas.client.model.ShowDto;

/**
 * The Class FilmSearcherTest.
 *
 * @author XXXX
 */    
@RunWith(SpringRunner.class)
public class FilmToShowDtoMapperTest {
		
	private FilmToShowDtoMapper filmToShowDtoMapper 
				= new FilmToShowDtoMapper();
	    
	@Test
	public void given_film_should_create_showdto() {
  	
		 Film film = Film.builder()
	        		.title("Batman Forever!")
	        		.year("2019")
	        		.imdbScore("3.4")
	        		.build();
		 		 
		 
	     ShowDto showDto = filmToShowDtoMapper.map(film);
	     
	     assertEquals(showDto.getTitle(), film.getTitle());
	     assertEquals(showDto.getImdbScore(), film.getImdbScore());
	     assertEquals(showDto.getYear(), film.getYear());
	}	
	
	@Test
	public void given_array_of_films_should_create_array_of_showDTO() {
		// Givem
		 Film film1 = Film.builder()
	        		.title("Batman Forever!")
	        		.year("2019")
	        		.imdbScore("3.4")
	        		.build();
		 
		 Film film2 = Film.builder()
	        		.title("Superman")
	        		.year("1959")
	        		.imdbScore("8.4")
	        		.build();
		 List<Film> films = new LinkedList<>();
		 films.add(film1); films.add(film2);
		 
		 // when
		 List<ShowDto> shows = filmToShowDtoMapper.mapAll(films);
		 
		 // Then
		 assertEquals(film1.getTitle(), shows.get(0).getTitle());
	     assertEquals(film1.getImdbScore(), shows.get(0).getImdbScore());
	     assertEquals(film1.getYear(), shows.get(0).getYear());
	     
	     assertEquals(film2.getTitle(), shows.get(1).getTitle());
	     assertEquals(film2.getImdbScore(), shows.get(1).getImdbScore());
	     assertEquals(film2.getYear(), shows.get(1).getYear());
	}
}



