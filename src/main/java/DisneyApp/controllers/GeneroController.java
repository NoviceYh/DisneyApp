package DisneyApp.controllers;

import DisneyApp.dto.GeneroDTO;
import DisneyApp.services.GeneroService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genders")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public List<GeneroDTO> genders() {
        List<GeneroDTO> generos = generoService.findAll();
        return generos;
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> create(@RequestBody GeneroDTO generoDTO) {
        GeneroDTO genero = generoService.create(generoDTO);
        return new ResponseEntity<>(genero, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGender(@PathVariable(name = "id") Long id) {
        generoService.delete(id);
        return new ResponseEntity<>("El genero ha sido eliminado con exito!", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<GeneroDTO> create(
            @PathVariable(name = "id") Long id, @RequestBody GeneroDTO generoDTO) {
        GeneroDTO genero = generoService.update(id, generoDTO);
        return new ResponseEntity<>(genero, HttpStatus.CREATED);
    }
}
