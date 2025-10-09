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
public class EstadoFinalizadoMonografia implements ProjectState{
    @Override
    public void siguienteEstado(ProjectContext context) {
        context.setCurrentState(new EstadoPresentadoDecanatura());
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoEnDesarrollo());
    }

    @Override
    public String getEstadoNombre() {
        return "Finalizado Monografia";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.FINALIZADO_MONOGRAFIA;
    }
    
}
