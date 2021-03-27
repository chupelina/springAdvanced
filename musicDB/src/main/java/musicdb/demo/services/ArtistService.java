package musicdb.demo.services;


import musicdb.demo.models.entities.ArtistEntity;

import java.util.List;

public interface ArtistService {
    void addAllArtists();

    List<String> getAllByName();

    ArtistEntity findByName(String artist);
}
