/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.solid.dip.domain.main;

//import co.unicauca.solid.dip.domain.EnumPrograma;
//import co.unicauca.solid.dip.domain.EnumRol;
//import co.unicauca.solid.dip.domain.Usuario;
//import co.unicauca.solid.dip.domain.access.UsuarioRepository;
//import co.unicauca.solid.dip.domain.controller.validations.UserValidation;
//import co.unicauca.solid.dip.domain.exception.UserException;
//import co.unicauca.solid.dip.domain.service.UsuarioService;
//import java.util.Arrays;
//import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class ClientMain {
// public static void main(String[] args) throws UserException {
//        UsuarioRepository repo = new UsuarioRepository();
//        UsuarioService service = new UsuarioService(repo);
//        Usuario u = new Usuario();
//        Scanner sc = new Scanner(System.in);
//
//        
//
//         while (true) {
//            System.out.println("----------------------------------------");
//            System.out.println("BIENVENIDO AL SISTEMA DE USUARIOS");
//            System.out.println("1. Registrar usuario");
//            System.out.println("2. Iniciar sesión");
//            System.out.println("3. Salir");
//            System.out.println("----------------------------------------");
//            System.out.print("Seleccione una opcion: ");
//            
//            if (!sc.hasNextInt()) {
//                System.out.println("Opción inválida. Por favor, ingrese un número.");
//                sc.next(); // Limpiar el buffer del scanner
//                continue;
//            }
//            
//            int op = sc.nextInt(); sc.nextLine(); // Consumir el salto de línea
//
//            if (op == 3) {
//                System.out.println("Saliendo del programa. ¡Hasta luego!");
//                break;
//            }
//
//            if (op == 1) {
//                System.out.println("--- REGISTRO DE USUARIO ---");
//                System.out.print("Nombre: ");
//                String nombres = sc.nextLine();
//
//                System.out.print("Apellido: ");
//                String apellidos = sc.nextLine();
//                
//                // seleccionar programa
//                EnumPrograma programa = null;
//                do {
//                    System.out.println("Seleccione programa:");
//                    for (EnumPrograma p : EnumPrograma.values()) {
//                        System.out.println("- " + p.getNombre());
//                    }
//                    System.out.print("Opcion: ");
//                    String programaInput = sc.nextLine();
//                    programa = Arrays.stream(EnumPrograma.values())
//                            .filter(p -> p.getNombre().equalsIgnoreCase(programaInput))
//                            .findFirst()
//                            .orElse(null);
//                    if (programa == null) {
//                        System.out.println("Programa invalido. Intente de nuevo.");
//                    }
//                } while (programa == null);
//
//                // seleccionar rol
//                EnumRol rol = null;
//                do {
//                    System.out.print("Seleccione rol (Estudiante/Docente): ");
//                    String rolInput = sc.nextLine();
//                    if (rolInput.equalsIgnoreCase("estudiante")) {
//                        rol = EnumRol.ESTUDIANTE;
//                    } else if (rolInput.equalsIgnoreCase("docente")) {
//                        rol = EnumRol.DOCENTE;
//                    } else {
//                        System.out.println("Rol invalido. Intente de nuevo.");
//                    }
//                } while (rol == null);
//                
//                // celular
//                System.out.print("Celular: ");
//                String celular = sc.nextLine();
//
//                // validar email
//                
//                String email;
//                while (true) {
//                    System.out.print("Email institucional: ");
//                    email = sc.nextLine();
//                    if (email.endsWith("@unicauca.edu.co")){
//                    System.out.println("El correo debe ser institucional (@unicauca.edu.co).");
//                    continue;
//                    }
//                //verificar si ya existe 
//                if (service.existeEmail(email)) {
//                  System.out.println("Error: Este email ya esta registrado. Use otro email.");
//                } else {
//                  break; // Email valido y disponible
//                  }
//                }
//
//                // validar contraseña
//                String password;
//                while (true) {
//                    System.out.print("Contraseña: ");
//                    password = sc.nextLine();
//                    Usuario tempUser = new Usuario();
//                    tempUser.setPassword(password);
//                    
//                    try {
//                        UserValidation.validatePassword(tempUser);
//                        break; // Si no lanza excepcion, la contrasena es valida
//                    } catch (Exception e) {
//                        System.out.println("Error en contrasena: " + e.getMessage());
//                    }
//                }
//
//                // Crear y registrar usuario
//                u = new Usuario( nombres, apellidos, programa, rol, email, password, celular);
//                if (service.registrarUsuario(u)) {
//                    System.out.println("Usuario registrado con éxito.");
//                } else {
//                    System.out.println("Error al registrar usuario.");
//                }
//            } else if (op == 2) {
//                System.out.println("--- INICIO DE SESION ---");
//                System.out.print("Email: ");
//                String email = sc.nextLine();
//                System.out.print("Contraseña: ");
//                String pass = sc.nextLine();
//                
//                
//                
//                u = service.login(email, pass);
//                if (u != null) {
//                    System.out.println("Bienvenido " + u.getNombres() + " - Rol: " + u.getRol());
//                    if (u.getRol().getNombre().equalsIgnoreCase("Estudiante")) {
//                        System.out.println("Menú estudiante: [1] Ver estado trabajo de grado, [2] Iniciar nuevo...");
//                    } else if (u.getRol().getNombre().equalsIgnoreCase("Docente")) {
//                        System.out.println("Menu docente: [1] Evaluar anteproyecto, [2] Evaluar monografía...");
//                    }
//                } else {
//                    System.out.println("Credenciales invalidas.");
//                }
//            } else {
//                System.out.println("Opcion no valida. Intente de nuevo.");
//            }
//        }
//    }
}
