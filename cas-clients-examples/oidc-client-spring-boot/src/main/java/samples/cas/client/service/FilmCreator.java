package samples.cas.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import samples.cas.client.model.Film;
import samples.cas.client.repository.FilmsRepository;

/**
 * The Class FilmSearcher.
 *
 * @author XXXX
 */
@Service

/** The Constant log. */
@Log4j2
public class FilmCreator  {
	
	/** The films repository. */
	private FilmsRepository filmsRepository;
	
	/**
	 * Instantiates a new film searcher.
	 *
	 * @param filmsRepository the films repository
	 */
	@Autowired	
	public FilmCreator(FilmsRepository filmsRepository){
		this.filmsRepository = filmsRepository;
    }

	public void createFilm(Film film) {
		log.info("Creating film '{}'", film.toString());
		filmsRepository.save (film);
	}
}





