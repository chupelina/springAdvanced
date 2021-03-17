package musicdb.demo.web;

import musicdb.demo.services.CarouselService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CarouselService carouselService;

    public HomeController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("firstImg", carouselService.firstImg());
        model.addAttribute("secondImg", carouselService.secondImg());
        model.addAttribute("thirdImg", carouselService.thirdImg());
        return "home";
    }
}
