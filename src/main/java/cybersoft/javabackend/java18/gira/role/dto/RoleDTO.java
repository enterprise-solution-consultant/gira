package cybersoft.javabackend.java18.gira.role.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    // Data Transfer Object
    private UUID id;
    private String name;
    private String code;
    private String description;
}
