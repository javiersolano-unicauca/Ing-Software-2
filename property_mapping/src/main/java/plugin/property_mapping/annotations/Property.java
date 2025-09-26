package plugin.property_mapping.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacion para la inyeccion de variables de entorno
 * del archivo de propiedades en campos de una clase
 * 
 * @author javiersolanop777
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {
    String property();
}
