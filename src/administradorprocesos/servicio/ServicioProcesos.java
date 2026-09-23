package administradorprocesos.servicio;

import administradorprocesos.modelo.ProcesoInfo;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Contiene toda la logica para interactuar con los procesos reales del sistema operativo.
// Usa la API ProcessHandle de Java (disponible desde Java 9), que permite ver y matar
// procesos de verdad, igual que hace el Administrador de Tareas de Windows.
public class ServicioProcesos {

    public List<ProcesoInfo> obtenerProcesos() {
        List<ProcesoInfo> lista = new ArrayList<>();

        ProcessHandle.allProcesses().forEach(handle -> {
            try {
                ProcessHandle.Info info = handle.info();
                String nombreCompleto = info.command().orElse("Desconocido");
                String nombreCorto = extraerNombreCorto(nombreCompleto);

                ProcesoInfo p = new ProcesoInfo();
                p.setPid(handle.pid());
                p.setNombre(nombreCorto);
                p.setUsuario(info.user().orElse("N/D"));
                p.setHoraInicio(info.startInstant()
                        .map(instant -> instant.atZone(ZoneId.systemDefault()).toLocalTime().toString())
                        .orElse("N/D"));
                p.setActivo(handle.isAlive());

                lista.add(p);
            } catch (Exception e) {
                // Algunos procesos del sistema no dan permiso para leer su informacion.
                // Se ignoran para que el programa no se caiga.
            }
        });

        lista.sort(Comparator.comparing(ProcesoInfo::getNombre, String.CASE_INSENSITIVE_ORDER));
        return lista;
    }

    private String extraerNombreCorto(String rutaCompleta) {
        if (rutaCompleta.contains("\\")) {
            return rutaCompleta.substring(rutaCompleta.lastIndexOf("\\") + 1);
        }
        if (rutaCompleta.contains("/")) {
            return rutaCompleta.substring(rutaCompleta.lastIndexOf("/") + 1);
        }
        return rutaCompleta;
    }

    public boolean finalizarProceso(long pid) {
        return ProcessHandle.of(pid)
                .map(handle -> handle.destroy() || handle.destroyForcibly())
                .orElse(false);
    }

    public boolean iniciarProceso(String comando) {
        try {
            new ProcessBuilder(comando).start();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

