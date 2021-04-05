package com.example.thelabit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thelabit.MainActivity;
import com.example.thelabit.R;
import com.example.thelabit.modelo.DBTheLabIT;

import java.util.ArrayList;

public class HomeEntrenador extends AppCompatActivity {

    DBTheLabIT DB;
    ArrayList<String> listitem = new ArrayList<String>();
    ArrayAdapter adapter;
    ListView corredores;
    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String USERNAME_KEY = "username_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String SPusername, SPpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_entrenador);

        corredores = findViewById(R.id.listacorredores);
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        String logueado = sharedpreferences.getString(USERNAME_KEY, null);
        Toast.makeText(HomeEntrenador.this, logueado, Toast.LENGTH_SHORT).show();
        viewdata();
    /*
        corredores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View viewm, int i,  long l) {
                String text  = corredores.getItemAtPosition(i).toString();
                Toast.makeText(HomeEntrenador.this, "bieeen", Toast.LENGTH_SHORT).show();
            }});*/
    }

    private void viewdata(){
        DB = new DBTheLabIT(this);
        Cursor c = DB.obtenerCorredores("Fernando");

        //String algo = c.getString(c.getColumnIndex("USERNAME"));
        if (c.moveToFirst()) {
            c.getInt(c.getColumnIndex("USERNAME"));

            while (c.moveToNext()) {
                Integer algo = c.getInt(c.getColumnIndex("USERNAME"));
                listitem.add(algo.toString());
            }
        }


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listitem);
        corredores.setAdapter(adapter);

    }
}