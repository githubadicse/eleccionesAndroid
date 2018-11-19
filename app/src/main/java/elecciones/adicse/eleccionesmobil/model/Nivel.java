package elecciones.adicse.eleccionesmobil.model;

import java.io.Serializable;
import java.util.List;

public class Nivel implements Serializable {

    private Integer idnivel;

    private String dscnivel;

    private List<Plantilla001> plantilla001s;


    public Nivel() {
    }

    public Integer getIdnivel() {
        return idnivel;
    }

    public void setIdnivel(Integer idnivel) {
        this.idnivel = idnivel;
    }

    public String getDscnivel() {
        return dscnivel;
    }

    public void setDscnivel(String dscnivel) {
        this.dscnivel = dscnivel;
    }

    public List<Plantilla001> getPlantilla001s() {
        return plantilla001s;
    }

    public void setPlantilla001s(List<Plantilla001> plantilla001s) {
        this.plantilla001s = plantilla001s;
    }
}
