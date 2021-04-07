package musicdb.demo.services.implementations;

import musicdb.demo.models.entities.AlbumEntity;
import musicdb.demo.models.entities.LogEntity;
import musicdb.demo.models.entities.UserEntity;
import musicdb.demo.models.serviceModels.LogServiceModel;
import musicdb.demo.repositories.LogRepository;
import musicdb.demo.services.AlbumService;
import musicdb.demo.services.LogService;
import musicdb.demo.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private final AlbumService albumService;
    private final UserService userService;

    public LogServiceImpl(LogRepository logRepository, AlbumService albumService, UserService userService) {
        this.logRepository = logRepository;
        this.albumService = albumService;
        this.userService = userService;
    }

    @Override
    public void createLog(Long albumId, String action) {
        AlbumEntity albumEntity = albumService.findEntityById(albumId);
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity = userService.findByName(username);
        LogEntity logEntity = new LogEntity();
        logEntity.setAction(action).setAlbumEntity(albumEntity)
                .setUserEntity(userEntity)
                .setDateTime(LocalDateTime.now());
        logRepository.save(logEntity);


    }

    @Override
    public List<LogServiceModel> getAllLogs() {
        return logRepository.findAll().stream().map(log -> {
            LogServiceModel logServiceModel = new LogServiceModel();
            logServiceModel.setAction(log.getAction()).setAlbumName(log.getAlbumEntity().getName())
                    .setDateTime(log.getDateTime()).setUsername(log.getUserEntity().getUsername())
                    .setId(log.getId());
            return logServiceModel;
        }).collect(Collectors.toList());
    }
}
