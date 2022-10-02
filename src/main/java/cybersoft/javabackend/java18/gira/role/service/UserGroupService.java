package cybersoft.javabackend.java18.gira.role.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import cybersoft.javabackend.java18.gira.role.dto.UserGroupDto;
import cybersoft.javabackend.java18.gira.role.dto.UserGroupWithUsersDTO;
import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import cybersoft.javabackend.java18.gira.role.repository.UserGroupRepository;
import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public interface UserGroupService extends GenericService<UserGroup, UserGroupDto, UUID> {
    UserGroupWithUsersDTO addUsers(UUID userGroupId, List<UUID> ids);

    List<UserGroupWithUsersDTO> findAllDtoIncludeUsers();
}

@Service
@Transactional
class UserGroupServiceImpl implements UserGroupService {
    private UserGroupRepository repository;
    private GiraMapper giraMapper;

    private UserService userService;

    UserGroupServiceImpl(UserGroupRepository repository, GiraMapper giraMapper, UserService userService) {
        this.repository = repository;
        this.giraMapper = giraMapper;
        this.userService = userService;
    }

    @Override
    public JpaRepository<UserGroup, UUID> getRepository() {
        return repository;
    }

    @Override
    public ModelMapper getMapper() {
        return giraMapper;
    }

    @Override
    public UserGroupWithUsersDTO addUsers(UUID userGroupId, List<UUID> ids) {
        UserGroup userGroup = repository.findById(userGroupId)
                .orElseThrow(() -> new ValidationException("UserGroup is not existed."));

        List<User> users = userService.findByIds(ids);
        users.forEach(userGroup::addUser);
        return giraMapper.map(userGroup, UserGroupWithUsersDTO.class);
    }

    @Override
    public List<UserGroupWithUsersDTO> findAllDtoIncludeUsers() {
        return repository.findAllWithUsers()
                .stream()
                .map(model -> giraMapper.map(model, UserGroupWithUsersDTO.class))
                .collect(Collectors.toList());
    }
}
