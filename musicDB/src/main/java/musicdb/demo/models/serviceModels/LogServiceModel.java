package musicdb.demo.models.serviceModels;

import musicdb.demo.models.entities.AlbumEntity;
import musicdb.demo.models.entities.UserEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class LogServiceModel {
    private Long id;
    private String username;
    private String action;
    private String albumName;
    private LocalDateTime dateTime;

    public Long getId() {
        return id;
    }

    public LogServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LogServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getAction() {
        return action;
    }

    public LogServiceModel setAction(String action) {
        this.action = action;
        return this;
    }

    public String getAlbumName() {
        return albumName;
    }

    public LogServiceModel setAlbumName(String albumName) {
        this.albumName = albumName;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogServiceModel setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
