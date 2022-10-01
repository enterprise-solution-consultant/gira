package cybersoft.javabackend.java18.gira.role.model;

import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = RoleEntity.UserGroup.TABLE_NAME)
public class UserGroup extends BaseEntity {
    @ManyToMany(mappedBy = "userGroups", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Role> roles = new LinkedHashSet<>();

    @Column(name = RoleEntity.UserGroup.NAME, unique = true, length = 100)
    @Length(min = 5, max = 100, message = "Role name must have length between {min} and {max}")
    private String name;

    @Column(name = RoleEntity.UserGroup.CODE, unique = true)
    @Length(min = 3, max = 10, message = "Role code must have length between {min} and {max}")
    private String code;

    @Column(name = RoleEntity.UserGroup.DESCRIPTION)
    @NotBlank
    private String description;
}
