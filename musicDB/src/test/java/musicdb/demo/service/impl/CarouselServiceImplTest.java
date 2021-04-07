package musicdb.demo.service.impl;

import musicdb.demo.services.ImageShuffler;
import musicdb.demo.services.implementations.CarouselServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class CarouselServiceImplTest {

    private CarouselServiceImpl serviceToTest;

    @BeforeEach
    public void setUp(){
        serviceToTest = new CarouselServiceImpl(List.of("a", "b", "c"), new TestImagesShuffler());
    }

    @Test
    public void testRefresh(){
        serviceToTest.refresh();
        Assertions.assertEquals("c", serviceToTest.firstImg() );
        Assertions.assertEquals("b", serviceToTest.secondImg());
        Assertions.assertEquals("a", serviceToTest.thirdImg() );
    }

    static  class TestImagesShuffler implements ImageShuffler{

        @Override
        public void shuffle(List<String> images) {
            Collections.reverse(images);
        }
    }
}
