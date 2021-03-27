package musicdb.demo.web;

import musicdb.demo.services.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatisticController {
    private  final LogService logService;

    public StatisticController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public String getStatistic(Model model){
        model.addAttribute("logs", logService.getAllLogs());
        return "stats";
    }

}
