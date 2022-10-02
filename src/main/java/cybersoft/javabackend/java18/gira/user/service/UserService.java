package cybersoft.javabackend.java18.gira.user.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import cybersoft.javabackend.java18.gira.user.dto.UserDTO;
import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface UserService extends GenericService<User, UserDTO, UUID> {

}

@Service
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GiraMapper giraMapper;

    UserServiceImpl(UserRepository userRepository, GiraMapper giraMapper) {
        this.userRepository = userRepository;
        this.giraMapper = giraMapper;
    }

    @Override
    public JpaRepository<User, UUID> getRepository() {
        return userRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return giraMapper;
    }
}
