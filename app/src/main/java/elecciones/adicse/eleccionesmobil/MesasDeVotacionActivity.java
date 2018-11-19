package elecciones.adicse.eleccionesmobil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import elecciones.adicse.eleccionesmobil.adaptadores.MesaDeVotacionAdapter;
import elecciones.adicse.eleccionesmobil.model.MesaDeVotacion;
import elecciones.adicse.eleccionesmobil.model.Personero;
import elecciones.adicse.eleccionesmobil.model.Ubigeo;

public class MesasDeVotacionActivity extends AppCompatActivity {

    private ListView lvMesaDeVotacion;
    private String fotoBase64;
    private Personero personero = null; // or other values
    private Boolean flagRegistradoSw;
    private TextView txtMsgMesasPendientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas_de_votacion);
        this.setTitle("Mesas De Votacion Pendientes");

        lvMesaDeVotacion = findViewById(R.id.lv_mesasDeVotacion);
        txtMsgMesasPendientes = (TextView) findViewById(R.id.txtMsgPendiente);

        Button btnMesasRegistradas = (Button)findViewById(R.id.btnMesasRegistradas);

        btnMesasRegistradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filtrarPorMesasRegistradas(view);
            }
        });

        Bundle b = getIntent().getExtras();

        if(b != null){
            personero = (Personero) b.get ("personero");
            //fotoBase64 = personero.getFotoBase64();
            //Toast.makeText(this,personero.getNombrepersonero(),Toast.LENGTH_LONG).show();
/*            getMesas sw = new getMesas(personero);
            sw.execute();*/
        }

        flagRegistradoSw = false;
    }


    @Override
    protected void onPostResume() {

        super.onPostResume();
        System.out.println("ENTRENDO EN RESUMEN DE MESAS DE VOTACION");
        getMesas sw = new getMesas(personero,flagRegistradoSw);
        sw.execute();
    }

    private class getMesas extends AsyncTask<String,String,String>{


        Personero personeroAux;
        HttpURLConnection urlConnection;
        Boolean flagRegistrado ;


        public getMesas(Personero personero,Boolean flagRegistrado) {
            this.personeroAux = personero;
            fotoBase64 = personeroAux.getFotoBase64();
            this.flagRegistrado = flagRegistrado;

        }

        @Override
        protected String doInBackground(String... strings) {



            StringBuilder stringBuilder = new StringBuilder();

            try {
                String idPersonero = personeroAux.getIdpersonero();
                URL url = new URL (UrlConnection.urlConn + "/mesaDeVotacion/getMesasDeVotacionByIdPersonero?idPersonero="+idPersonero+"&flagRegistrado="+this.flagRegistrado);
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestProperty("Authorization", "Bearer " + UrlConnection.token);
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ( (line = reader.readLine()) != null   ){
                    stringBuilder.append(line);
                }
            } catch (MalformedURLException e) {
                Toast.makeText(MesasDeVotacionActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }

            return stringBuilder.toString() ;
        }

        @Override
        protected void onPostExecute(String s) {
            cargarLista(s);
        }



    }


    private void cargarLista(String msgJson)  {
        final List<MesaDeVotacion> lstMesaDeVotacion = new ArrayList<>();
        try {


            JSONArray jsonArr = new JSONArray(msgJson);
            JSONObject jsonObject;
            JSONObject jsonObjectPersonero;
            JSONObject jsonObjectUbigeo;
            MesaDeVotacion mesaDeVotacion=null;
            Ubigeo ubigeo;
            Personero personero;
            if(jsonArr.length() == 0 ){
                txtMsgMesasPendientes.setVisibility(View.VISIBLE);
            }else {
                txtMsgMesasPendientes.setVisibility(View.INVISIBLE);
            }

            for(int i = 0; i < jsonArr.length(); i++){
                jsonObject = jsonArr.getJSONObject(i);

                mesaDeVotacion = new MesaDeVotacion();
                mesaDeVotacion.setIdMesaDeVotacion(jsonObject.getInt("idMesaDeVotacion"));
                mesaDeVotacion.setNumeroDeVotantes(jsonObject.getInt("numeroDeVotantes"));
                mesaDeVotacion.setLocalDeVotacion( jsonObject.getString("localDeVotacion") );
                mesaDeVotacion.setNumeroDeMesa( jsonObject.getString("numeroDeMesa") );

                jsonObjectPersonero = jsonObject.getJSONObject("personero");

                personero = new Personero();
                personero.setIdpersonero( jsonObjectPersonero.getString("idpersonero")  );
                personero.setNombrepersonero( jsonObjectPersonero.getString("nombrepersonero") );
                personero.setDni( jsonObjectPersonero.getString("dni") );
                personero.setFotoBase64(fotoBase64);

                jsonObjectUbigeo = jsonObject.getJSONObject("ubigeo");
                ubigeo = new Ubigeo();
                ubigeo.setIdUbigeo( jsonObjectUbigeo.getInt("idubigeo")  );
                ubigeo.setDscUbigeo ( jsonObjectUbigeo.getString("dscubigeo")  );
                ubigeo.setDepartamento ( jsonObjectUbigeo.getString("departamento")  );
                ubigeo.setProvincia ( jsonObjectUbigeo.getString("provincia")  );
                ubigeo.setDistrito ( jsonObjectUbigeo.getString("distrito")  );

                mesaDeVotacion.setPersonero(personero);
                mesaDeVotacion.setUbigeo(ubigeo);

                lstMesaDeVotacion.add(mesaDeVotacion);

            }

            //Lanzamos el listview
            MesaDeVotacionAdapter adapter ;
            adapter = new MesaDeVotacionAdapter(this,lstMesaDeVotacion);
            lvMesaDeVotacion.setAdapter(adapter);

            lvMesaDeVotacion.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        final int position, long id) {

                    if(flagRegistradoSw.equals(false)){
                        MesaDeVotacion mesaDeVotacionParm = new MesaDeVotacion();
                        mesaDeVotacionParm = lstMesaDeVotacion.get(position);

                        Intent intent = new Intent( MesasDeVotacionActivity.this ,VotoActivity.class);
                        intent.putExtra("mesaDeVotacion", mesaDeVotacionParm );
                        startActivity(intent);
                    }


                }
            });




        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }

    public void filtrarPorMesasRegistradas(View view){


        Button btn = (Button) view.findViewById(R.id.btnMesasRegistradas);
        if(flagRegistradoSw.equals(false)){
            btn.setText("MESAS PENDIENTES");
            this.setTitle("Mesas De Votacion Registradas");

        }else{
            btn.setText("MESAS REGISTRADAS");
            this.setTitle("Mesas De Votacion Pendientes");
        }



        flagRegistradoSw = !flagRegistradoSw;
        getMesas sw = new getMesas(personero,flagRegistradoSw);
        sw.execute();

    }
}
