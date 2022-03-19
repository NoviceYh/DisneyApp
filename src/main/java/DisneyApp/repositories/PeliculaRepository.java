
package DisneyApp.repositories;

import DisneyApp.models.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
    
    @Query("Select c From Pelicula c where c.title like :title")
    public List<Pelicula> findByTitle(@Param("title") String title);
    
    //public Pelicula findByTitle(String title);
    
    //public Pelicula findByTitle(String title);
}
