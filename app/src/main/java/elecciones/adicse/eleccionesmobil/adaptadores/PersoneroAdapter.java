package elecciones.adicse.eleccionesmobil.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import elecciones.adicse.eleccionesmobil.R;
import elecciones.adicse.eleccionesmobil.model.Personero;

public class PersoneroAdapter extends BaseAdapter {

    Context contexto;
    List<Personero> lstPersoneros;


    public PersoneroAdapter(Context contexto, List<Personero> lstPersoneros) {

        this.contexto = contexto;
        this.lstPersoneros = lstPersoneros;
    }


    @Override
    public int getCount() {
        return lstPersoneros.size();
    }

    @Override
    public Object getItem(int i) {
        return lstPersoneros.get(i) ;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        LayoutInflater inflater =  LayoutInflater.from(contexto);
        View itemView = inflater.inflate(R.layout.personeros_lv,null);

        ImageView imagenPersonero = (ImageView) itemView.findViewById(R.id.imgPersonero);
        TextView txtNombrePersonero = (TextView) itemView.findViewById(R.id.txtNombrePersonero );
        TextView txtDniPersonero = (TextView) itemView.findViewById(R.id.txtDniPersonero);

        String encodedImage = lstPersoneros.get(i).getFotoBase64();
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        imagenPersonero.setImageBitmap(decodedByte);
        txtNombrePersonero.setText(lstPersoneros.get(i).getNombrepersonero());
        txtDniPersonero.setText(lstPersoneros.get(i).getIdpersonero());

        return itemView;
    }
}
