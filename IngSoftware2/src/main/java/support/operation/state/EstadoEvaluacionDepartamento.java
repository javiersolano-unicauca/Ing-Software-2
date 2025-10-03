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
public class EstadoEvaluacionDepartamento implements ProjectState{
    @Override
    public void siguienteEstado(ProjectContext context) {
        // Simular decisión del departamento
        if (Math.random() > 0.4) {
            context.setCurrentState(new EstadoEvaluadorAcepta());
        } else {
            context.setCurrentState(new EstadoEvaluadorPideCorrecciones());
        }
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoPresentadoJefatura());
    }

    @Override
    public String getEstadoNombre() {
        return "Evaluación Departamento";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.EVALUACION_DEPARTAMENTO;
    }
}
