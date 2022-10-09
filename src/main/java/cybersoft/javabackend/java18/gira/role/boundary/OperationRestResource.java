package cybersoft.javabackend.java18.gira.role.boundary;

import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.OperationDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.service.OperationService;
import cybersoft.javabackend.java18.gira.security.authorization.GiraOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/operations")
public class OperationRestResource {
    private final OperationService operationService;

    public OperationRestResource(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "findAllOperation", type= Operation.Type.FETCH)
    public ResponseEntity<?> findAll(){
        return ResponseUtils.get(operationService.findAllDto(OperationDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid OperationDTO dto) {
        return ResponseUtils.get(
                operationService.save(dto, Operation.class, OperationDTO.class)
                , HttpStatus.OK
        );
    }


}
