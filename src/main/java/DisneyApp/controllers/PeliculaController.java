package DisneyApp.controllers;

import DisneyApp.dto.PeliculaDTO;
import DisneyApp.dto.PeliculaImageTitleDateDTO;
import DisneyApp.services.PeliculaService;
import java.util.List;
//import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping()
    public List<PeliculaImageTitleDateDTO> movies() {
        List<PeliculaImageTitleDateDTO> peliculas = peliculaService.movies();
        return peliculas;
    }

    @GetMapping(params = "order")
    public List<PeliculaDTO> moviesOrder(@RequestParam(name = "order") String order) {
        List<PeliculaDTO> peliculas = peliculaService.orderBy(order);
        return peliculas;
    }

    @GetMapping(params = "title")
    public List<PeliculaDTO> findByTitle(@RequestParam(name = "title") String title) {
        List<PeliculaDTO> peliculasDTO = peliculaService.findByTitle(title);
        return peliculasDTO;
    }

//    @GetMapping(params = "genderId")
//    public List<PeliculaDTO> orderByGenderId(@RequestParam(name = "genderId")Long genderId){
//        List<PeliculaDTO> peliculas = peliculaService.orderByGenderId(genderId);
//        return peliculas;
//    }
    @GetMapping("/details")
    public List<PeliculaDTO> moviesDetails() {
        List<PeliculaDTO> peliculas = peliculaService.findAll();
        return peliculas;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PeliculaDTO> createMovie(@RequestBody PeliculaDTO peliculaDTO) {
        return new ResponseEntity<>(peliculaService.createMovie(peliculaDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("{id}/characters/{characterId}")
    public ResponseEntity<String> addCharacterToMovie(
            @PathVariable(name = "id") Long id, @PathVariable(name = "characterId") Long characterId) {
        peliculaService.addCharacterToMovie(characterId, id);
        return new ResponseEntity<>("El personaje se a??adio correctamente", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable(name = "id") Long id) {
        peliculaService.deleteMovie(id);
        return new ResponseEntity<>("Pelicula eliminada con exito!", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PeliculaDTO> updateMovie(
            @PathVariable(name = "id") Long id, @RequestBody PeliculaDTO peliculaDTO) {
        PeliculaDTO pelicula = peliculaService.updateMovie(id, peliculaDTO);
        return new ResponseEntity<>(pelicula, HttpStatus.OK);
    }
}
