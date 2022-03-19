
package DisneyApp.repositories;

import DisneyApp.models.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long>{
    
    public Personaje findByName(String name);
    
    public Personaje findByAge(Integer age);  
    
}
