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
public class EstadoEvaluadorRechaza implements ProjectState{
    @Override
    public void siguienteEstado(ProjectContext context) {
        // Estado final - no hay siguiente estado
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoEvaluacionDepartamento());
    }

    @Override
    public String getEstadoNombre() {
        return "Evaluador Rechaza";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.EVALUADOR_RECHAZA;
    }
    
}
