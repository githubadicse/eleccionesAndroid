package elecciones.adicse.eleccionesmobil;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import elecciones.adicse.eleccionesmobil.adaptadores.VotacionAdapter;
import elecciones.adicse.eleccionesmobil.model.Agrupacion;
import elecciones.adicse.eleccionesmobil.model.Candidato;
import elecciones.adicse.eleccionesmobil.model.MesaDeVotacion;
import elecciones.adicse.eleccionesmobil.model.Nivel;
import elecciones.adicse.eleccionesmobil.model.Plantilla001;
import elecciones.adicse.eleccionesmobil.model.Plantilla002;
import elecciones.adicse.eleccionesmobil.model.Voto001;
import elecciones.adicse.eleccionesmobil.model.Voto002;




public class VotoActivity extends AppCompatActivity implements Dialogo_Voto.FinalizaCuadroDialogo {



    private Voto001 voto001;


    private ListView lvVoto ;

    Context contexto;

    VotacionAdapter adapter;

    Integer posicionItem;

    Button btnGrabarActa;

    ProgressBar progresBarActa;

    private View viewItemSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voto);


        contexto = this;
        btnGrabarActa = (Button) findViewById(R.id.btnGrabarActa);
        progresBarActa = (ProgressBar) findViewById(R.id.progressBarActa);

        btnGrabarActa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grabarActa();
            }
        });

        Bundle b = getIntent().getExtras();
        MesaDeVotacion mesaDeVotacion = null; // or other values
        if(b != null){
            mesaDeVotacion = (MesaDeVotacion) b.get("mesaDeVotacion");
        }

        lvVoto = findViewById(R.id.lv_votos );

        MostarListaDeAgrupaciones ex = new MostarListaDeAgrupaciones(mesaDeVotacion);
        ex.execute();

    }


    private void  grabarActa(){



        GrabarActaAsyn ex = new GrabarActaAsyn(this.voto001);
        ex.execute();



    }

    private class GrabarActaAsyn extends AsyncTask<String,String,String> {

        Voto001 voto001Create;
        HttpURLConnection urlConnection;
        String jsonStr ;

        ObjectMapper mapperObj = new ObjectMapper();
        JSONObject jsonObj;

        public GrabarActaAsyn(Voto001 voto001) {
            progresBarActa.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);





            this.voto001Create = voto001;
            this.voto001Create.getMesaDeVotacion().setPersonero(null);
            this.voto001Create.getMesaDeVotacion().setUbigeo(null);
            for(Voto002 row: this.voto001Create.getVoto002s()){

                row.getPlantilla002().setCandidato(null);
            }


            System.out.println("Impimiendo json :"  );
            try {
                // get Employee object as a json string
                this.jsonStr = mapperObj.writeValueAsString(this.voto001Create);

                jsonObj = new JSONObject(this.jsonStr);
                //System.out.println("Impimiendo json :"  + jsonStr);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... strings) {




            StringBuilder stringBuilder = new StringBuilder();
            System.out.print(this.jsonObj.toString());
            try {
                System.out.println("Inicialdo conexion para grabar");
                URL url = new URL (UrlConnection.urlConn + "/voto001/create");
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestProperty("Authorization", "Bearer " + UrlConnection.token);
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("POST");
                //make some HTTP header nicety
                urlConnection.setRequestProperty("Content-Type", "application/json");

                urlConnection.connect();

                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(this.jsonStr.toString());

                writer.flush();
                writer.close();
                os.close();

                int responseCode = urlConnection.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK){

                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast toast = Toast.makeText(getApplicationContext(), "EL ACTA SE GRABO CORRECTAMENTE", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    });


                }else {
                    System.out.print("respuesta codigo : " + responseCode);
                }

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ( (line = reader.readLine()) != null   ){
                    stringBuilder.append(line);
                }
            } catch (MalformedURLException e) {
                Toast.makeText(VotoActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                //Toast.makeText(VotoActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT);
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
                System.out.print("cerrando conexion");
            }

            return stringBuilder.toString() ;
        }

        @Override
        protected void onPreExecute() {



        }

        @Override
        protected void onPostExecute(String s) {
            progresBarActa.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            finish();
        }

        @Override
        protected void onProgressUpdate(String... values) {

            progresBarActa.setProgress(View.VISIBLE);

        }
    }


    private class MostarListaDeAgrupaciones extends AsyncTask<String,String,String>{

        //PLANTILLA PARA GOBIERNOS REGIONALES
        Integer idPlantilla = 1;

        MesaDeVotacion mesaDeVotacion;




        HttpURLConnection urlConnection;


        public MostarListaDeAgrupaciones(MesaDeVotacion mesaDeVotacion) {
            this.mesaDeVotacion = mesaDeVotacion;
            progresBarActa.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();

            try {
                System.out.println("Inicialdo conexion");
                URL url = new URL (UrlConnection.urlConn + "/plantilla001/edit?id="+this.idPlantilla);
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
                Toast.makeText(VotoActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }

            return stringBuilder.toString() ;
        }


        @Override
        protected void onPostExecute(String s) {
            cargarModeloConDatos(s,mesaDeVotacion);
            progresBarActa.setVisibility(View.INVISIBLE);
        }
    }

    private void cargarModeloConDatos(String msgJson,MesaDeVotacion mesaDeVotacion){
        //System.out.print(msgJson);
        try {
            Plantilla001 plantilla001 = new Plantilla001();
            Nivel nivel;
            Plantilla002 plantilla002;
            Candidato candidato;
            Agrupacion agrupacion;

            List<Plantilla002> lstPlantilla002 = new ArrayList<>();


            JSONArray jsonArr ;

            JSONObject obj_plantilla001 ;
            JSONObject obj_nivel ;

            obj_plantilla001 = new JSONObject(msgJson);
            plantilla001.setIdplantilla001(obj_plantilla001.getInt("idplantilla001"));

            obj_nivel =  obj_plantilla001.getJSONObject("nivel");
            nivel = new Nivel();
            nivel.setIdnivel(  obj_nivel.getInt("idnivel"));
            nivel.setDscnivel( obj_nivel.getString("dscnivel"));


            jsonArr = obj_plantilla001.getJSONArray("plantilla002s");

            JSONObject obj_plantilla002;
            JSONObject obj_candidato;
            JSONObject obj_agrupacion;

            for(int i=0; i < jsonArr.length() ; i++){
                obj_plantilla002 = jsonArr.getJSONObject(i);
                plantilla002 = new Plantilla002();
                plantilla002.setIdplantilla002(obj_plantilla002.getString("idplantilla002"));
                plantilla002.setOrden(obj_plantilla002.getInt("orden"));

                obj_candidato = obj_plantilla002.getJSONObject("candidato");
                candidato = new Candidato();
                candidato.setIdcandidato(obj_candidato.getInt("idcandidato"));
                candidato.setFoto(obj_candidato.getString("foto"));
                //candidato.setFotoBase64(obj_candidato.getString("fotoBase64"));
                candidato.setNombre(obj_candidato.getString("nombre"));

                obj_agrupacion = obj_candidato.getJSONObject("agrupacion");
                agrupacion = new Agrupacion();
                agrupacion.setIdagrupacion(obj_agrupacion.getInt("idagrupacion"));
                agrupacion.setDscagrupacion(obj_agrupacion.getString("dscagrupacion"));
                agrupacion.setLogo(obj_agrupacion.getString("logo"));
                //agrupacion.setFotoBase64(obj_agrupacion.getString("fotoBase64"));

                candidato.setAgrupacion(agrupacion);
                plantilla002.setCandidato(candidato);
                lstPlantilla002.add(plantilla002);

            }
            plantilla001.setNivel(nivel);
            plantilla001.setPlantilla002s(lstPlantilla002);

            //Creamos La Voto001 segun plantilla001 seleccionada
            voto001 = new Voto001();
            voto001.setIdvoto001(0);
            voto001.setPlantilla001(plantilla001);
            mesaDeVotacion.setFlagRegistrado(true);
            voto001.setMesaDeVotacion(mesaDeVotacion);
            voto001.setAlta(null);
            voto001.setFlagRegistrado(false);

            Voto002 voto002 = null;
            List<Voto002> lstVoto002 = new ArrayList<>();
            for(Plantilla002 row: plantilla001.getPlantilla002s()){
                voto002 = new Voto002();
                voto002.setVoto(0);
                voto002.setIdvoto002(UUID.randomUUID().toString());
                voto002.setPlantilla002(row);
                lstVoto002.add(voto002);

            }
            voto001.setVoto002s(lstVoto002);



            adapter = new VotacionAdapter(this,voto001);
            lvVoto.setAdapter(adapter);



            lvVoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        final int position, long id) {

                    TextView txt = (TextView)view.findViewById(R.id.txtVotos);

                    posicionItem = position;
                    String pos = String.valueOf(position);
                    //Toast.makeText(contexto, "Pos : " + pos + " txt :" + txt.getText() ,Toast.LENGTH_LONG).show();
                    viewItemSeleccionado = view;
                    new Dialogo_Voto(contexto,VotoActivity.this);


                }
            });



        }catch (Exception e){
            Toast.makeText(this, "Erro al cargar la lista " + e.getMessage(),Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public void ResutadoCuadroDialogo(String votos) {

        this.voto001.getVoto002s().get(posicionItem).setVoto(  Integer.parseInt(votos) );
        adapter.notifyDataSetChanged();
        Integer sumVotos = 0;
        for (Voto002 row: this.voto001.getVoto002s()){
            if(row.getVoto() == null){
                row.setVoto(0);
            }
            sumVotos = sumVotos + row.getVoto();
        }

        TextView txtSumVotos = (TextView) findViewById(R.id.txtTotalVotos);
        txtSumVotos.setText(sumVotos.toString());


    }

    @Override
    public void onBackPressed() {
        if(progresBarActa.isShown()){

        }else{
            super.onBackPressed();
        }

    }
}
