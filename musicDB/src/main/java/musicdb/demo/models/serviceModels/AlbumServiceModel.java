package musicdb.demo.models.serviceModels;

import musicdb.demo.models.entities.enums.Genre;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public class AlbumServiceModel {
    private String username;
    private String name;
    private String imageUrl;
    private String videoUrl;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private LocalDate releaseDate;
    private Genre genre;
    private String artist;

    public String getArtist() {
        return artist;
    }

    public AlbumServiceModel setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public AlbumServiceModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumServiceModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AlbumServiceModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public AlbumServiceModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AlbumServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
