package com.example.thelabit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thelabit.modelo.DBTheLabIT;
import com.example.thelabit.modelo.Login;
import com.example.thelabit.modelo.Sesion;
import com.example.thelabit.vista.EjemploMapa;
import com.example.thelabit.vista.HomeCorredor;
import com.example.thelabit.vista.HomeEntrenador;
import com.example.thelabit.vista.RegistrarUsuario;
import com.example.thelabit.vista.ViewPlanesDetalle;

public class MainActivity extends AppCompatActivity {
    //API KEY AIzaSyCcekE7ZB5tACXfm6SJ9uDnfDbcX3IBd7E
    EditText username;
    EditText password;
    Button btnlogin;
    Button btnSignUp, btnMapa;
    DBTheLabIT DB;
    Boolean LoginOK = false;
    SharedPreferences sharedpreferences;
    String SPusername, SPpassword;
    private Sesion sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username    = (EditText) findViewById(R.id.username);
        password    = (EditText) findViewById(R.id.password);
        btnlogin    = (Button) findViewById(R.id.btnLogin);
        btnSignUp   = (Button) findViewById(R.id.btnSignUp);
        btnMapa   = (Button) findViewById(R.id.btnMapa);
        DB = new DBTheLabIT(this);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //esto es para iniciar la base de datos pero no sé si funciona
                DB.inicio();

                String user = username.getText().toString();
                String logueado = username.getText().toString();
                String pass = password.getText().toString();

                //creo un objeto Login para hacer el chequeo
                Login login = new Login(user, pass);

                String tipo = DB.chequearUsuarioPassword(login);

                //chequeo de los datos ingresados
                if (tipo == "corredor"){
                    sesion = new Sesion(MainActivity.this); //in oncreate
                    sesion.setusername(login.getUsername());

                    Intent intent = new Intent(getApplicationContext(), HomeCorredor.class);
                    startActivity(intent);
                }else if (tipo == "entrenador"){
                    sesion = new Sesion(MainActivity.this); //in oncreate
                    sesion.setusername(login.getUsername());

                    Intent intent = new Intent(MainActivity.this, HomeEntrenador.class);
                    Bundle b = new Bundle();
                    b.putString("logueado", logueado);
                    intent.putExtras(b);
                    startActivity(intent);

                }else {
                    Toast.makeText(MainActivity.this, "nooo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), RegistrarUsuario.class);
                startActivity(intent);
            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(), EjemploMapa.class);
                Intent intent = new Intent(getApplicationContext(), EjemploMapa.class);
                startActivity(intent);
            }
        });
    }
}