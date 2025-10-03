/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.state;

import access.models.StatusEnum;
/**
 *
 * @author yezid
 */
public class EstadoEnEvaluacionComite implements ProjectState{
    @Override
    public void siguienteEstado(ProjectContext context) {
        // Simular decisión del comité (70% aprobado, 30% correcciones)
        if (Math.random() > 0.3) {
            context.setCurrentState(new EstadoAceptadoComite());
        } else {
            context.setCurrentState(new EstadoCorreccionesComite());
        }
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoPresentadoACoordinador());
    }

    @Override
    public String getEstadoNombre() {
        return "En Evaluación Comité";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.EN_EVALUACION_COMITE;
    }
}
