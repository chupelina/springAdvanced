package musicdb.demo.web;

import musicdb.demo.models.entities.enums.Genre;
import musicdb.demo.repositories.AlbumRepository;
import musicdb.demo.repositories.ArtistRepository;
import musicdb.demo.repositories.LogRepository;
import musicdb.demo.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
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

import javax.servlet.http.PushBuilder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AlbumRestControllerTest {

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
    public void setUp() {
        albumTestData = new AlbumTestData(userRepository, artistRepository, albumRepository, logRepository);
        albumTestData.init();
    }

    @AfterEach
    public void cleanUp() {
        albumTestData.cleanUpData();
    }
    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void testFetchAlbum() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/albums/api"))
              .andExpect(status().isOk())
              .andExpect(jsonPath("[0].name").value("JUSTICE FOR ALL")).
        andExpect(jsonPath("[0].copies").value(11)).
                andExpect(jsonPath("[0].genre").value(Genre.METAL.name())).
                andExpect(jsonPath("[1].name").value("MASTER OF PUPPETS")).
                andExpect(jsonPath("[1].copies").value(111)).
                andExpect(jsonPath("[1].genre").value(Genre.METAL.name()));
    }
}
