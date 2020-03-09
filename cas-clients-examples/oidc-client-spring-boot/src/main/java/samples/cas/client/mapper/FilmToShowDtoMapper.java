package samples.cas.client.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import samples.cas.client.model.Film;
import samples.cas.client.model.ShowDto;

@Component
public class FilmToShowDtoMapper {
	
	public ShowDto map(Film film)  {	
		
		ShowDto showDto = new ShowDto();
    	showDto.setTitle(film.getTitle());
    	showDto.setYear(film.getYear());
    	showDto.setImdbScore(film.getImdbScore());    	
		return showDto;
	}

	public List<ShowDto> mapAll(List<Film> films) {
		List<ShowDto>  shows = new LinkedList<>();		
		for (Film film: films) {
			shows.add(this.map(film));
		}
		return shows;
	}
}
