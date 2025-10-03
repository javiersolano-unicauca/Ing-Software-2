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
public class EstadoPresentadoDecanatura implements ProjectState{
    @Override
    public void siguienteEstado(ProjectContext context) {
        context.setCurrentState(new EstadoFechaSustentacionFijada());
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoFinalizadoMonografia());
    }

    @Override
    public String getEstadoNombre() {
        return "Presentado a Decanatura";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.PRESENTADO_DECANATURA;
    }
    
}
