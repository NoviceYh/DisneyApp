package DisneyApp.services;

import DisneyApp.dto.GeneroDTO;
import java.util.List;

public interface GeneroService {

    public List<GeneroDTO> findAll();

    public GeneroDTO create(GeneroDTO generoDTO);

    public void delete(Long id);

    public GeneroDTO update(Long id, GeneroDTO generoDTO);

}
