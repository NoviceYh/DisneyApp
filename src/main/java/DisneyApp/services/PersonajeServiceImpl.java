package DisneyApp.services;

import DisneyApp.dto.PersonajeDTO;
import DisneyApp.dto.PersonajeNameImageDTO;
import DisneyApp.exceptions.ResourceNotFoundException;
import DisneyApp.models.Personaje;
import DisneyApp.repositories.PersonajeRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

//    @Autowired
//    private ModelMapper modelMapper;
    @Override
    @Transactional
    public PersonajeDTO create(PersonajeDTO personajeDTO) {
        Personaje personaje = mapearEntidad(personajeDTO);
        Personaje personajeNuevo = personajeRepository.save(personaje);
        PersonajeDTO personajeDevuelto = mapearDTO(personajeNuevo);
        return personajeDevuelto;
    }

    @Override
    @Transactional
    public List<PersonajeDTO> findAll() {
        List<Personaje> personajes = personajeRepository.findAll();
        List<PersonajeDTO> personajesDTO = personajes.stream().map(personaje -> mapearDTO(personaje)).collect(Collectors.toList());
        return personajesDTO;
    }

    @Override
    @Transactional
    public PersonajeDTO findById(Long id) {
        Personaje personaje = personajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", id));
        PersonajeDTO personajeDevuelto = mapearDTO(personaje);
        return personajeDevuelto;
    }

    @Override
    @Transactional
    public PersonajeDTO findByName(String name) {
        Personaje personaje = personajeRepository.findByName(name);
        PersonajeDTO personajeDevuelto = mapearDTO(personaje);
        return personajeDevuelto;
    }

    @Override
    @Transactional
    public PersonajeDTO findByAge(Integer age) {
        Personaje personaje = personajeRepository.findByAge(age);
        PersonajeDTO personajeDevuelto = mapearDTO(personaje);
        return personajeDevuelto;
    }

    @Override
    @Transactional
    public List<PersonajeNameImageDTO> findCharacters() {
        List<Personaje> personajes = personajeRepository.findAll();
        List<PersonajeNameImageDTO> personajeNameImage; //newArrayList borre

        personajeNameImage = personajes.stream()
                .map(personaje -> mapearPersonajeNameImageDTO(personaje)).collect(Collectors.toList());

        return personajeNameImage;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Personaje personaje = personajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", id));
        personajeRepository.delete(personaje);
    }

    @Override
    @Transactional
    public PersonajeDTO updateCharacter(Long id, PersonajeDTO personajeDTO) {
        Personaje personaje = personajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", id));

        personaje.setAge(personajeDTO.getAge());
        personaje.setHistory(personajeDTO.getHistory());
        personaje.setWeight(personajeDTO.getWeight());
        personaje.setName(personajeDTO.getName());
        personaje.setImage(personajeDTO.getImage());

        PersonajeDTO personajeDevuelto = mapearDTO(personajeRepository.save(personaje));
        return personajeDevuelto;
    }

    //Mapeos de Entidades
    private PersonajeNameImageDTO mapearPersonajeNameImageDTO(Personaje personaje) {
        PersonajeNameImageDTO personajeNuevo = new PersonajeNameImageDTO();
        personajeNuevo.setId(personaje.getId());
        personajeNuevo.setImage(personaje.getImage());
        personajeNuevo.setName(personaje.getName());
        return personajeNuevo;
    }

    private PersonajeDTO mapearDTO(Personaje personaje) {
        PersonajeDTO personajeNuevo = new PersonajeDTO();
        personajeNuevo.setAge(personaje.getAge());
        personajeNuevo.setHistory(personaje.getHistory());
        personajeNuevo.setId(personaje.getId());
        personajeNuevo.setWeight(personaje.getWeight());
        personajeNuevo.setName(personaje.getName());
        personajeNuevo.setImage(personaje.getImage());
        return personajeNuevo;
    }

    private Personaje mapearEntidad(PersonajeDTO personajeDTO) {
        Personaje personaje = new Personaje();
        personaje.setAge(personajeDTO.getAge());
        personaje.setHistory(personajeDTO.getHistory());
        personaje.setId(personajeDTO.getId());
        personaje.setWeight(personajeDTO.getWeight());
        personaje.setName(personajeDTO.getName());
        personaje.setImage(personajeDTO.getImage());
        return personaje;
    }

}
