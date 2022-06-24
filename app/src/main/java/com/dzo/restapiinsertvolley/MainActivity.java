package com.dzo.restapiinsertvolley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name1 , email1;
    private final String url = "your API here";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name1 = findViewById(R.id.name);
        email1 = findViewById(R.id.email);

    }

    public void insertData(View view) {

        insertToDatabase(name1.getText().toString(), email1.getText().toString());

    }

       public void insertToDatabase(String name,String email){

           StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {

                   name1.setText("");
                   email1.setText("");
                   Toast.makeText(MainActivity.this, "Data has Inserted"+response, Toast.LENGTH_LONG).show();


               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

                   Toast.makeText(MainActivity.this, "SomeThing Went Wrong"+error.toString(), Toast.LENGTH_LONG).show();

               }
           }
           ){
               @Nullable
               @Override
               protected Map<String, String> getParams() throws AuthFailureError {
                   Map<String,String> maps = new HashMap<String,String>();
                   maps.put("name",name);
                   maps.put("email",email);
                   return maps;

               }
           };

           RequestQueue queue =  Volley.newRequestQueue(getApplicationContext());
           queue.add(stringRequest);

    }
}