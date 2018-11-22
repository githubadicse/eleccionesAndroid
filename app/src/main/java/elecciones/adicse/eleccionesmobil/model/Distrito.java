package elecciones.adicse.eleccionesmobil.model;

import java.util.List;

public class Distrito {


    public Distrito() {
    }

    private String iddistrito;

    private String dscdistrito;

    private List<CentroDeVotacion> centroDeVotacions;


    public String getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(String iddistrito) {
        this.iddistrito = iddistrito;
    }

    public String getDscdistrito() {
        return dscdistrito;
    }

    public void setDscdistrito(String dscdistrito) {
        this.dscdistrito = dscdistrito;
    }

    public List<CentroDeVotacion> getCentroDeVotacions() {
        return centroDeVotacions;
    }

    public void setCentroDeVotacions(List<CentroDeVotacion> centroDeVotacions) {
        this.centroDeVotacions = centroDeVotacions;
    }
}
