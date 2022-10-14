package cybersoft.javabackend.java18.gira.security.authorization;

import cybersoft.javabackend.java18.gira.common.exception.GiraBusinessException;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.model.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j
public class AuthorizationAspect {
    private final OperationRepository operationRepository;

    public AuthorizationAspect(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }
    // define point cut

    @Before("@annotation(giraOperation)")
    public void authorizeOperation(GiraOperation giraOperation){
        log.info("Pointcut has been activated, operation = " + giraOperation.name());
        // get current user
        String username = getCurrentUser();

        // check permission
        if (!isPermitted(username, giraOperation.name()))
            throw new GiraBusinessException(
                    "User is not permitted to use this operation. Please contact administrators for permissions."
        );
    }

    private boolean isPermitted(String username, String operationName) {
        List<Operation> permittedOperations
                = operationRepository.findAllByNameAndUsername(operationName, username);

        return !permittedOperations.isEmpty();
    }

    private String getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null)
            return null;

        if (auth.getPrincipal() instanceof String principal)
            return principal;

        UserDetails currentUser = (UserDetails) auth.getPrincipal();
        return currentUser.getUsername();
    }
}
