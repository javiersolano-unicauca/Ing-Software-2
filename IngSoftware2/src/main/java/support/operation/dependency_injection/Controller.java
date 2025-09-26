package support.operation.dependency_injection;

/**
 * Anotacion para la inyeccion de controladores
 * 
 * @author javiersolanop777
 */
public @interface Controller {
    String controller() default "";
}
