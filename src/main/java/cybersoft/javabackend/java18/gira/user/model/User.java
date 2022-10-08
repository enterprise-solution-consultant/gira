package cybersoft.javabackend.java18.gira.user.model;

import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = UserEntity.User.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = "User.findByUsernameLikeIgnoreCase", query = "select u from User u where upper(u.username) like upper(:username)")
})
public class User extends BaseEntity {
    @Column(name = UserEntity.User.USERNAME
            , nullable = false
            , unique = true
            , length = 100
            , updatable = false
    )
    private String username;

    @Column(name = UserEntity.User.PASSWORD, nullable = false)
    private String password;

    @Column(name = UserEntity.User.EMAIL
            , unique = true
            , nullable = false
            , length = 100
    )
    private String email;

    @Column(name = UserEntity.User.DISPLAY_NAME)
    private String displayName;

    @Column(name = UserEntity.User.FULLNAME)
    private String fullname;

    @Column(name = UserEntity.User.AVATAR)
    private String avatar;

    @Column(name = UserEntity.User.STATUS)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = UserEntity.User.FACEBOOK_URL)
    private String facebookUrl;

    @Column(name = UserEntity.User.MAJORITY)
    private String majority;

    @Column(name = UserEntity.User.DEPARTMENT)
    private String department;

    @Column(name = UserEntity.User.HOBBY)
    private String hobbies;

    @ManyToMany(mappedBy = "users")
    private Set<UserGroup> userGroups = new LinkedHashSet<>();

    public enum Status {
        ACTIVE,
        TEMPORARY_BLOCKED,
        PERMANENT_BLOCKED
    }
}
