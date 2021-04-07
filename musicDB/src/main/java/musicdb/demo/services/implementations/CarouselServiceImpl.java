package musicdb.demo.services.implementations;

import musicdb.demo.services.CarouselService;
import musicdb.demo.services.ImageShuffler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    private Logger LOGGER = LoggerFactory.getLogger(CarouselServiceImpl.class);

    private List<String> images = new ArrayList<>();
    private ImageShuffler imageShuffler;

    public CarouselServiceImpl(@Value("${carousel.images}") List<String> images, ImageShuffler imageShuffler) {
        this.imageShuffler = imageShuffler;
        this.images.addAll(images);
    }

    @PostConstruct
    public void afterInitialise() {
        if (images.size() < 3) {
            throw new IllegalArgumentException("less than 3 images");
        }
    }


    @Override
    public String firstImg() {
        return images.get(0);
    }

    @Override
    public String secondImg() {
        return images.get(1);
    }

    @Override
    public String thirdImg() {
        return images.get(2);
    }

    @Scheduled(cron = "${carousel.refresh-cron}")
    public void refresh() {
        LOGGER.info("shuffling");
        imageShuffler.shuffle(images);
    }

}
