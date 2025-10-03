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
public class EstadoInicio implements ProjectState{
    @Override
    public void siguienteEstado(ProjectContext context) {
        context.setCurrentState(new EstadoFormatoADiligenciado());
    }

    @Override
    public void estadoAnterior(ProjectContext context) {
        // No hay estado anterior al inicio
    }

    @Override
    public String getEstadoNombre() {
        return "Inicio";    
    }

    @Override
    public StatusEnum getStatusEnum() {
        return StatusEnum.INICIO;
    }
}
