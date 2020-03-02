package samples.cas.client.service;

import java.util.List;
import java.util.Optional;

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
public class FilmSearcher  {
	
	/** The films repository. */
	private FilmsRepository filmsRepository;
	
	/**
	 * Instantiates a new film searcher.
	 *
	 * @param filmsRepository the films repository
	 */
	@Autowired	
	public FilmSearcher(FilmsRepository filmsRepository){
		this.filmsRepository = filmsRepository;
    }
	
	/**
	 * Search.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<Film> search(Long id) {    		
    	log.info("Searching film with id '{}'..", id);
    	return filmsRepository.search(id); 
    }
    
    public List<Film> searchAll() {    		
    	log.info("Searching all films");
    	return filmsRepository.searchAll(); 
	}
}





