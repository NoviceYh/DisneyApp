
package DisneyApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneroDTO {
    
    private Long id;
    
    private String name;
    
    private byte[] image;

    public GeneroDTO() {
    }

    public GeneroDTO(Long id, String name, byte[] image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
    
}
