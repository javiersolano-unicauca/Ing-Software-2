package support.operation.dependency_injection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacion para el escaneo de clases que implementen 
 * la anotacion 'Controller'
 * 
 * @see Controller
 * 
 * @author javiersolanop777
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ControllersScan {
    String packageName();
}
