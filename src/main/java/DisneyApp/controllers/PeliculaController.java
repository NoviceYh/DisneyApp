
package DisneyApp.controllers;

import DisneyApp.dto.PeliculaDTO;
import DisneyApp.dto.PeliculaImageTitleDateDTO;
import DisneyApp.services.PeliculaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    @GetMapping
    public List<PeliculaImageTitleDateDTO> movies(){
        List<PeliculaImageTitleDateDTO> peliculas = peliculaService.movies();
        return peliculas;
    }
    
    @GetMapping("/details")
    public List<PeliculaDTO> moviesDetails(){
        List<PeliculaDTO> peliculas = peliculaService.findAll();
        return peliculas;
    }
    
//    @GetMapping(params = "title")
//    public ResponseEntity<PeliculaDTO> findByTitle(@RequestParam(name = "title") String title){
//        PeliculaDTO peliculaDTO = peliculaService.findByTitle(title);
//        return new ResponseEntity<>(peliculaDTO,HttpStatus.OK);
//    }
    @GetMapping(params = "title")
    public List<PeliculaDTO> findByTitle(@RequestParam(name = "title") String title){
        List<PeliculaDTO> peliculasDTO = peliculaService.findByTitle(title);
        return peliculasDTO;
    }
    
    @PostMapping
    public ResponseEntity<PeliculaDTO> createMovie(@RequestBody PeliculaDTO peliculaDTO){
        PeliculaDTO pelicula = peliculaService.createMovie(peliculaDTO);
        return new ResponseEntity<>(pelicula,HttpStatus.CREATED);
    }
    
    @PostMapping("{id}/characters/{characterId}")
    public ResponseEntity<String> addCharacterToMovie(
            @PathVariable(name = "id") Long id,@PathVariable(name = "characterId") Long characterId ){
        peliculaService.addCharacterToMovie(characterId, id);
        return new ResponseEntity<>("El personaje se añadio correctamente", HttpStatus.OK);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable(name = "id") Long id){
        peliculaService.deleteMovie(id);
        return new ResponseEntity<>("Pelicula eliminada con exito!", HttpStatus.OK);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<PeliculaDTO> updateMovie(
            @PathVariable(name = "id")Long id, @RequestBody PeliculaDTO peliculaDTO){
        PeliculaDTO pelicula = peliculaService.updateMovie(id, peliculaDTO);
        return new ResponseEntity<>(pelicula,HttpStatus.OK);
    }    
}
