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
public class EstadoPresentadoACoordinador implements ProjectState{
    @Override
    public void siguienteEstado(ProjectContext context) {
        context.setCurrentState(new EstadoEnEvaluacionComite());
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoFormatoADiligenciado());
    }

    @Override
    public String getEstadoNombre() {
        return "Presentado a Coordinador";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.PRESENTADO_A_COORDINADOR;
    }
}
