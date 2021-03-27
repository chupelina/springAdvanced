package musicdb.demo.repositories;

import musicdb.demo.models.entities.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

    @Query("select a.name from ArtistEntity as a ")
    List<String> getAllNames();

    Optional<ArtistEntity> findFirstByName(String name);
}