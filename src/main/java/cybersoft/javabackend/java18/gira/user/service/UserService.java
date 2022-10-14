package cybersoft.javabackend.java18.gira.user.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import cybersoft.javabackend.java18.gira.user.dto.UserDTO;
import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserService extends GenericService<User, UserDTO, UUID> {
    UserDTO createUser(UserDTO dto);
}

@Service
@Transactional
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GiraMapper giraMapper;
    private final PasswordEncoder passwordEncoder;

    UserServiceImpl(UserRepository userRepository, GiraMapper giraMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.giraMapper = giraMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public JpaRepository<User, UUID> getRepository() {
        return userRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return giraMapper;
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = giraMapper.map(dto, User.class);
        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return giraMapper.map(
                userRepository.save(user),
                UserDTO.class
        );
    }
}
