package elecciones.adicse.eleccionesmobil;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Dialogo_Voto {

    public interface FinalizaCuadroDialogo{
        void ResutadoCuadroDialogo(String votos);
    }

    private FinalizaCuadroDialogo interfaz;

    public  Dialogo_Voto(Context contexto, FinalizaCuadroDialogo iActividad){

        interfaz = iActividad;

        final Dialog dialog = new Dialog(contexto);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialogo_voto);

        //ImageView imgFoto = (ImageView) dialog.findViewById(R.id.imgFoto);
        final TextView txtVotos = (TextView) dialog.findViewById(R.id.txtVotos);
        Button btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
        Button btnCerrar = (Button) dialog.findViewById(R.id.btnCerrar);
        txtVotos.setInputType(InputType.TYPE_CLASS_NUMBER);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dato = txtVotos.getText().toString();
                interfaz.ResutadoCuadroDialogo(dato);
                dialog.dismiss();
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        txtVotos.setFocusableInTouchMode(true);
        txtVotos.requestFocus();
    }
}
