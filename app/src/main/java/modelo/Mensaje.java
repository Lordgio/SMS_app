package modelo;

/**
 * Created by Jorge on 08/04/2017.
 */

public class Mensaje {

    private String origen;
    private String mensaje;

    public Mensaje(String origen, String mensaje) {
        this.origen = origen;
        this.mensaje = mensaje;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
