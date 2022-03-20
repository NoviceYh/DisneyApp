
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
    
    @Query("Select c From Pelicula c Order By c.releaseDate ASC")
    public List<Pelicula> orderByDateReleaseASC(@Param("order") String order);
    
    @Query("Select c From Pelicula c Order By c.releaseDate DESC")
    public List<Pelicula> orderByDateReleaseDESC(@Param("order") String order);
    
//    @Query("Select c From Pelicula c where c.genero.id like :genderId")
//    public List<Pelicula> orderByGenderId(@Param("genderId") Long genderId);
    
    
            
//    public List<Pelicula> orderByDateReleaseDESC
    
    //public Pelicula findByTitle(String title);
    
    
}
