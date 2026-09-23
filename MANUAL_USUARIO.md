# Manual de Usuario — Administrador de Procesos (Java)

## 1. ¿Qué es?
Un programa que muestra, en una tabla, los procesos que están corriendo actualmente
en el sistema operativo, y permite finalizarlos de verdad o iniciar programas nuevos.

## 2. Cómo iniciar el programa
Desde NetBeans: abre el proyecto y presiona el botón ▶ (Run) o F6.
Se abrirá una ventana con la tabla de procesos.

## 3. Partes de la pantalla
- **Buscar**: escribe el nombre de un proceso y presiona Enter para filtrar la tabla.
- **Actualizar**: vuelve a leer la lista de procesos del sistema.
- **Finalizar proceso**: cierra el proceso seleccionado en la tabla.
- **Programa + Iniciar**: escribe el nombre o ruta de un programa (ej. `notepad.exe`)
  y presiona el botón para abrirlo.
- **Tabla principal**: muestra PID, nombre, usuario, hora de inicio y si está activo.
- La lista se actualiza sola cada 5 segundos.

## 4. Cómo finalizar un proceso
1. Haz clic sobre la fila del proceso que quieres cerrar.
2. Presiona **"Finalizar proceso"**.
3. Confirma en el cuadro de diálogo.
4. Si sale el mensaje de que no se pudo finalizar, es porque ese proceso necesita
   permisos de administrador: cierra NetBeans y ábrelo de nuevo con "Ejecutar como
   administrador", o corre el `.jar` final como administrador.

## 5. Cómo iniciar un proceso nuevo
1. Escribe el nombre o ruta del programa (ejemplo: `notepad.exe` o
   `C:\Windows\System32\calc.exe`).
2. Presiona **"Iniciar"**.

## 6. Errores comunes
| Problema | Causa probable | Solución |
|---|---|---|
| "No se pudo finalizar el proceso" | Falta de permisos | Ejecutar como administrador |
| "No se pudo iniciar el proceso" | Ruta o nombre incorrecto | Verifica que el programa exista |
| La tabla sale casi vacía o con "N/D" | Windows restringe leer info de ciertos procesos sin permisos de admin | Es normal para procesos del sistema; ejecutar como admin para ver más detalle |
