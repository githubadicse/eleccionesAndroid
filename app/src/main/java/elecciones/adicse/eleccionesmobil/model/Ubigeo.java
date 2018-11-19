package elecciones.adicse.eleccionesmobil.model;

import java.io.Serializable;

public class Ubigeo implements Serializable {

    private Integer idUbigeo;
    private String dscUbigeo;
    private Integer totalelectores;
    private String departamento;
    private String provincia;
    private String distrito;


    public Ubigeo() {
    }

    public Ubigeo(Integer idUbigeo, String dscUbigeo, Integer totalelectores, String departamento, String provincia, String distrito) {
        this.idUbigeo = idUbigeo;
        this.dscUbigeo = dscUbigeo;
        this.totalelectores = totalelectores;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
    }


    public Integer getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(Integer idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public String getDscUbigeo() {
        return dscUbigeo;
    }

    public void setDscUbigeo(String dscUbigeo) {
        this.dscUbigeo = dscUbigeo;
    }

    public Integer getTotalelectores() {
        return totalelectores;
    }

    public void setTotalelectores(Integer totalelectores) {
        this.totalelectores = totalelectores;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
}
