package musicdb.demo.services.implementations;

import musicdb.demo.models.viewModels.ArticleViewModel;
import musicdb.demo.repositories.ArticleRepository;
import musicdb.demo.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
