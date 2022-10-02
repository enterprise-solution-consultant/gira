package cybersoft.javabackend.java18.gira.role.boundary;

import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.UserGroupDto;
import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import cybersoft.javabackend.java18.gira.role.service.UserGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usergroups")
public class UserGroupRestResource {
    private final UserGroupService service;

    public UserGroupRestResource(UserGroupService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAllUser(){
        return ResponseUtils.get(
                service.findAllDto(UserGroupDto.class)
                , HttpStatus.OK
        );
    }

    @GetMapping("/include-users")
    public ResponseEntity<?> findAllUserGroupIncludedUsers(){
        return ResponseUtils.get(
                service.findAllDtoIncludeUsers()
                , HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserGroupDto userGroupDto) {
        return ResponseUtils.get(
                service.save(userGroupDto, UserGroup.class, UserGroupDto.class)
                , HttpStatus.OK
        );
    };

    @PostMapping("{user-group-id}/add-users")
    public ResponseEntity<?> addUsers(
            @PathVariable("user-group-id") UUID userGroupId,
            @RequestBody List<UUID> ids){
        return ResponseUtils.get(
                service.addUsers(userGroupId, ids)
                , HttpStatus.OK
        );
    }
}
