package musicdb.demo.services.implementations;

import musicdb.demo.services.ImageShuffler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ImageShufflerImpl implements ImageShuffler {
    @Override
    public void shuffle(List<String> images) {
        Collections.shuffle(images);
    }
}
