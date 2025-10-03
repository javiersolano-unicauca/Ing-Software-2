/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.facade;

import access.models.implement.ProjectModel;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author yezid
 */

/**
 * Subsistema para asignacion de evaluadores
 */
public class EvaluatorAssignmentService {
    
    private List<String> availableEvaluators;
    
    public EvaluatorAssignmentService() {
        this.availableEvaluators = new ArrayList<>();
        initializeEvaluators();
    }
    
    private void initializeEvaluators() {
        availableEvaluators.add("Dr. Julio Ariel Hurtado");
        availableEvaluators.add("Dra. Maria Fernanda Gomez");
        availableEvaluators.add("Dr. Carlos Andres Lopez");
        availableEvaluators.add("Dra. Ana Patricia Rodriguez");
        availableEvaluators.add("Dr. Luis Eduardo Martinez");
    }
    
    public boolean assignEvaluators(ProjectModel project) {
        System.out.println("Asignando evaluadores para: " + project.getTitle());
        
        if (availableEvaluators.isEmpty()) {
            System.out.println("Error: No hay evaluadores disponibles");
            return false;
        }
        
        // Simular asignacion de 2 evaluadores
        List<String> assignedEvaluators = new ArrayList<>();
        
        for (int i = 0; i < Math.min(2, availableEvaluators.size()); i++) {
            String evaluator = availableEvaluators.get(i);
            assignedEvaluators.add(evaluator);
            System.out.println("Evaluador asignado: " + evaluator);
        }
        
        boolean success = !assignedEvaluators.isEmpty();
        System.out.println("Asignacion completada: " + (success ? "EXITOSA" : "FALLIDA"));
        return success;
    }
    
    public List<String> getAvailableEvaluators() {
        return new ArrayList<>(availableEvaluators);
    }
    
    public boolean addEvaluator(String evaluatorName) {
        if (evaluatorName != null && !evaluatorName.trim().isEmpty()) {
            availableEvaluators.add(evaluatorName);
            System.out.println("Evaluador agregado: " + evaluatorName);
            return true;
        }
        return false;
    }
    
    public boolean removeEvaluator(String evaluatorName) {
        boolean removed = availableEvaluators.remove(evaluatorName);
        if (removed) {
            System.out.println("Evaluador removido: " + evaluatorName);
        }
        return removed;
    }
    
    public int getAvailableEvaluatorsCount() {
        return availableEvaluators.size();
    }
}
