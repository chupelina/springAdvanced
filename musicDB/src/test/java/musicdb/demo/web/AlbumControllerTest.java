package musicdb.demo.web;

import musicdb.demo.models.entities.AlbumEntity;
import musicdb.demo.models.entities.ArtistEntity;
import musicdb.demo.models.entities.UserEntity;
import musicdb.demo.models.entities.UserRoleEntity;
import musicdb.demo.models.entities.enums.Genre;
import musicdb.demo.models.entities.enums.UserRoleEnum;
import musicdb.demo.repositories.AlbumRepository;
import musicdb.demo.repositories.ArtistRepository;
import musicdb.demo.repositories.LogRepository;
import musicdb.demo.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AlbumControllerTest {

    private static final String ALBUM_CONTROLLER_PREFIX = "/albums";

    private long testAlbumId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @BeforeEach
    public void setup() {
        this.init();
    }

//    @Test
//    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
//    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get(
//                ALBUM_CONTROLLER_PREFIX + "/details/{id}", testAlbumId
//        )).
//                andExpect(status().isOk()).
//                andExpect(view().name("details")).
//                andExpect(model().attributeExists("currentAlbum"));
//    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void addAlbum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ALBUM_CONTROLLER_PREFIX + "/add")
                .param("name", "test album").
                        param("genre", Genre.METAL.name()).
                        param("imageUrl", "http://example.com/image.png").
                        param("videoUrl", "_fKAsvJrFes").
                        param("description", "Description test").
                        param("copies", "123333").
                        param("price", "10").
                        param("releaseDate", "2000-01-01").
                        param("artist", "METALLICA").
                        with(csrf())).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(2, albumRepository.count());
        //todo: may verify the created album properties
    }

    private void init() {
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
        testAlbumId = albumEntity.getId();
    }
}