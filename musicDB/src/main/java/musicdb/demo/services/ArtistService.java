package musicdb.demo.services;


import musicdb.demo.models.entities.ArtistEntity;
import musicdb.demo.models.viewModels.ArtistViewModel;

import java.util.List;

public interface ArtistService {
    void addAllArtists();

    List<ArtistViewModel> getAllByName();
}
