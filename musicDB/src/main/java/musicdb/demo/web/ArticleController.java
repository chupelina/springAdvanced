package musicdb.demo.web;

import musicdb.demo.models.binding.ArticleBindingModel;
import musicdb.demo.services.ArticleService;
import musicdb.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getAllArticles(Model model){
        model.addAttribute("latestArticle", articleService.findFirstByDate());
        return "all-articles";
    }

    @GetMapping("/add")
    public String addArticle(Model model){
        if(!model.containsAttribute("articleBindingModel")){
            model.addAttribute("articleBindingModel", new ArticleBindingModel());
        }
        return "add-article";
    }

    @PostMapping("/add")
    public String addArticle(@Valid ArticleBindingModel articleBindingModel, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Principal principal){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("articleBindingModel", articleBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.articleBindingModel",
                    bindingResult);
            return "redirect:/articles/add";
        }
        articleService.saveArticle(articleBindingModel, userService.findByName(principal.getName()));
        return "redirect:/articles/all";

    }
}
