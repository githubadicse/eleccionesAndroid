package elecciones.adicse.eleccionesmobil.model;

import java.io.Serializable;

public class Personero implements Serializable {



    private String idpersonero;

    private String nombrepersonero;

    private String dni;

    private String fotoBase64;



    public String getIdpersonero() {
        return idpersonero;
    }

    public void setIdpersonero(String idpersonero) {
        this.idpersonero = idpersonero;
    }

    public String getNombrepersonero() {
        return nombrepersonero;
    }

    public void setNombrepersonero(String nombrepersonero) {
        this.nombrepersonero = nombrepersonero;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }
}
