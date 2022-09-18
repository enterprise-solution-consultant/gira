package cybersoft.javabackend.java18.gira.role.boundary;

import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
    
    @PostMapping
    public Object save(@RequestBody Role role){
        return new ResponseEntity<>(service.save(role), HttpStatus.CREATED);
    }
}
