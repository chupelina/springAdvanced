package musicdb.demo.models.viewModels;

public class ArtistViewModel {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public ArtistViewModel setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ArtistViewModel setName(String name) {
        this.name = name;
        return this;
    }
}
