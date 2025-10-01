📌 **Introducción**

Este taller busca fortalecer la comprensión y aplicación de los requisitos funcionales y no funcionales en el desarrollo de software.
A través de la documentación de decisiones arquitectónicas, la definición de historias de usuario y la identificación de atributos de calidad, los estudiantes podrán relacionar la teoría con la práctica y sentar las bases para el proyecto de curso en Ingeniería de Software II.

📖 **Capítulo 1 – Decisiones**
🔧 Tecnologías Seleccionadas

Backend: Java con Spring Boot, por su robusta documentación y utilidades para desarrollar servicios web eficientes y seguros.

Frontend: Bootstrap, para generar independencia de las tecnologías del backend y comunicación con fetchAPI.

Tipo de aplicación: Aplicación Web, dado que existirán diferentes actores que no estarán conectados a la misma red.

Base de datos: MySQL, ya que los datos tienen relaciones y la herramienta ofrece una buena relación costo-beneficio en términos de licencias.

**📂 Artefactos del Proyecto**

Épicas: https://docs.google.com/spreadsheets/d/1cOphUmauQRl-LjGOENtXSeC0xSfy2gzO/edit?usp=sharing&ouid=110377452110604466779&rtpof=true&sd=true

Historias de Usuario: https://docs.google.com/spreadsheets/d/1R9fIHc27YjlVK_t2JLks6WxTWJ2MViOo/edit?usp=sharing&ouid=110377452110604466779&rtpof=true&sd=true

**🎯 Atributos de Calidad Identificados**

-Extensible

-Modificable

-Seguro

**📊 Escenarios de Calidad**
**1. Extensibilidad**
Porción del Escenario	Valor
Fuente	Administrador del sistema
Estímulo	Se requiere adicionar notificaciones automáticas a jurados al subir documentos
Artefacto	Código fuente del sistema
Entorno	Sistema en mantenimiento planificado
Respuesta	Se adiciona la funcionalidad reutilizando componentes existentes
Medida de Respuesta	Implementación en máximo 1 semana, sin afectar funcionalidades previas
**2. Seguridad**
Porción del Escenario	Valor
Fuente	Usuario externo con correo no institucional
Estímulo	Intenta iniciar sesión con un correo personal (@gmail.com, @hotmail.com)
Artefacto	Módulo de autenticación del sistema
Entorno	Sistema en operación normal
Respuesta	El sistema rechaza la autenticación y muestra un mensaje de error
Medida de Respuesta	100% de intentos de acceso no autorizados bloqueados
**3. Modificabilidad**
Porción del Escenario	Valor
Fuente	Equipo de desarrollo
Estímulo	Se solicita modificar la funcionalidad de generación de reportes cambiando el formato de salida a PDF
Artefacto	Sistema de gestión de procesos del trabajo de grado
Entorno	Sistema en producción (estudiantes, profesores y administrativos)
Respuesta	La modificación se incorpora encapsulando la lógica de generación de reportes en un módulo independiente
Medida de Respuesta	Implementación en máximo 2 semanas, modificando menos del 10% del código base
**🏗️ Tácticas de Arquitectura
Caso 1: Extensibilidad**

**Táctica:** Increase Cohesion (Incrementar cohesión)

Mantener responsabilidades claras en cada módulo.

Facilita la adición de funcionalidades como gestión de entregas, asignación de jurados o notificaciones automáticas.

Minimiza riesgos de errores colaterales y facilita la evolución del sistema.

**Caso 2: Seguridad**

**Táctica:** Resist Attacks → Authenticate Actors

Autenticación de usuarios con correos institucionales (@unicauca.edu.co).

Verificación de credenciales contra un sistema confiable (autenticación de la universidad).

Evita accesos indebidos y protege información sensible (actas, documentos de grado).

**Caso 3: Modificabilidad**

**Táctica:** Reduce Coupling → Encapsulate

Encapsular cambios dentro de módulos específicos.

**Ejemplo:** generar reportes en formato PDF sin afectar otros módulos.

Mantiene arquitectura organizada y fácil de extender en el futuro.

**📚 Referencias**

Bass, L., Clements, P., & Kazman, R. (2013). Software Architecture in Practice (3rd Edition).

Amazon Web Services. (s.f.). ¿Cuál es la diferencia entre las aplicaciones web, las aplicaciones nativas y las aplicaciones híbridas?
