
package DisneyApp.dto;

import DisneyApp.models.Personaje;
import java.util.Date;
import java.util.Set;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter @Setter
public class PeliculaDTO {
    
    private Long id;
    
    private byte[] image;
    
    private String title;
    
    @DateTimeFormat
    private Date releaseDate;
    
    @Size(min = 1, max = 5, message = "La calificacion debe estar entre 1 y 5")
    private Integer qualification;
    
    private Set<Personaje> personajes;

    public PeliculaDTO() {
    }

    public PeliculaDTO(Long id, byte[] image, String title, Date releaseDate, Integer qualification) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.releaseDate = releaseDate;
        this.qualification = qualification;
    }

    public PeliculaDTO(Long id, byte[] image, String title, Date releaseDate, Integer qualification, Set<Personaje> personajes) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.releaseDate = releaseDate;
        this.qualification = qualification;
        this.personajes = personajes;
    }
    
}
