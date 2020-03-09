package samples.cas.client.repository;

import java.util.List;
import java.util.Optional;

import samples.cas.client.model.Film;

/**
 * The Interface FilmsRepository.
 */
public interface FilmsRepository {
	
	Optional<Film> search(Long id);
		
    void save (Film film);
    
    List<Film> searchAll();
}
