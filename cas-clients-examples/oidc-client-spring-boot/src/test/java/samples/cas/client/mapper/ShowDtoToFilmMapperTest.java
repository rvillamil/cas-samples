package samples.cas.client.mapper;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import samples.cas.client.model.Film;
import samples.cas.client.model.ShowDto;

/**
 * The Class FilmSearcherTest.
 *
 * @author XXXX
 */    
@RunWith(SpringRunner.class)
public class ShowDtoToFilmMapperTest {
		
	private ShowDtoToFilmMapper showDtoToFilmMapper 
				= new ShowDtoToFilmMapper();
	    
	@Test
	public void given_film_should_create_new_film() {
  	
		 ShowDto showDto = new ShowDto();
		 showDto.setTitle("title");
		 showDto.setImdbScore("9.5");
		 showDto.setYear("1976");
		 
		 
	     Film film = showDtoToFilmMapper.map(showDto);
	     
	     assertEquals(showDto.getTitle(), film.getTitle());
	     assertEquals(showDto.getImdbScore(), film.getImdbScore());
	     assertEquals(showDto.getYear(), film.getYear());
	}	
	
	@Test
	public void given_array_of_showDto_should_create_array_of_film() {
  	
		 ShowDto showDto1 = new ShowDto();
		 showDto1.setTitle("title");
		 showDto1.setImdbScore("9.5");
		 showDto1.setYear("1976");
		 
		 ShowDto showDto2 = new ShowDto();
		 showDto2.setTitle("title2");
		 showDto2.setImdbScore("8.5");
		 showDto2.setYear("1996");
		 
		 List<ShowDto> shows = new LinkedList<>();
		 shows.add(showDto1);
		 shows.add(showDto2);		 
		 List<Film> films = showDtoToFilmMapper.mapAll(shows);
		 
		 assertEquals(showDto1.getTitle(), films.get(0).getTitle());
		 assertEquals(showDto1.getImdbScore(), films.get(0).getImdbScore());
		 assertEquals(showDto1.getYear(), films.get(0).getYear());
		 
		 assertEquals(showDto2.getTitle(), films.get(1).getTitle());
		 assertEquals(showDto2.getImdbScore(), films.get(1).getImdbScore());
		 assertEquals(showDto2.getYear(), films.get(1).getYear());
	}
}



