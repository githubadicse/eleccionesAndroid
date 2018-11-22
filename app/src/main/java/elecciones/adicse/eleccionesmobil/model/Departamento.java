package elecciones.adicse.eleccionesmobil.model;

import java.io.Serializable;
import java.util.List;

public class Departamento implements Serializable {


    public Departamento() {
    }

    private String iddepartamento;

    private String dscdepartamento;

    private List<Provincia> provincias;


    public String getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(String iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public String getDscdepartamento() {
        return dscdepartamento;
    }

    public void setDscdepartamento(String dscdepartamento) {
        this.dscdepartamento = dscdepartamento;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }
}
