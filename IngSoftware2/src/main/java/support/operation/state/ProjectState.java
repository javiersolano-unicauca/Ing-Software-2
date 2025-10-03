/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package support.operation.state;

import access.models.StatusEnum;

/**
 *
 * @author yezid
 */

/**
 * Interfaz para el patrón State que define el comportamiento de los estados del proyecto
 */
public interface ProjectState {
    void siguienteEstado(ProjectContext context);
    void estadoAnterior(ProjectContext context);
    String getEstadoNombre();
    StatusEnum getStatusEnum();
}
