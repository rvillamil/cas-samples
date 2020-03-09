package samples.cas.client.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import samples.cas.client.api.ShowsApi;
import samples.cas.client.mapper.FilmToShowDtoMapper;
import samples.cas.client.mapper.ShowDtoToFilmMapper;
import samples.cas.client.model.Film;
import samples.cas.client.model.ShowDto;
import samples.cas.client.service.FilmCreator;
import samples.cas.client.service.FilmSearcher;

/**
 * The Class ShowController.
 *
 * @author XXXX
 */

/** The Constant log. */
@Log4j2
@RestController
public class ShowController implements ShowsApi {

	/** The environment descripion. */
	@Value("${environment.description}")
	private String environmentDescripion;
	
	private FilmSearcher 			filmSearcher;
	private FilmCreator 			filmCreator;	
	private FilmToShowDtoMapper 	filmToShowDtoMapper;
	private ShowDtoToFilmMapper 	showDtoToFilmMapper;	
	
	/**
	 * Instantiates a new show controller.
	 *
	 * @param filmSearcher the film searcher
	 */
	@Autowired
    public ShowController(FilmSearcher 		 	filmSearcher, 
    					  FilmCreator 			filmCreator,
    					  FilmToShowDtoMapper 	filmToShowDtoMapper,
    					  ShowDtoToFilmMapper   showDtoToFilmMapper) {
		
        this.filmSearcher 		 = filmSearcher;
        this.filmCreator 		 = filmCreator;
        this.filmToShowDtoMapper = filmToShowDtoMapper;
        this.showDtoToFilmMapper = showDtoToFilmMapper;       
    }

    /**
	 * Gets the show by id.
	 *
	 * @param id the id
	 * @return the show by id
	 */
    @Override
    public ResponseEntity<ShowDto> getShowById(@PathVariable("id") Long id) {    	
     	log.info("GET film with id '{}'", id);   	
    
    	final Optional<Film> foundFilm = filmSearcher.search(id);
    	
    	if (!foundFilm.isPresent()) {			
			log.info(String.format("ShowEntity with id '%d' not found.", id));
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

    	return new ResponseEntity<>(filmToShowDtoMapper.map(foundFilm.get()), 
    								HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Void> addShow(@RequestBody ShowDto showDto) {
    	log.info("POST show '{}'", showDto.toString());
    	
    	filmCreator.createFilm(showDtoToFilmMapper.map(showDto));
    	
    	
    	return new ResponseEntity<>(HttpStatus.CREATED);        
    }
    
    
    @Override
    public ResponseEntity<List<ShowDto>> getAllShows() {
    	log.info("Environment description: '{}'", environmentDescripion);
    	log.info("GET shows");
    	List<ShowDto> shows = filmToShowDtoMapper.mapAll(filmSearcher.searchAll());
    	
    	return new ResponseEntity<>(shows,HttpStatus.OK);       
    }
}
