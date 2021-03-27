package musicdb.demo.services.implementations;

import musicdb.demo.models.entities.AlbumEntity;
import musicdb.demo.models.entities.UserEntity;
import musicdb.demo.models.serviceModels.AlbumServiceModel;
import musicdb.demo.models.viewModels.AlbumViewModel;
import musicdb.demo.repositories.AlbumRepository;
import musicdb.demo.repositories.UserRepository;
import musicdb.demo.services.AlbumService;
import musicdb.demo.services.ArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final ArtistService artistService;
    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public AlbumServiceImpl(ArtistService artistService, AlbumRepository albumRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.artistService = artistService;
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void createAlbum(AlbumServiceModel albumServiceModel) {
        AlbumEntity albumEntity = modelMapper.map(albumServiceModel, AlbumEntity.class);
        UserEntity user = userRepository.findByUsername(albumServiceModel.getUsername()).orElseThrow(()->
              new IllegalArgumentException("Creator for this album could not be found :( :( :("));
        albumEntity.setUser(user);
        albumEntity.setArtist(artistService.findByName(albumServiceModel.getArtist()));
        albumRepository.save(albumEntity);
    }

    @Override
    public AlbumViewModel getAlbumById(Long id) {
        AlbumEntity current = albumRepository.findById(id).orElseThrow();
        AlbumViewModel map = modelMapper.map(current, AlbumViewModel.class);
        map.setArtist(current.getArtist().getName());
        return map;
    }

    @Override
    public AlbumEntity findEntityById(Long albumId) {
        return albumRepository.findById(albumId).orElseThrow();
    }
}
