package com.example.iip_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityListaEmple extends AppCompatActivity {
    ListView listaemple;
    List<Empleado> empleadoList;
    ArrayList<String> arrayEmple;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_emple);

        listaemple = (ListView) findViewById(R.id.listemple);
        empleadoList = new ArrayList<>();
        arrayEmple = new ArrayList<String >();

        ConsumirListaEmpleados();

    }

    private void ConsumirListaEmpleados() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, RestApiMethods.EndPointGetEmple, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //convertir a un objeto json
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray EmpleArray = jsonObject.getJSONArray("empleado");

                    for (int i=0; i<EmpleArray.length(); i++)
                    {
                        JSONObject RowEmple = EmpleArray.getJSONObject(i);
                        Empleado emple = new Empleado(RowEmple.getString("id")
                                                        ,RowEmple.getString("nombre")
                                                        ,RowEmple.getString("apellidos")
                                                        ,RowEmple.getString("edad"));

                        empleadoList.add(emple);
                        arrayEmple.add(emple.getNombre()+' '+ emple.getApellidos());
                    }
                    ArrayAdapter adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrayEmple);
                    listaemple.setAdapter(adp);

                }catch (Exception e)
                {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }//para poderlo convertir a json
}