package elecciones.adicse.eleccionesmobil.model;

import java.io.Serializable;
import java.util.List;

public class Agrupacion implements Serializable {

    private Integer idagrupacion;

    private String dscagrupacion;

    private String logo;

    private List<Candidato> candidatos;

    private String fotoBase64;


    public Agrupacion() {
    }

    public Integer getIdagrupacion() {
        return idagrupacion;
    }

    public void setIdagrupacion(Integer idagrupacion) {
        this.idagrupacion = idagrupacion;
    }

    public String getDscagrupacion() {
        return dscagrupacion;
    }

    public void setDscagrupacion(String dscagrupacion) {
        this.dscagrupacion = dscagrupacion;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }
}
