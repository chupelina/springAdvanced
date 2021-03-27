package musicdb.demo.models.binding;

import musicdb.demo.models.entities.enums.Genre;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumBindingModel {
    @NotNull
    @Size(min = 5)
    private String name;
    @NotNull
    @Size(min = 5)
    private String imageUrl;
    private String videoUrl;
    @NotNull
    @Size(min = 5)
    private String description;
    @Min(0)
    @NotNull
    private Integer copies;
    @DecimalMin("0")
    @NotNull
    private BigDecimal price;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate releaseDate;
    @NotNull
    private Genre genre;
    @NotNull
    private String artist;

    public String getArtist() {
        return artist;
    }

    public AlbumBindingModel setArtist(String artist) {
        this.artist = artist;
        return this;
    }

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

    public AlbumBindingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
