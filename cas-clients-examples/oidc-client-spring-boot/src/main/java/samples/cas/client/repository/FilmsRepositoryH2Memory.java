package samples.cas.client.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import samples.cas.client.entity.ShowEntity;
import samples.cas.client.mapper.ShowDtoToFilmMapper;
import samples.cas.client.model.Film;

@Component
public class FilmsRepositoryH2Memory implements FilmsRepository {

	private FilmsRepositoryJPAHelper filmsRepositoryJPAHelper;

	@Autowired
	public FilmsRepositoryH2Memory(FilmsRepositoryJPAHelper filmsRepositoryJPAHelper,
								   ShowDtoToFilmMapper	showDtoToFilmMapper) {
		this.filmsRepositoryJPAHelper = filmsRepositoryJPAHelper;
	}

	@Override
	public Optional<Film> search(Long id) {
		Optional<ShowEntity> entityFilm = filmsRepositoryJPAHelper.findById(id);
		
		if ( entityFilm.isPresent()) {				
			return Optional.of (Film.builder()
						.imdbScore(entityFilm.get().getImdbScore())
						.year(entityFilm.get().getYear())
						.title(entityFilm.get().getTitle())
						.build());
		} else {						
				return Optional.empty();
		}		
	}

	@Override
	public void save(Film film) {

		ShowEntity entityFilm = new ShowEntity();
		entityFilm.setTitle(film.getTitle());
		entityFilm.setYear(film.getYear());
		entityFilm.setImdbScore(film.getImdbScore());

		filmsRepositoryJPAHelper.save(entityFilm);
	}

	@Override
	public List<Film> searchAll() {
		List<ShowEntity> showsEntity = filmsRepositoryJPAHelper.findAll();
		List<Film> list = new LinkedList<>();
		
		for (ShowEntity showEntity: showsEntity) {
			list.add(Film.builder()
				.imdbScore(showEntity.getImdbScore())
				.year(showEntity.getYear())
				.title(showEntity.getTitle())
				.build());
		}
		return list;
	}
}
