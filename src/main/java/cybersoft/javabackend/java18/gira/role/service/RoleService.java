package cybersoft.javabackend.java18.gira.role.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import cybersoft.javabackend.java18.gira.role.dto.RoleDTO;
import cybersoft.javabackend.java18.gira.role.dto.RoleWithOperationsDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

public interface RoleService extends GenericService<Role, RoleDTO, UUID> {
    Role update(Role role, String code);
    void deleteByCode(String code);

    RoleDTO save(RoleDTO dto);

    RoleWithOperationsDTO addOperations(UUID roleId, List<UUID> ids);
}

@Service
@Transactional
class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final GiraMapper mapper;
    private final OperationService operationService;
    
    public RoleServiceImpl(RoleRepository repository, GiraMapper mapper, OperationService operationService){
        this.repository = repository;
        this.mapper = mapper;
        this.operationService = operationService;
    }

    @Override
    public Role update(Role role, String code) {
        Role curRole = repository.findByCode(code);
        curRole.setName(role.getName());
        curRole.setDescription(role.getDescription());
        return repository.save(curRole);
    }

    @Override
    public void deleteByCode(String code) {
        repository.deleteByCode(code);
    }

    @Override
    public RoleDTO save(RoleDTO dto) {
        Role model = mapper.map(dto, Role.class);
        Role savedModel = repository.save(model);
        return mapper.map(savedModel, RoleDTO.class);
    }

    @Override
    public RoleWithOperationsDTO addOperations(UUID roleId, List<UUID> ids) {
        Role curRole = repository.findById(roleId)
                .orElseThrow( () ->
                        new ValidationException("Role is not existed.")
                );

        List<Operation> operations = operationService.findByIds(ids);

        operations.forEach(curRole::addService);

        return mapper.map(curRole, RoleWithOperationsDTO.class);
    }


    @Override
    public JpaRepository<Role, UUID> getRepository() {
        return this.repository;
    }

    @Override
    public ModelMapper getMapper() {
        return this.mapper;
    }
}
