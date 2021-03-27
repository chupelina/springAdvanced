package musicdb.demo.services;

import musicdb.demo.models.entities.AlbumEntity;
import musicdb.demo.models.serviceModels.AlbumServiceModel;
import musicdb.demo.models.viewModels.AlbumViewModel;

public interface AlbumService {
    void createAlbum(AlbumServiceModel albumServiceModel);

    AlbumViewModel getAlbumById(Long id);

    AlbumEntity findEntityById(Long albumId);
}
