package musicdb.demo.web;

import musicdb.demo.models.binding.AlbumBindingModel;
import musicdb.demo.models.serviceModels.AlbumServiceModel;
import musicdb.demo.services.AlbumService;
import musicdb.demo.services.ArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/albums")
public class AlbumController {
    private final ModelMapper modelMapper;
    private final AlbumService albumService;
    private final ArtistService artistService;


    public AlbumController(ModelMapper modelMapper, AlbumService albumService, ArtistService artistService) {
        this.modelMapper = modelMapper;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @ModelAttribute("albumBindingModel")
    public AlbumBindingModel createModel(){
        return new AlbumBindingModel();
    }

    @GetMapping("/add")
    public String addAlbum(Model model){
        model.addAttribute("artists", artistService.getAllByName());
        return "add-album";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AlbumBindingModel albumBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetails principal){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("albumBindingModel", albumBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumBindingModel",
                    bindingResult);
            return "redirect:/albums/add";
        }
        AlbumServiceModel albumServiceModel = modelMapper.map(albumBindingModel, AlbumServiceModel.class);
        albumServiceModel.setUsername(principal.getUsername());
        albumService.createAlbum(albumServiceModel);
        return "redirect:/home";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){
        model.addAttribute("currentAlbum", albumService.getAlbumById(id));
                return "details";
    }
}
