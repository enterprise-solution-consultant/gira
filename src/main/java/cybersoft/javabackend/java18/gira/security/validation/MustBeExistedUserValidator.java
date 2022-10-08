package cybersoft.javabackend.java18.gira.security.validation;

import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MustBeExistedUserValidator implements ConstraintValidator<MustBeExistedUser, String> {
    private String message;
    private final UserRepository userRepository;

    public MustBeExistedUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(MustBeExistedUser target) {
        this.message = target.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        User user = userRepository.findByUsername(username)
                        .orElse(null);

        if (user != null) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
