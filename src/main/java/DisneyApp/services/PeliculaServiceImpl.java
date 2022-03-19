
package DisneyApp.services;

import DisneyApp.dto.PeliculaDTO;
import DisneyApp.dto.PeliculaImageTitleDateDTO;
import DisneyApp.models.Pelicula;
import DisneyApp.models.Personaje;
import DisneyApp.repositories.PeliculaRepository;
import DisneyApp.repositories.PersonajeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaServiceImpl implements PeliculaService{

    @Autowired
    private PeliculaRepository peliculaRepository;
    
    @Autowired
    private PersonajeRepository personajeRepository;

    @Override
    public List<PeliculaDTO> findAll() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        List<PeliculaDTO> peliculasDtos = peliculas.stream()
                .map(pelicula -> mapearDTO(pelicula)).collect(Collectors.toList());
        return peliculasDtos;
    }   

    @Override
    public PeliculaDTO createMovie(PeliculaDTO peliculaDTO) {
        Pelicula pelicula = mapearEntidad(peliculaDTO);
        PeliculaDTO peliculaDevuelta = mapearDTO(peliculaRepository.save(pelicula));
        return peliculaDevuelta;
    }

    @Override
    public void addCharacterToMovie(Long personajeId, Long peliculaId) {
        Pelicula pelicula = peliculaRepository.findById(peliculaId).get();
        Personaje personaje = personajeRepository.findById(personajeId).get();
        
        Set<Personaje> personajes = pelicula.getCharacters();
        personajes.add(personaje);
        pelicula.setCharacters(personajes);
        peliculaRepository.save(pelicula);       
    }

    @Override
    public List<PeliculaImageTitleDateDTO> movies() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        List<PeliculaImageTitleDateDTO> peliculasDTO = new ArrayList<>();
        peliculasDTO = peliculas.stream()
                .map(pelicula -> mapearPeliculaImageTitleDateDTO(pelicula)).collect(Collectors.toList());
        return peliculasDTO;
    }    

    @Override
    public void deleteMovie(Long id) {
        Pelicula pelicula = peliculaRepository.findById(id).get();
        peliculaRepository.delete(pelicula);
    }

    @Override
    public PeliculaDTO updateMovie(Long id, PeliculaDTO peliculaDTO) {
        Pelicula pelicula = peliculaRepository.findById(id).get();        
        Pelicula peliculaNueva = PeliculaSaveDto(pelicula, peliculaDTO);        
        PeliculaDTO peliculaNuevaDTO = mapearDTO(peliculaRepository.save(peliculaNueva));
        return peliculaNuevaDTO;
    }
    
    private Pelicula PeliculaSaveDto(Pelicula pelicula, PeliculaDTO peliculaDTO){
        pelicula.setImage(peliculaDTO.getImage());
        pelicula.setCharacters(peliculaDTO.getPersonajes());
        pelicula.setQualification(peliculaDTO.getQualification());
        pelicula.setTitle(peliculaDTO.getTitle());
        pelicula.setReleaseDate(peliculaDTO.getReleaseDate());
        return pelicula;
    }
    
    private PeliculaDTO mapearDTO(Pelicula pelicula) {
        PeliculaDTO peliculaNueva = new PeliculaDTO();
        peliculaNueva.setImage(pelicula.getImage());
        peliculaNueva.setQualification(pelicula.getQualification());
        peliculaNueva.setId(pelicula.getId());
        peliculaNueva.setReleaseDate(pelicula.getReleaseDate());
        peliculaNueva.setTitle(pelicula.getTitle());
        return peliculaNueva;
    }

    private Pelicula mapearEntidad(PeliculaDTO peliculaDTO) {
        Pelicula pelicula = new Pelicula();        
        pelicula.setImage(peliculaDTO.getImage());
        pelicula.setQualification(peliculaDTO.getQualification());
        pelicula.setId(peliculaDTO.getId());
        pelicula.setReleaseDate(peliculaDTO.getReleaseDate());
        pelicula.setTitle(peliculaDTO.getTitle());
        return pelicula;
    }
    
    private PeliculaImageTitleDateDTO mapearPeliculaImageTitleDateDTO(Pelicula pelicula){
        PeliculaImageTitleDateDTO peliculaNueva = new PeliculaImageTitleDateDTO();
        peliculaNueva.setId(pelicula.getId());
        peliculaNueva.setImage(pelicula.getImage());
        peliculaNueva.setTitle(pelicula.getTitle());
        peliculaNueva.setReleaseDate(pelicula.getReleaseDate());
        return peliculaNueva;
    }

//    @Override
//    public PeliculaDTO findByTitle(String title) {
//        Pelicula pelicula = peliculaRepository.findByTitle("%"+title+"%");
//        PeliculaDTO peliculaDTO = mapearDTO(pelicula);
//        return peliculaDTO;
//    }
    @Override
    public List<PeliculaDTO> findByTitle(String title) {
        List<Pelicula> peliculas = peliculaRepository.findByTitle("%"+title+"%");
        List<PeliculaDTO> peliculasDTO = peliculas.stream()
                .map(pelicula -> mapearDTO(pelicula)).collect(Collectors.toList());
        return peliculasDTO;
    }
    
}
