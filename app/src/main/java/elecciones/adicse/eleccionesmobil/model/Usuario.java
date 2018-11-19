package elecciones.adicse.eleccionesmobil.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private Integer idUsuario;
    private String nombreUsuario;


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
