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
public class EstadoSustentacionRechazada implements ProjectState {
    @Override
    public void siguienteEstado(ProjectContext context) {
        // Estado final - proyecto rechazado en sustentación
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoSustentacionPublica());
    }

    @Override
    public String getEstadoNombre() {
        return "Sustentacion Rechazada";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.SUSTENTACION_RECHAZADA;
    }
    
}
