package cybersoft.javabackend.java18.gira.security.dto;

import cybersoft.javabackend.java18.gira.security.validation.MustBeExistedUser;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @MustBeExistedUser
    private String username;
    private String password;
}
