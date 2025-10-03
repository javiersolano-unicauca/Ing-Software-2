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
public class EstadoEscribiendoAnteproyecto implements ProjectState{
    @Override
    public void siguienteEstado(ProjectContext context) {
        context.setCurrentState(new EstadoPresentadoJefatura());
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoAceptadoComite());
    }

    @Override
    public String getEstadoNombre() {
        return "Escribiendo Anteproyecto";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.ESCRIBIENDO_ANTEPROYECTO;
    }
}
