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
import org.springframework.web.bind.annotation.*;

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
    public String addConfirm(AlbumBindingModel albumBindingModel, @AuthenticationPrincipal
                             UserDetails principal){
        AlbumServiceModel albumServiceModel = modelMapper.map(albumBindingModel, AlbumServiceModel.class);
        albumServiceModel.setUsername(principal.getUsername());
        albumService.createAlbum(albumServiceModel);
        return "redirect:/home";
    }

}
