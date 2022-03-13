package com.example.iip_movil;

public class RestApiMethods {
    private static final String ipaddress="192.168.0.10";
    private static final String StringHttp = "http://";
    //Enpoint URL
    private static final String GetEmple = "/CRUD-PHP/listaempleados.php";
    private static final String CreateEmple = "/CRUD-PHP/crear.php";
    private static final String ImageUpload = "/CRUD-PHP/UploadFile.php";
    //Metodos Crud Res Apa para consumir//


    //Metodo Get
    public static final String EndPointGetEmple = StringHttp + ipaddress+ GetEmple;
    public static final String EndPointCreateEmple = StringHttp + ipaddress+ CreateEmple;
    public static final String EndPointImage = StringHttp + ipaddress + ImageUpload;

}
