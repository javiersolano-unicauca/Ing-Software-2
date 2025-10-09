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
public class EstadoProrrogaSolicitada implements ProjectState{
    @Override
    public void siguienteEstado(ProjectContext context) {
        context.setCurrentState(new EstadoFinalizadoMonografia());
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoEnDesarrollo());
    }

    @Override
    public String getEstadoNombre() {
        return "Prorroga Solicitada";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.PRORROGA_SOLICITADA;
    }
}
