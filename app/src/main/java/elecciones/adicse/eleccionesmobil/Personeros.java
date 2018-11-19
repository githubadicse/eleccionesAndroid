package elecciones.adicse.eleccionesmobil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

import elecciones.adicse.eleccionesmobil.adaptadores.PersoneroAdapter;
import elecciones.adicse.eleccionesmobil.model.Personero;

public class Personeros extends AppCompatActivity {

    private ListView lvPersoneros ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personeros);
        this.setTitle("Pesoneros");

        Bundle b = getIntent().getExtras();
        int idUsuario = -1; // or other values
        if(b != null){
            idUsuario = b.getInt("idUsuario");
        }


        lvPersoneros = findViewById(R.id.lvPersoneros);

        getPersoneros ws = new getPersoneros(idUsuario);
        ws.execute();





    }

    
    private class getPersoneros extends AsyncTask<String, String, String> {

        private int idUsuario;
        private String resp;

        HttpURLConnection urlConnection;

        public getPersoneros(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder stringBuilder = new StringBuilder();

            try {
                System.out.println("Inicialdo conexion");
                URL url = new URL (UrlConnection.urlConn + "/personero/getPersonerosByIdUsuario?idUsuario="+this.idUsuario);
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
                Toast.makeText(Personeros.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }

            return stringBuilder.toString() ;
        }

        @Override
        protected void onPostExecute(String s) {

                jsonInsertarPersonero(s);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }


    private void jsonInsertarPersonero(String msgJson)  {

        System.out.println(msgJson);

        try {


            List<String> lstString = new ArrayList<String>();
            final List<Personero> lstPersonero = new ArrayList<>();
            Personero personero ;
            JSONArray jsonArr = new JSONArray(msgJson);
            JSONObject obj_personero = null;

            for(int i=0; i < jsonArr.length() ; i++){
                obj_personero = jsonArr.getJSONObject(i);
                String nombres = obj_personero.getString("nombrepersonero");
                personero = new Personero();
                personero.setIdpersonero(obj_personero.getString("idpersonero"));
                personero.setNombrepersonero(obj_personero.getString("nombrepersonero"));
                personero.setFotoBase64( obj_personero.getString("fotoBase64")  );
                lstPersonero.add(personero);
                lstString.add(nombres);
            }

            PersoneroAdapter adapter ;
            adapter = new PersoneroAdapter(this,lstPersonero);
            lvPersoneros.setAdapter(adapter);


            lvPersoneros.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        final int position, long id) {

                    Personero personero = lstPersonero.get(position);

                    Intent intent = new Intent(Personeros.this , MesasDeVotacionActivity.class);
                    intent.putExtra("personero", personero);

                    startActivity(intent);
                }
            });



        }catch (Exception e){
            Toast.makeText(this, "Erro al cargar la lista " + e.getMessage(),Toast.LENGTH_LONG ).show();
        }




    }





}
