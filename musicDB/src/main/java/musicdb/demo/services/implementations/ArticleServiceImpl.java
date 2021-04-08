package musicdb.demo.services.implementations;

import musicdb.demo.models.binding.ArticleBindingModel;
import musicdb.demo.models.entities.ArticleEntity;
import musicdb.demo.models.entities.UserEntity;
import musicdb.demo.models.viewModels.ArticleViewModel;
import musicdb.demo.repositories.ArticleRepository;
import musicdb.demo.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;


    public ArticleServiceImpl(ModelMapper modelMapper, ArticleRepository articleRepository) {
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleViewModel> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(a -> {
                    ArticleViewModel map = modelMapper.map(a, ArticleViewModel.class);
                    map.setAuthor(a.getUserEntity().getUsername());
                    return map;
                }).collect(Collectors.toList());
    }

    @Override
    public void saveArticle(ArticleBindingModel articleBindingModel, UserEntity userEntity) {
        ArticleEntity articleEntity = modelMapper.map(articleBindingModel, ArticleEntity.class);
        articleEntity.setUserEntity(userEntity);
        articleEntity.setReleaseDate(LocalDateTime.now());
        articleRepository.save(articleEntity);
    }

    @Override
    public ArticleViewModel findFirstByDate() {
        ArticleEntity articleEntity = articleRepository.findTopByOrderByReleaseDateDesc()
                .orElseThrow();
        ArticleViewModel map = modelMapper.map(articleEntity, ArticleViewModel.class);
        map.setAuthor(articleEntity.getUserEntity().getUsername());
        return map;
    }
}
