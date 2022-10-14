package cybersoft.javabackend.java18.gira.security.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MustBeExistedUserValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MustBeExistedUser {
    String message() default "{user.not.existed}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
