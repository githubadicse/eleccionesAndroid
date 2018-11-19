package elecciones.adicse.eleccionesmobil.model;

import java.io.Serializable;
import java.util.List;

public class Plantilla001 implements Serializable {
    private Integer idplantilla001;

    private Nivel nivel;


    private List<Plantilla002> plantilla002s;


    private List<Voto001> voto001s;


    public Plantilla001() {
    }

    public Integer getIdplantilla001() {
        return idplantilla001;
    }

    public void setIdplantilla001(Integer idplantilla001) {
        this.idplantilla001 = idplantilla001;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public List<Plantilla002> getPlantilla002s() {
        return plantilla002s;
    }

    public void setPlantilla002s(List<Plantilla002> plantilla002s) {
        this.plantilla002s = plantilla002s;
    }

    public List<Voto001> getVoto001s() {
        return voto001s;
    }

    public void setVoto001s(List<Voto001> voto001s) {
        this.voto001s = voto001s;
    }
}
