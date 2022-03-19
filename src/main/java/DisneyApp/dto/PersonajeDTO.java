
package DisneyApp.dto;

import DisneyApp.models.Pelicula;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonajeDTO {
    
    private Long id;
    
    
    private byte[] image;
    
    
    private String name;
    
    
    private Integer age;
    
    
    private String weight;
    
    private String history;

    private Set<Pelicula> peliculas;
    
    public PersonajeDTO() {
    }

    public PersonajeDTO(Long id, byte[] image, String name, Integer age, String weight, String history) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.history = history;
    }

    public PersonajeDTO(Long id, byte[] image, String name, Integer age, String weight, String history, Set<Pelicula> peliculas) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.history = history;
        this.peliculas = peliculas;
    }

    
    
}
