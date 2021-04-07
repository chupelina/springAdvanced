package musicdb.demo.services;

import musicdb.demo.models.viewModels.ArticleViewModel;

import java.util.List;

public interface ArticleService {

    List<ArticleViewModel> getAllArticles();
}
