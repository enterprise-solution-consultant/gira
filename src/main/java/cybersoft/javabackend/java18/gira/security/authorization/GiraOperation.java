package cybersoft.javabackend.java18.gira.security.authorization;

import cybersoft.javabackend.java18.gira.role.model.Operation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GiraOperation {
    String name() default "";

    Operation.Type type() default Operation.Type.FETCH;
}
