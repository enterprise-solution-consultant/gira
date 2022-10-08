package cybersoft.javabackend.java18.gira.user.dto;

import cybersoft.javabackend.java18.gira.user.model.User;
import lombok.*;

import java.util.UUID;

/**
 * A DTO for the {@link cybersoft.javabackend.java18.gira.user.model.User} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String displayName;
    private String fullname;
    private String avatar;
    private User.Status status;
}
