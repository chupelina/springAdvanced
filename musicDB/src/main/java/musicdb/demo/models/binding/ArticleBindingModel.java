package musicdb.demo.models.binding;

import musicdb.demo.models.entities.enums.Genre;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class ArticleBindingModel {
    @Length(min=3, max = 20)
    @NotNull
    private String title;
    @Length(min=5)
    @NotNull
    private String imageUrl;
    @NotNull
    private Genre genre;
    @Length(min=5)
    @NotNull
    private String content;

    public String getTitle() {
        return title;
    }

    public ArticleBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArticleBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public ArticleBindingModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleBindingModel setContent(String content) {
        this.content = content;
        return this;
    }
}
