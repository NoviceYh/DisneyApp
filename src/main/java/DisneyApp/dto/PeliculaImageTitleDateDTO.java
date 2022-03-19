
package DisneyApp.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter @Setter
public class PeliculaImageTitleDateDTO {
    
    private Long id;
    
    private byte[] image;
    
    private String title;
    
    @DateTimeFormat
    private Date releaseDate;

    public PeliculaImageTitleDateDTO() {
    }

    public PeliculaImageTitleDateDTO(Long id, byte[] image, String title, Date releaseDate) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.releaseDate = releaseDate;
    }
}
