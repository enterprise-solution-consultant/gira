package cybersoft.javabackend.java18.gira.role.boundary;

import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.RoleDTO;
import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleRestResource {
    private final RoleService service;
    
    public RoleRestResource(RoleService roleService){
        this.service = roleService;
    }

    @GetMapping
    public Object findAll(){
        return ResponseUtils.get(service.findAllDto(RoleDTO.class), HttpStatus.OK);
    }
    
    @PostMapping
    public Object save(@RequestBody Role role){
        return ResponseUtils.get(service.save(role), HttpStatus.CREATED);
    }
}
