
# Administrador de Procesos (Java)
Aplicación de escritorio en Java (Swing) que permite:
- Ver los procesos reales que están corriendo en el sistema (PID, nombre, usuario, hora de inicio, si está activo).
- Buscar/filtrar procesos por nombre.
- **Finalizar de verdad** un proceso seleccionado (usa `ProcessHandle.destroy()`).
- Iniciar un nuevo proceso escribiendo su nombre o ruta (ej. `notepad.exe`).
- Actualización automática cada 5 segundos.
- 
# Paquetes y clases (divididos por parte)



| Clase | Paquete | Contenido | Responsable sugerido |
|---|---|---|---|
| `ProcesoInfo.java` | `administradorprocesos.modelo` | Modelo de datos de un proceso | Jeshua |
| `ServicioProcesos.java` | `administradorprocesos.servicio` | Lógica para listar/finalizar/iniciar procesos | Hugo |
| `VentanaPrincipal.java` | `administradorprocesos` | Interfaz gráfica (Swing) y eventos de botones | Jeshua |
| `Main.java` | `administradorprocesos` | Punto de entrada | Hugo |
| `MANUAL_USUARIO.md` | — | Manual de usuario | Hugo |



