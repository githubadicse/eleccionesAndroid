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
import elecciones.adicse.eleccionesmobil.model.MesaDeVotacion;

public class MesaDeVotacionAdapter extends BaseAdapter {

    List<MesaDeVotacion> lstMesaDeVotacion;
    Context contexto;


    public MesaDeVotacionAdapter( Context contexto, List<MesaDeVotacion> lstMesaDeVotacion) {
        this.lstMesaDeVotacion = lstMesaDeVotacion;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return lstMesaDeVotacion.size();
    }

    @Override
    public Object getItem(int i) {
        return lstMesaDeVotacion.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(contexto);

        View itemView = inflater.inflate(R.layout.mesas_lv,null);

        TextView txtNumeroMesa = (TextView) itemView.findViewById(R.id.txtNumeroMesa);
        TextView txtNombrePersonero = (TextView) itemView.findViewById(R.id.txtNombrePersonero);
        TextView txtDniPersonero = (TextView) itemView.findViewById(R.id.txtDniPersonero);
        TextView txtDepartamento = (TextView) itemView.findViewById(R.id.txtDepartamento);
        TextView txtProvincia = (TextView) itemView.findViewById(R.id.txtProvincia);
        TextView txtDistrito = (TextView) itemView.findViewById(R.id.txtDistrito);
        TextView txtLocalDeVotacion = (TextView) itemView.findViewById(R.id.txtLocalDeVotacion);
        //ImageView imgPersoneroMesa = (ImageView) itemView.findViewById(R.id.imgPersoneroMesa);

        txtNumeroMesa.setText(lstMesaDeVotacion.get(i).getNumeroDeMesa().toString());
        txtNombrePersonero.setText( lstMesaDeVotacion.get(i).getPersonero().getNombrepersonero() );
        txtDniPersonero.setText( lstMesaDeVotacion.get(i).getPersonero().getDni() );

        txtDepartamento.setText( lstMesaDeVotacion.get(i).getUbigeo().getDepartamento() );
        txtProvincia.setText( lstMesaDeVotacion.get(i).getUbigeo().getProvincia() );
        txtDistrito.setText( lstMesaDeVotacion.get(i).getUbigeo().getDistrito() );
        txtLocalDeVotacion.setText( lstMesaDeVotacion.get(i).getLocalDeVotacion() );

/*        String encodedImage = lstMesaDeVotacion.get(i).getPersonero().getFotoBase64();
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);*/

/*        imgPersoneroMesa.setImageBitmap( decodedByte );*/

        return itemView;
    }
}
