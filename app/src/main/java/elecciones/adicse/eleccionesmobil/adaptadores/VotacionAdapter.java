package elecciones.adicse.eleccionesmobil.adaptadores;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import elecciones.adicse.eleccionesmobil.R;
import elecciones.adicse.eleccionesmobil.model.Voto001;
import elecciones.adicse.eleccionesmobil.databinding.ListViewVotos;



public class VotacionAdapter extends BaseAdapter {


    private Context contexto;
    private Voto001 voto001;
    private ListViewVotos listViewVotos;


    public VotacionAdapter(Context contexto, Voto001 voto001) {
        this.contexto = contexto;
        this.voto001 = voto001;
    }

    @Override
    public int getCount() {
        return voto001.getVoto002s().size();
    }

    @Override
    public Object getItem(int i) {
        return voto001.getVoto002s().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {



        if(convertView == null){
            convertView = LayoutInflater.from(contexto).inflate(R.layout.votacion_lv,null);
            listViewVotos = DataBindingUtil.bind(convertView);

            convertView.setTag(listViewVotos);
        }else{
            listViewVotos = (ListViewVotos) convertView.getTag();
        }

        listViewVotos.setVoto001(voto001);
        listViewVotos.setPosicion(i);
/*
        ImageView imgCandidato = convertView.findViewById(R.id.imgCandidato);

        String encodedImage = voto001.getVoto002s().get(i).getPlantilla002().getCandidato().getFotoBase64();
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        imgCandidato.setImageBitmap(decodedByte);
        */

/*        LayoutInflater inflater = LayoutInflater.from(contexto);
        View viewItem = inflater.inflate(R.layout.votacion_lv,null  );


        listViewVotos = DataBindingUtil.bind(viewItem);
        viewItem.setTag(listViewVotos);


        ImageView imgCandidato = viewItem.findViewById(R.id.imgCandidato);
        TextView txtNombreCandidato = viewItem.findViewById(R.id.txtNombreCandidato);
        TextView txtPartidoPolitico = viewItem.findViewById(R.id.txtPartidoPolitico);
        TextView txtVotos = viewItem.findViewById(R.id.txtVotos);

        String encodedImage = voto001.getVoto002s().get(i).getPlantilla002().getCandidato().getFotoBase64();
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        imgCandidato.setImageBitmap(decodedByte);
        txtNombreCandidato.setText(voto001.getVoto002s().get(i).getPlantilla002().getCandidato().getNombre());
        txtPartidoPolitico.setText(voto001.getVoto002s().get(i).getPlantilla002().getCandidato().getAgrupacion().getDscagrupacion());*/

        return listViewVotos.getRoot();
    }
}
