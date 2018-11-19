package elecciones.adicse.eleccionesmobil.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


import java.io.Serializable;




public class Voto002  extends BaseObservable implements Serializable {

    private String idvoto002;


    private Integer voto;


    private Plantilla002 plantilla002;


    private Voto001 voto001;


    public Voto002() {


    }

    public Voto002(String idvoto002, Integer voto, Plantilla002 plantilla002, Voto001 voto001) {
        this.idvoto002 = idvoto002;
        this.voto = voto;
        this.plantilla002 = plantilla002;
        this.voto001 = voto001;
    }

    public String getIdvoto002() {
        return idvoto002;
    }

    public void setIdvoto002(String idvoto002) {
        this.idvoto002 = idvoto002;
    }


    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;

    }

    public Plantilla002 getPlantilla002() {
        return plantilla002;
    }

    public void setPlantilla002(Plantilla002 plantilla002) {
        this.plantilla002 = plantilla002;
    }

    public Voto001 getVoto001() {
        return voto001;
    }

    public void setVoto001(Voto001 voto001) {
        this.voto001 = voto001;
    }
}
