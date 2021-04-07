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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    private AlbumTestData albumTestData;
    @MockBean
    private LogRepository logRepository;

    @BeforeEach
    public void setup() {
        albumTestData = new AlbumTestData(userRepository, artistRepository, albumRepository, logRepository);
        albumTestData.init();
        testAlbumId = albumTestData.getTestAlbumId();
    }

    @AfterEach
    public void cleanUp(){
        albumTestData.cleanUpData();
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                ALBUM_CONTROLLER_PREFIX + "/details/{id}", testAlbumId
        )).
                andExpect(status().isOk()).
                andExpect(view().name("details")).
                andExpect(model().attributeExists("currentAlbum"));
    }

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

        Assertions.assertEquals(3, albumRepository.count());
        //todo: may verify the created album properties
    }

}