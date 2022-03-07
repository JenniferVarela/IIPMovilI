package com.example.iip_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/*Metodos Retrofit*/
import com.example.iip_movil.Interfaces.Interfaces;
import com.example.iip_movil.Models.Usuarios;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityRestApi extends AppCompatActivity {
    ListView list;
    ArrayList<String> titulos = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    EditText txtBuscar;
    Button btnBuscar,btnListar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_rest_api);

        ObtenerUsuarios();

        list= (ListView) findViewById(R.id.lista);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,titulos);
        list.setAdapter(arrayAdapter);

        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnListar = (Button) findViewById(R.id.btnLista);
        txtBuscar = (EditText) findViewById(R.id.txtBuscar);

        setListener();
    }

    private void setListener() {
        btnBuscar.setOnClickListener(v->ObtenerUsuario());
        btnListar.setOnClickListener(v->ObtenerUsuarios());
    }

    private void ObtenerUsuarios()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Interfaces interfaceUsers = retrofit.create(Interfaces.class);
        //Objeto de request
        Call<List<Usuarios>> llamada = interfaceUsers.getUsuUsuarios();

        //Objeto de Response
        titulos = new ArrayList<>();
        llamada.enqueue(new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {

                for(Usuarios usuarios : response.body())
                {
                    Log.i("On Response",usuarios.getTitle());
                    titulos.add(usuarios.getTitle());
                }
                arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,titulos);
                list.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {

            }
        });
    }

    private void ObtenerUsuario()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Interfaces interfaceUsers = retrofit.create(Interfaces.class);
        //Objeto de request
        Call<Usuarios> llamada = interfaceUsers.getUsuario(txtBuscar.getText().toString());

        //Objeto de Response
        titulos = new ArrayList<>();
        llamada.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {

                Usuarios usuarios = response.body();

                    //Log.i("On Response",usuarios.getTitle());
                    titulos.add(usuarios.getTitle());
                arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,titulos);
                list.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {

            }
        });
    }
}