package elecciones.adicse.eleccionesmobil.model;

import java.io.Serializable;
import java.util.List;

public class Plantilla002 implements Serializable {

    private String idplantilla002;

    private Integer orden;


    private Candidato candidato;

    private Plantilla001 plantilla001;

    private List<Voto002> voto002s;


    public Plantilla002() {
    }

    public String getIdplantilla002() {
        return idplantilla002;
    }

    public void setIdplantilla002(String idplantilla002) {
        this.idplantilla002 = idplantilla002;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Plantilla001 getPlantilla001() {
        return plantilla001;
    }

    public void setPlantilla001(Plantilla001 plantilla001) {
        this.plantilla001 = plantilla001;
    }

    public List<Voto002> getVoto002s() {
        return voto002s;
    }

    public void setVoto002s(List<Voto002> voto002s) {
        this.voto002s = voto002s;
    }
}
