package musicdb.demo.repositories;

import musicdb.demo.models.entities.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository  extends JpaRepository<ArticleEntity, Long> {
    Optional<ArticleEntity> findTopByOrderByReleaseDateDesc();
}
