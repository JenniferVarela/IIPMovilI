package com.example.iip_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iip_movil.Models.Usuarios;

import java.util.ArrayList;

public class ActivityVolley extends AppCompatActivity {
    ListView list;
    ArrayList<String> titulos = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        ObtenerUsuarios();

        list= (ListView) findViewById(R.id.lista);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,titulos);
        list.setAdapter(arrayAdapter);
    }

    private void ObtenerUsuarios()
    {
        RequestQueue cola= Volley.newRequestQueue(this);
        String endpoint = "https://jsonplaceholder.typicode.com/posts";

        StringRequest stringRequest= new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {

                Log.i ("Mensaje: ", response.substring(0,500));

            }
        },new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error){

            }
        });
        cola.add(stringRequest);
    }
}