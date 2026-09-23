

Aplicación de escritorio en Java (Swing) que permite:
- Ver los procesos reales que están corriendo en el sistema (PID, nombre, usuario, hora de inicio, si está activo).
- Buscar/filtrar procesos por nombre.
- **Finalizar de verdad** un proceso seleccionado (usa `ProcessHandle.destroy()`).
- Iniciar un nuevo proceso escribiendo su nombre o ruta (ej. `notepad.exe`).
- Actualización automática cada 5 segundos.

Requiere **Java 9 o superior** (usa la API `ProcessHandle`). NetBeans moderno ya trae un JDK compatible.


| Clase | Paquete | Contenido | Responsable sugerido |
|---|---|---|---|
| `ProcesoInfo.java` | `administradorprocesos.modelo` | Modelo de datos de un proceso | Jeshua |
| `ServicioProcesos.java` | `administradorprocesos.servicio` | Lógica para listar/finalizar/iniciar procesos | Hugo |
| `VentanaPrincipal.java` | `administradorprocesos` | Interfaz gráfica (Swing) y eventos de botones | Jeshua |
| `Main.java` | `administradorprocesos` | Punto de entrada | Hugo |
| `MANUAL_USUARIO.md` | — | Manual de usuario | Hugo |


Cada integrante trabaja en su propia rama de Git (`git checkout -b nombre-parte`) y comitea
con su propio `git config user.name` / `user.email` (debe coincidir con el correo de su
cuenta de GitHub para que el commit se le atribuya). Al final se integra todo a `main`
mediante Pull Requests. Esto hace que en GitHub, en la pestaña **Insights → Contributors**
del repositorio, se vea automáticamente cuánto aportó cada persona.
