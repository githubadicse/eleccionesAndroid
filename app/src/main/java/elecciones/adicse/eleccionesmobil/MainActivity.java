package elecciones.adicse.eleccionesmobil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(  MainActivity.this, LoginActivity.class);
        startActivity(intent);


    }

    public void BotonAceptar (View vista){
        Intent intent= new Intent (MainActivity.this, Personeros.class);
        startActivity(intent);
    }
}
