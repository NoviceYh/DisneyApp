
package DisneyApp.services;

import DisneyApp.dto.PersonajeDTO;
import DisneyApp.dto.PersonajeNameImageDTO;
import java.util.List;


public interface PersonajeService {
    
    public PersonajeDTO create(PersonajeDTO personajeDTO);
    
    public List<PersonajeDTO> findAll();
    
    public PersonajeDTO findByName(String name);
    
    public PersonajeDTO findByAge(Integer age);
    
    public PersonajeDTO findById(Long id);
    
    public void delete(Long id);
    
    public PersonajeDTO updateCharacter(Long id, PersonajeDTO personajeDTO);
    
    public List<PersonajeNameImageDTO> findCharacters();
    
    
}
