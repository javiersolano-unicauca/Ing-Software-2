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
public class EstadoEnDesarrollo implements ProjectState{
    @Override
    public void siguienteEstado(ProjectContext context) {
        // Simular si pide prórroga o finaliza
        if (Math.random() > 0.7) {
            context.setCurrentState(new EstadoProrrogaSolicitada());
        } else {
            context.setCurrentState(new EstadoFinalizadoMonografia());
        }
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoResolucionAprobado());
    }

    @Override
    public String getEstadoNombre() {
        return "En Desarrollo";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.EN_DESARROLLO;
    }
    
}
