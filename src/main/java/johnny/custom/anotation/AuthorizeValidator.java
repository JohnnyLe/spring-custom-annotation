package johnny.custom.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Johnny on 23/03/18.
 */
//@Component
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizeValidator {
    // Custom parameters, example array String
    String[] value() default "";
}
