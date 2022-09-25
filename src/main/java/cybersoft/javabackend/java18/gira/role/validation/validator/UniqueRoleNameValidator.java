package cybersoft.javabackend.java18.gira.role.validation.validator;

import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.repository.RoleRepository;
import cybersoft.javabackend.java18.gira.role.validation.annotation.UniqueRoleName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueRoleNameValidator
        implements ConstraintValidator<UniqueRoleName, String> {
    private String message;
    private final RoleRepository repository;

    public UniqueRoleNameValidator(RoleRepository roleRepository){
        this.repository = roleRepository;
    }

    @Override
    public void initialize(UniqueRoleName constraintAnnotation) {
       message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        Optional<Role> roleOpt = repository.findByName(name);

        if(roleOpt.isEmpty())
            return true;

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
