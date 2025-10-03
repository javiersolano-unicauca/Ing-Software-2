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
public class EstadoFormatoADiligenciado implements ProjectState{
    @Override
    public void siguienteEstado(ProjectContext context) {
        context.setCurrentState(new EstadoPresentadoACoordinador());
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoInicio());
    }

    @Override
    public String getEstadoNombre() {
        return "Formato A Diligenciado";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.FORMATO_A_DILIGENCIADO;
    }
}
