package cybersoft.javabackend.java18.gira.security.service;

import cybersoft.javabackend.java18.gira.common.exception.GiraBusinessException;
import cybersoft.javabackend.java18.gira.security.dto.LoginDTO;
import cybersoft.javabackend.java18.gira.security.jwt.JwtUtils;
import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface AuthService {
    String login(LoginDTO dto);
}

@Service
class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String login(LoginDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(
                        () -> new GiraBusinessException("User is not existed")
                );

        if(passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            // return jwt
            return jwtUtils.generateJwt(dto.getUsername());
        }

        throw new GiraBusinessException("Password is not correct.");
    }
}
