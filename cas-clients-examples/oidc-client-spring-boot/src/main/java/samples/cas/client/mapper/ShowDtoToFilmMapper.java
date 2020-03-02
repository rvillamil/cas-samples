package samples.cas.client.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import samples.cas.client.model.Film;
import samples.cas.client.model.ShowDto;

@Component
public class ShowDtoToFilmMapper {
	
	public Film map(ShowDto showDto)  {	
		
		return Film.builder()
				.imdbScore(showDto.getImdbScore())
				.title(showDto.getTitle())
				.year(showDto.getYear())
				.build();
	}

	public List<Film> mapAll (List<ShowDto> shows) {
		List<Film>  films = new LinkedList<>();		
		for (ShowDto show: shows) {
			films.add(this.map(show));
		}
		return films;	
	}
}
