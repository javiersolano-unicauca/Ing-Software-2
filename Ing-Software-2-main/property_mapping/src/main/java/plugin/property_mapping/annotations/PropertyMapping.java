package plugin.property_mapping.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacion para la inyeccion de variables de entorno
 * del archivo de propiedades
 * 
 * @author javiersolanop777
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PropertyMapping {
    String propertyMapping() default "";
}
