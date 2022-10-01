package cybersoft.javabackend.java18.gira.role.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.role.dto.OperationDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.model.OperationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface OperationService extends GenericService<Operation, OperationDTO, UUID> {

    List<Operation> findAll(List<UUID> operationIds);
}

@org.springframework.stereotype.Service
@Transactional
class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final ModelMapper modelMapper;

    OperationServiceImpl(OperationRepository operationRepository, ModelMapper modelMapper) {
        this.operationRepository = operationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JpaRepository<Operation, UUID> getRepository() {
        return operationRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public List<Operation> findAll(List<UUID> operationIds) {
        return operationRepository.findAllById(operationIds);
    }
}
