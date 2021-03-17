package musicdb.demo.models.viewModels;

import musicdb.demo.models.entities.enums.Genre;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.Instant;

public class AlbumViewModel {
    private String name;
    private String imageUrl;
    private String videoUrl;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private Instant releaseDate;
    private Genre genre;

    public String getName() {
        return name;
    }

    public AlbumViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public AlbumViewModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumViewModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public AlbumViewModel setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public AlbumViewModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }
}
