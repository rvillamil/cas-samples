package samples.cas.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import samples.cas.client.entity.ShowEntity;

@Component
public interface FilmsRepositoryJPAHelper extends JpaRepository<ShowEntity, Long>  {

}
