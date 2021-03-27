package musicdb.demo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class LogEntity extends BaseEntity {
    @ManyToOne
    private UserEntity userEntity;
    @Column(nullable = false)
    private String action;
    @ManyToOne
    private AlbumEntity albumEntity;
    @Column(nullable = false)
    private LocalDateTime dateTime;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public LogEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public String getAction() {
        return action;
    }

    public LogEntity setAction(String action) {
        this.action = action;
        return this;
    }

    public AlbumEntity getAlbumEntity() {
        return albumEntity;
    }

    public LogEntity setAlbumEntity(AlbumEntity albumEntity) {
        this.albumEntity = albumEntity;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
