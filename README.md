# Administrador de Procesos (Java)

Aplicación de escritorio en Java (Swing) que permite:
- Ver los procesos reales que están corriendo en el sistema (PID, nombre, usuario, hora de inicio, si está activo).
- Buscar/filtrar procesos por nombre.
- **Finalizar de verdad** un proceso seleccionado (usa `ProcessHandle.destroy()`).
- Iniciar un nuevo proceso escribiendo su nombre o ruta (ej. `notepad.exe`).
- Actualización automática cada 5 segundos.

Requiere **Java 9 o superior** (usa la API `ProcessHandle`). NetBeans moderno ya trae un JDK compatible.

## Paquetes y clases (divididos por parte)
| Clase | Paquete | Contenido | Responsable sugerido |
|---|---|---|---|
| `ProcesoInfo.java` | `administradorprocesos.modelo` | Modelo de datos de un proceso | Integrante 1 |
| `ServicioProcesos.java` | `administradorprocesos.servicio` | Lógica para listar/finalizar/iniciar procesos | Integrante 2 |
| `VentanaPrincipal.java` | `administradorprocesos` | Interfaz gráfica (Swing) y eventos de botones | Integrante 3 |
| `Main.java` | `administradorprocesos` | Punto de entrada | Integrante 4 (o quien arme el repo) |
| `MANUAL_USUARIO.md` | — | Manual de usuario | Integrante encargado de documentación |

## Cómo se comprueba el trabajo de cada quien
Cada integrante trabaja en su propia rama de Git (`git checkout -b nombre-parte`) y comitea
con su propio `git config user.name` / `user.email` (debe coincidir con el correo de su
cuenta de GitHub para que el commit se le atribuya). Al final se integra todo a `main`
mediante Pull Requests. Esto hace que en GitHub, en la pestaña **Insights → Contributors**
del repositorio, se vea automáticamente cuánto aportó cada persona.
