package musicdb.demo;

import musicdb.demo.services.ArtistService;
import musicdb.demo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MusicDBInit implements CommandLineRunner {
    private final UserService userService;
    private final ArtistService artistService;

    public MusicDBInit(UserService userService, ArtistService artistService) {
        this.userService = userService;
        this.artistService = artistService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
        artistService.addAllArtists();
    }
}
