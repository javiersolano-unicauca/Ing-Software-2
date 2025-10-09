/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.state;

import access.models.implement.ProjectModel;
/**
 *
 * @author yezid
 */

/**
 * Contexto que mantiene una referencia al estado actual
 */
public class ProjectContext {
    private ProjectState currentState;
    private ProjectModel project;
    private int intentosCorreccion;

    public ProjectContext(ProjectModel project) {
        this.project = project;
        this.intentosCorreccion = 0;
        // Inicializar con el estado correspondiente al StatusEnum del proyecto
        this.currentState = StateFactory.createState(project.getStatus());
    }

    public void siguienteEstado() {
        currentState.siguienteEstado(this);
    }

    public void estadoAnterior() {
        currentState.estadoAnterior(this);
    }

    public void corregir() {
        intentosCorreccion++;
        if (intentosCorreccion > 3) {
            System.out.println("Se supero el numero maximo de correcciones. Rechazado.");
            setCurrentState(new EstadoRechazadoComite());
        } else {
            System.out.println("Proyecto devuelto para correccion. Intento " + intentosCorreccion);
            setCurrentState(new EstadoCorreccionesComite());
        }
    }

    // Getters y Setters
    public ProjectState getCurrentState() { return currentState; }
    public void setCurrentState(ProjectState state) { 
        this.currentState = state; 
        this.project.setStatus(state.getStatusEnum());
    }
    public ProjectModel getProject() { return project; }
    public int getIntentosCorreccion() { return intentosCorreccion; }
    public void setIntentosCorreccion(int intentos) { this.intentosCorreccion = intentos; }
}
