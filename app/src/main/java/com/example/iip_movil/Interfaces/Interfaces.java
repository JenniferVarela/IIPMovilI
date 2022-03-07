package com.example.iip_movil.Interfaces;

import com.example.iip_movil.Models.Usuarios;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;//solicita representacion de un recurso especifico para recuperar datos.
import retrofit2.http.Path;


public interface Interfaces {

    String Ruta0= "/posts";
    String Ruta1="/posts/{valor}";

    @GET(Ruta0)
    Call<List<Usuarios>> getUsuUsuarios();

    @GET(Ruta1)
    Call<Usuarios> getUsuario(@Path("valor") String valor);//pasar parametro por valor
}
