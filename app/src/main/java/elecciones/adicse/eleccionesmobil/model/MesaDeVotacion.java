package elecciones.adicse.eleccionesmobil.model;

import java.io.Serializable;

public class MesaDeVotacion implements Serializable {


    private Integer idMesaDeVotacion;
    private Ubigeo ubigeo;
    private Personero personero;
    private Integer numeroDeVotantes;
    private String numeroDeMesa;

    private String localDeVotacion;

    private Boolean flagRegistrado;

    public MesaDeVotacion() {

    }

    public MesaDeVotacion(Integer idMesaDeVotacion, Ubigeo ubigeo, Personero personero, Integer numeroDeVotantes) {
        this.idMesaDeVotacion = idMesaDeVotacion;
        this.ubigeo = ubigeo;
        this.personero = personero;
        this.numeroDeVotantes = numeroDeVotantes;
    }

    public Integer getIdMesaDeVotacion() {
        return idMesaDeVotacion;
    }

    public void setIdMesaDeVotacion(Integer idMesaDeVotacion) {
        this.idMesaDeVotacion = idMesaDeVotacion;
    }

    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    public Personero getPersonero() {
        return personero;
    }

    public void setPersonero(Personero personero) {
        this.personero = personero;
    }

    public Integer getNumeroDeVotantes() {
        return numeroDeVotantes;
    }

    public void setNumeroDeVotantes(Integer numeroDeVotantes) {
        this.numeroDeVotantes = numeroDeVotantes;
    }

    public String getNumeroDeMesa() {
        return numeroDeMesa;
    }

    public void setNumeroDeMesa(String numeroDeMesa) {
        this.numeroDeMesa = numeroDeMesa;
    }

    public String getLocalDeVotacion() {
        return localDeVotacion;
    }

    public void setLocalDeVotacion(String localDeVotacion) {
        this.localDeVotacion = localDeVotacion;
    }

    public Boolean getFlagRegistrado() {
        return flagRegistrado;
    }

    public void setFlagRegistrado(Boolean flagRegistrado) {
        this.flagRegistrado = flagRegistrado;
    }
}
