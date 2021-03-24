package musicdb.demo.services.implementations;

import musicdb.demo.models.entities.AlbumEntity;
import musicdb.demo.models.entities.UserEntity;
import musicdb.demo.models.serviceModels.AlbumServiceModel;
import musicdb.demo.repositories.AlbumRepository;
import musicdb.demo.repositories.UserRepository;
import musicdb.demo.services.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void createAlbum(AlbumServiceModel albumServiceModel) {
        AlbumEntity albumEntity = modelMapper.map(albumServiceModel, AlbumEntity.class);
        UserEntity user = userRepository.findByUsername(albumServiceModel.getUsername()).orElseThrow(()->
              new IllegalArgumentException("Creator for this album could not be found :( :( :("));
        albumRepository.save(albumEntity);
    }
}
