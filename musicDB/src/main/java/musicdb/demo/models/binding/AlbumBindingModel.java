package musicdb.demo.models.binding;

import musicdb.demo.models.entities.enums.Genre;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.Instant;

public class AlbumBindingModel {
    private String name;
    private String imageUrl;
    private String videoUrl;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private Instant releaseDate;
    private Genre genre;


    public Genre getGenre() {
        return genre;
    }

    public AlbumBindingModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public AlbumBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumBindingModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public AlbumBindingModel setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }
}
