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
public class EstadoEvaluadorPideCorrecciones implements ProjectState{
    @Override
    public void siguienteEstado(ProjectContext context) {
        context.setCurrentState(new EstadoEvaluacionDepartamento());
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoEvaluacionDepartamento());
    }

    @Override
    public String getEstadoNombre() {
        return "Evaluador Pide Correcciones";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.EVALUADOR_PIDE_CORRECCIONES;
    }
}
