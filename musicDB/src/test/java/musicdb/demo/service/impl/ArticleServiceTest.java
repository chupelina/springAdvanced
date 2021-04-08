package musicdb.demo.service.impl;

import musicdb.demo.models.entities.ArticleEntity;
import musicdb.demo.models.entities.UserEntity;
import musicdb.demo.models.entities.enums.Genre;
import musicdb.demo.models.viewModels.ArticleViewModel;
import musicdb.demo.repositories.ArticleRepository;
import musicdb.demo.services.implementations.ArticleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {
    private ArticleServiceImpl serviceToTest;
    private UserEntity userEntity, userEntity1;
    private ArticleEntity articleEntity, articleEntity1;

    @Mock
    ArticleRepository mockArticleRepository;
    ModelMapper modelMapper;

    @BeforeEach
    public void init() {
        modelMapper = new ModelMapper();
        userEntity = new UserEntity();
        userEntity.setUsername("u");
        articleEntity = new ArticleEntity();
        articleEntity.setTitle("1")
                .setImageUrl("1")
                .setGenre(Genre.METAL)
                .setContent("aa")
                .setUserEntity(userEntity);
        userEntity1 = new UserEntity();
        userEntity1.setUsername("a");
        articleEntity1 = new ArticleEntity();
        articleEntity1.setTitle("2")
                .setImageUrl("2")
                .setGenre(Genre.ROCK)
                .setContent("bb")
                .setUserEntity(userEntity1);

        serviceToTest = new ArticleServiceImpl(modelMapper, mockArticleRepository);
    }

    @Test
    public void findAllTest(){
        when(mockArticleRepository.findAll()).thenReturn(List.of(articleEntity, articleEntity1));
        List<ArticleViewModel> allArticles = serviceToTest.getAllArticles();
        Assertions.assertEquals(2,allArticles.size());

        ArticleViewModel model1 = allArticles.get(0);
        ArticleViewModel model2 = allArticles.get(1);

        // verify model 1
        Assertions.assertEquals(articleEntity.getTitle(), model1.getTitle());
        Assertions.assertEquals(articleEntity.getImageUrl(), model1.getImageUrl());
        Assertions.assertEquals(articleEntity.getGenre(), model1.getGenre());
        Assertions.assertEquals(articleEntity.getContent(), model1.getContent());
        Assertions.assertEquals(userEntity.getUsername(), model1.getAuthor());

        // verify model 2
        Assertions.assertEquals(articleEntity1.getTitle(), model2.getTitle());
        Assertions.assertEquals(articleEntity1.getImageUrl(), model2.getImageUrl());
        Assertions.assertEquals(articleEntity1.getGenre(), model2.getGenre());
        Assertions.assertEquals(articleEntity1.getContent(), model2.getContent());
        Assertions.assertEquals(userEntity1.getUsername(), model2.getAuthor());
    }

    @Test
    public void testLatestArticle(){
        when(mockArticleRepository.findTopByOrderByReleaseDateDesc()).thenReturn(Optional.of(articleEntity));
        ArticleViewModel firstByDate = serviceToTest.findFirstByDate();

        Assertions.assertEquals(articleEntity.getTitle(), firstByDate.getTitle());
        Assertions.assertEquals(articleEntity.getUserEntity().getUsername(), firstByDate.getAuthor());
    }

    @Test
    public void testLatestArticleWithNoPresent(){
        when(mockArticleRepository.findTopByOrderByReleaseDateDesc()).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class,()->serviceToTest.findFirstByDate());
    }
}
