
package DisneyApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonajeNameImageDTO {
    
    private Long id;
    
    
    private byte[] image;
    
    
    private String name;

    public PersonajeNameImageDTO() {
    }

    public PersonajeNameImageDTO(Long id, byte[] image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }
    
    
    
}
