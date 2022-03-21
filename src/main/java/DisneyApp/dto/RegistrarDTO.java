package DisneyApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrarDTO {

    private String nombre;
    private String username;
    private String email;
    private String password;

    public RegistrarDTO() {
    }

    public RegistrarDTO(String nombre, String username, String email, String password) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
