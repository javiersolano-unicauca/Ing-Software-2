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
public class EstadoSustentacionPublica implements ProjectState {
    @Override
    public void siguienteEstado(ProjectContext context) {
        // Simular resultado de sustentación
        if (Math.random() > 0.2) {
            context.setCurrentState(new EstadoSustentacionAprobada());
        } else {
            context.setCurrentState(new EstadoSustentacionRechazada());
        }
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        context.setCurrentState(new EstadoFechaSustentacionFijada());
    }

    @Override
    public String getEstadoNombre() {
        return "Sustentación Pública";
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.SUSTENTACION_PUBLICA;
    }
    
}
