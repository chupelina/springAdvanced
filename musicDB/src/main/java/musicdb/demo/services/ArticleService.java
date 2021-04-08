package musicdb.demo.services;

import musicdb.demo.models.binding.ArticleBindingModel;
import musicdb.demo.models.entities.UserEntity;
import musicdb.demo.models.viewModels.ArticleViewModel;

import java.util.List;

public interface ArticleService {

    List<ArticleViewModel> getAllArticles();

    void saveArticle(ArticleBindingModel articleBindingModel, UserEntity name);

    ArticleViewModel findFirstByDate();
}
