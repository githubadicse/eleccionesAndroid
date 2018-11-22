package elecciones.adicse.eleccionesmobil.model;

import java.io.Serializable;
import java.util.List;

public class CentroDeVotacion implements Serializable {


    public CentroDeVotacion() {
    }

    private String idCentroDeVotacion;

    private String nombreCentroDeVotacion;

    private Distrito distrito;

    private List<MesaDeVotacion> mesaDeVotacions;


    public String getIdCentroDeVotacion() {
        return idCentroDeVotacion;
    }

    public void setIdCentroDeVotacion(String idCentroDeVotacion) {
        this.idCentroDeVotacion = idCentroDeVotacion;
    }

    public String getNombreCentroDeVotacion() {
        return nombreCentroDeVotacion;
    }

    public void setNombreCentroDeVotacion(String nombreCentroDeVotacion) {
        this.nombreCentroDeVotacion = nombreCentroDeVotacion;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public List<MesaDeVotacion> getMesaDeVotacions() {
        return mesaDeVotacions;
    }

    public void setMesaDeVotacions(List<MesaDeVotacion> mesaDeVotacions) {
        this.mesaDeVotacions = mesaDeVotacions;
    }
}
