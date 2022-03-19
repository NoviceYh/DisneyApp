
package DisneyApp.controllers;

import DisneyApp.dto.PersonajeDTO;
import DisneyApp.dto.PersonajeNameImageDTO;
import DisneyApp.services.PersonajeService;
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
@RequestMapping("/characters")
public class PersonajeController {
    
    @Autowired
    private PersonajeService personajeService;
    
    @GetMapping()
    public List<PersonajeNameImageDTO> characters(){
        List<PersonajeNameImageDTO> personajes = personajeService.findCharacters();
        return personajes; 
    } 
    
    @GetMapping("details")
    public List<PersonajeDTO> charactersDetails(){
        List<PersonajeDTO> personajes = personajeService.findAll();
        return personajes; 
    } 
    
    @GetMapping("{id}")
    public ResponseEntity<PersonajeDTO> findById(@PathVariable(name = "id") Long id){
        PersonajeDTO personajeDTO = personajeService.findById(id);
        return new ResponseEntity<>(personajeDTO,HttpStatus.OK);
    }
    
    @GetMapping(params = "name")
    public ResponseEntity<PersonajeDTO> findByName(@RequestParam(name = "name") String name){
        PersonajeDTO personajeDTO = personajeService.findByName(name);
        return new ResponseEntity<>(personajeDTO,HttpStatus.OK);
    }
    
    @GetMapping(params = "age")
    public ResponseEntity<PersonajeDTO> findByAge(@RequestParam(name = "age") Integer age){
        PersonajeDTO personajeDTO = personajeService.findByAge(age);
        return new ResponseEntity<>(personajeDTO,HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<PersonajeDTO> saveCharacter(@RequestBody PersonajeDTO personajeDTO){
        PersonajeDTO personajeDevuelto = personajeService.create(personajeDTO);
        return new ResponseEntity<>(personajeDevuelto, HttpStatus.CREATED);        
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCharacter(@PathVariable(name = "id") Long id){
        personajeService.delete(id);
        return new ResponseEntity<>("El personaje fue eliminado correctamente!",HttpStatus.OK);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<PersonajeDTO> updateCharacter(
            @RequestBody PersonajeDTO personajeDTO, @PathVariable(name = "id") Long id){
        PersonajeDTO personajeDevuelto = personajeService.updateCharacter(id, personajeDTO);
        return new ResponseEntity<>(personajeDevuelto,HttpStatus.OK);
    }
    
    
}
