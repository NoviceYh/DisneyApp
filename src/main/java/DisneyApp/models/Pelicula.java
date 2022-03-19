
package DisneyApp.models;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity @Getter @Setter
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private byte[] image;
    
    private String title;
    
    @DateTimeFormat
    private Date releaseDate;
    
    private Integer qualification;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "peliculas_personajes", joinColumns = 
            @JoinColumn(name = "movie_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "character_id", nullable = false))
    private Set<Personaje> characters;

    public Pelicula(byte[] image, String title, Date releaseDate, Integer qualification, Set<Personaje> characters) {
        this.image = image;
        this.title = title;
        this.releaseDate = releaseDate;
        this.qualification = qualification;
        this.characters = characters;
    }

    public Pelicula() {
    }
    
    
}
