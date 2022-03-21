package DisneyApp.services;

import DisneyApp.dto.GeneroDTO;
import DisneyApp.exceptions.ResourceNotFoundException;
import DisneyApp.models.Genero;
import DisneyApp.repositories.GeneroRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    @Transactional
    public List<GeneroDTO> findAll() {
        List<Genero> generos = generoRepository.findAll();
        List<GeneroDTO> genders = generos.stream()
                .map(genero -> mapearDTO(genero)).collect(Collectors.toList());
        return genders;
    }

    @Override
    @Transactional
    public GeneroDTO create(GeneroDTO generoDTO) {
        Genero genero = mapearEntidad(generoDTO);
        GeneroDTO generoNuevo = mapearDTO(generoRepository.save(genero));
        return generoNuevo;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genero", "id", id));
        generoRepository.delete(genero);
    }

    @Override
    @Transactional
    public GeneroDTO update(Long id, GeneroDTO generoDTO) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genero", "id", id));
        genero.setImage(generoDTO.getImage());
        genero.setName(generoDTO.getName());

        GeneroDTO generoNuevo = mapearDTO(generoRepository.save(genero));
        return generoNuevo;

    }

    private GeneroDTO mapearDTO(Genero genero) {
        GeneroDTO generoNuevo = new GeneroDTO();

        generoNuevo.setId(genero.getId());
        generoNuevo.setImage(genero.getImage());
        generoNuevo.setName(genero.getName());
        return generoNuevo;
    }

    private Genero mapearEntidad(GeneroDTO generoDTO) {
        Genero genero = new Genero();

        genero.setId(generoDTO.getId());
        genero.setName(generoDTO.getName());
        genero.setImage(generoDTO.getImage());

        return genero;
    }

}
