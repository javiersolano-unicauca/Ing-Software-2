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
public class EstadoResolucionAprobado implements ProjectState{
    @Override
    public void siguienteEstado(ProjectContext context) {
        context.setCurrentState(new EstadoEnDesarrollo());
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoAprobadoConsejo());
    }

    @Override
    public String getEstadoNombre() {
        return "Resolucion Aprobado";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.RESOLUCION_APROBADO;
    }
    
}
