package com.example.iip_movil;

import static android.util.Base64.encodeToString;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
//import java.util.Base64;

public class ActivityImagen extends AppCompatActivity {

    ImageView imageView;
    Button btngaleria,btnUploadImage;
    Bitmap photo;
    static final int RESUL_GALLERY_IMG =200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        imageView = (ImageView) findViewById(R.id.imageView);
        btngaleria = (Button) findViewById(R.id.btnGaleria);
        btnUploadImage = (Button) findViewById(R.id.btnUploadImage);

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // String imageBase64 = GetStringImage(photo);
                SendImage();

            }
        });

        btngaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GaleriaImagen();
            }
        });

    }

    private void SendImage() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, RestApiMethods.EndPointImage, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Respuesta",response.toString());
                Toast.makeText(getApplicationContext(),"imagen SUbida" + response.toString(),Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Respuesta",error.toString());
                Toast.makeText(getApplicationContext(),"Error " + error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {
            //data que se envia mediante request
            protected Map<String, String> getParams() {

                String image = GetStringImage(photo);
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("IMAGEN", image);

                return parametros;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private String GetStringImage(Bitmap photo) {

       try {
           ByteArrayOutputStream ba = new ByteArrayOutputStream();
           photo.compress(Bitmap.CompressFormat.JPEG, 70, ba);
           byte[] imagebyte = ba.toByteArray();
           String encode = Base64.encodeToString(imagebyte, Base64.DEFAULT);

           return encode;
       }catch (Exception ex)
       {
           ex.toString();
       }
       return "";
    }

    private void GaleriaImagen()
    {
        Intent intentGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentGaleria,RESUL_GALLERY_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri imageUri;
        if(resultCode==RESULT_OK && requestCode==RESUL_GALLERY_IMG)
        {

            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            try {
                photo=MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);

            }catch (Exception e)
            {

            }
        }
    }
}