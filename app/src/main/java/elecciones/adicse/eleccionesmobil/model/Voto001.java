package elecciones.adicse.eleccionesmobil.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.List;

public class Voto001 extends BaseObservable implements Serializable {

    private Integer idvoto001;

    private Timestamp alta;

    private Boolean flagRegistrado;

    //private Integer idubigeo;

    //private Integer nromesa;

    private MesaDeVotacion mesaDeVotacion;


    private Plantilla001 plantilla001;

    private List<Voto002> voto002s;


    public Voto001() {
    }

    public Integer getIdvoto001() {
        return idvoto001;
    }

    public void setIdvoto001(Integer idvoto001) {
        this.idvoto001 = idvoto001;
    }

    public Timestamp getAlta() {
        return alta;
    }

    public void setAlta(Timestamp alta) {
        this.alta = alta;
    }

    public Boolean getFlagRegistrado() {
        return flagRegistrado;
    }

    public void setFlagRegistrado(Boolean flagRegistrado) {
        this.flagRegistrado = flagRegistrado;
    }



    public MesaDeVotacion getMesaDeVotacion() {
        return mesaDeVotacion;
    }

    public void setMesaDeVotacion(MesaDeVotacion mesaDeVotacion) {
        this.mesaDeVotacion = mesaDeVotacion;
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
