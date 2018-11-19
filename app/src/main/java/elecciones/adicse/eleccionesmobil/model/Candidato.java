package elecciones.adicse.eleccionesmobil.model;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import elecciones.adicse.eleccionesmobil.R;

public class Candidato implements Serializable {

    private Integer idcandidato;

    private String foto;

    private String nombre;

    private String  fotoBase64;

    private Agrupacion agrupacion;

    private List<Plantilla002> plantilla002s;


    private static   int[] image_array;

    public Candidato() {
        image_array = new int[] { R.drawable.bogarin , R.drawable.grundel,
                R.drawable.votoblanco ,R.drawable.votonulo };
    }


    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public Integer getIdcandidato() {
        return idcandidato;
    }

    public void setIdcandidato(Integer idcandidato) {
        this.idcandidato = idcandidato;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Agrupacion getAgrupacion() {
        return agrupacion;
    }

    public void setAgrupacion(Agrupacion agrupacion) {
        this.agrupacion = agrupacion;
    }

    public List<Plantilla002> getPlantilla002s() {
        return plantilla002s;
    }

    public void setPlantilla002s(List<Plantilla002> plantilla002s) {
        this.plantilla002s = plantilla002s;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        //Logic
        if(imageUrl.equals("BOGARIN")){
            Picasso.with(view.getContext()).load(image_array[0]).placeholder(R.drawable.ic_launcher_background).into(view);
        }
        if(imageUrl.equals("GRUNDEL")){
            Picasso.with(view.getContext()).load(image_array[1]).placeholder(R.drawable.ic_launcher_background).into(view);
        }
        if(imageUrl.equals("BLANCO")){
            Picasso.with(view.getContext()).load(image_array[2]).placeholder(R.drawable.ic_launcher_background).into(view);
        }
        if(imageUrl.equals("NULO")){
            Picasso.with(view.getContext()).load(image_array[3]).placeholder(R.drawable.ic_launcher_background).into(view);
        }


    }

    public static int getDrawableIdFromFileName(Context context, String nameOfDrawable) {
        return context.getResources().getIdentifier(nameOfDrawable, "drawable", context.getPackageName());
    }
}
