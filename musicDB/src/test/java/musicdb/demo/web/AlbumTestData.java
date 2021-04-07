package musicdb.demo.web;

import musicdb.demo.models.entities.AlbumEntity;
import musicdb.demo.models.entities.ArtistEntity;
import musicdb.demo.models.entities.UserEntity;
import musicdb.demo.models.entities.enums.Genre;
import musicdb.demo.repositories.AlbumRepository;
import musicdb.demo.repositories.ArtistRepository;
import musicdb.demo.repositories.LogRepository;
import musicdb.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumTestData {

    private  long testAlbumId;

    private UserRepository userRepository;
    private ArtistRepository artistRepository;
    private AlbumRepository albumRepository;
    private LogRepository logRepository;

    public AlbumTestData(UserRepository userRepository, ArtistRepository artistRepository,
                         AlbumRepository albumRepository, LogRepository logRepository) {
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.logRepository = logRepository;
    }

    public void init() {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName("METALLICA");
        artistEntity.setCareerInformation("Some info about metallica");
        artistEntity = artistRepository.save(artistEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("pesho").setPassword("xyz").setFullName("petar petrov");
        userEntity = userRepository.save(userEntity);

        AlbumEntity albumEntity = new AlbumEntity();
        albumEntity.
                setName("JUSTICE FOR ALL").
                setImageUrl("https://upload.wikimedia.org/wikipedia/en/b/bd/Metallica_-_...And_Justice_for_All_cover.jpg").
                setVideoUrl("_fKAsvJrFes").
                setDescription("Sample description").
                setCopies(11).
                setPrice(BigDecimal.TEN).
                setReleaseDate(LocalDate.of(1988, 3, 3)).
                setGenre(Genre.METAL).
                setArtist(artistEntity).
                setUser(userEntity);
        albumEntity = albumRepository.save(albumEntity);
        AlbumEntity masterOfPuppets = new AlbumEntity();
        masterOfPuppets.
                setName("MASTER OF PUPPETS").
                setImageUrl("https://upload.wikimedia.org/wikipedia/en/b/bd/Metallica_-_...And_Justice_for_All_cover.jpg").
                setVideoUrl("_fKAsvJrFes").
                setDescription("Sample description").
                setCopies(111).
                setPrice(BigDecimal.TEN).
                setReleaseDate(LocalDate.of(1988, 3, 3)).
                setGenre(Genre.METAL).
                setArtist(artistEntity).
                setUser(userEntity);

        masterOfPuppets = albumRepository.save(masterOfPuppets);


        testAlbumId = albumEntity.getId();
    }

    public long getTestAlbumId() {
        return testAlbumId;
    }

    void cleanUpData(){
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        userRepository.deleteAll();
        logRepository.deleteAll();
    }
}
