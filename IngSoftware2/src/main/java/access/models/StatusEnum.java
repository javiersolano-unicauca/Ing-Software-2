package access.models;


/**
 * Enumerador de los estados de un proyecto de grado
 * 
 * @author javiersolanop777
 */
public enum StatusEnum {
    
    FIRST("primera evaluacion formato A"),
    SECOND("segunda evaluacion formato A"),
    THIRD("tercera evaluacion formato A"),
    ACCEPTED("aceptado formato A"),
    REJECTED("rechazado formato A"),
    
    // Estados del lab - Patrón State
    INICIO("Inicio"),
    FORMATO_A_DILIGENCIADO("Formato A Diligenciado"),
    PRESENTADO_A_COORDINADOR("Presentado a Coordinador"),
    EN_EVALUACION_COMITE("En Evaluación Comité"),
    CORRECCIONES_COMITE("Correcciones Comité"),
    ACEPTADO_COMITE("Aceptado por Comité"),
    RECHAZADO_COMITE("Rechazado por Comité"),
    ESCRIBIENDO_ANTEPROYECTO("Escribiendo Anteproyecto"),
    PRESENTADO_JEFATURA("Presentado a Jefatura"),
    EVALUACION_DEPARTAMENTO("Evaluación Departamento"),
    EVALUADOR_PIDE_CORRECCIONES("Evaluador Pide Correcciones"),
    EVALUADOR_ACEPTA("Evaluador Acepta"),
    EVALUADOR_RECHAZA("Evaluador Rechaza"),
    APROBADO_CONSEJO("Aprobado Consejo"),
    RESOLUCION_APROBADO("Resolución Aprobado"),
    EN_DESARROLLO("En Desarrollo"),
    PRORROGA_SOLICITADA("Prórroga Solicitada"),
    FINALIZADO_MONOGRAFIA("Finalizado Monografía"),
    PRESENTADO_DECANATURA("Presentado a Decanatura"),
    FECHA_SUSTENTACION_FIJADA("Fecha Sustentación Fijada"),
    SUSTENTACION_PUBLICA("Sustentación Pública"),
    SUSTENTACION_APROBADA("Sustentación Aprobada"),
    SUSTENTACION_RECHAZADA("Sustentación Rechazada");
    
    private final String atrName;
    
    StatusEnum(String prmName)
    {
        this.atrName = prmName;
    }
    
    /**
     * Metodo para obtener el nombre del programa
     * 
     * @return El nombre
     */
    public String getName()
    {
        return this.atrName;
    }
    
    /**
     * Metodo para obtener la modalidad a partir de su nombre
     * 
     * @param prmModality Recibe el nombre
     * 
     * @return El programa si se encuentra. De lo contrario null.
     */
    public static StatusEnum getStatus(String prmModality)
    {
        for(StatusEnum objStatus: StatusEnum.values())
            if(objStatus.getName().equals(prmModality))
                return objStatus;
        return null;
    }
}
