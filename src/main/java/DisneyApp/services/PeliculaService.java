
package DisneyApp.services;

import DisneyApp.dto.PeliculaDTO;
import DisneyApp.dto.PeliculaImageTitleDateDTO;
import java.util.List;


public interface PeliculaService {
    
    public List<PeliculaDTO> findAll();
    
    public List<PeliculaImageTitleDateDTO> movies();
    
    public PeliculaDTO createMovie(PeliculaDTO peliculaDTO);
    
    public void addCharacterToMovie(Long personajeId, Long peliculaId);
    
    public void deleteMovie(Long id);
    
    public PeliculaDTO updateMovie(Long id, PeliculaDTO peliculaDTO);
    
//    public PeliculaDTO findByTitle(String title);
    
    public List<PeliculaDTO> findByTitle(String title);
}
