package com.example.abhivandit.newchat.serverside;

/**
 * Created by Abhivandit on 14/6/2017.
 */

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import com.example.abhivandit.newchat.utils.SharedPrefUtil;
public class AddToDatabase extends AppCompatActivity {

    //defining views

    //  private ProgressDialog progressDialog;
    public String emaill;
    public String password1;

    //URL to RegisterDevice.php
    private static final String URL_REGISTER_DEVICE = "http://192.168.0.120/chatapp/RegisterDevice.php";

    public AddToDatabase(String emaillk, String password1k) {
        emaill = emaillk;
        password1 = password1k;
        System.out.println("here");
        Log.d("here", "here");

    }
    public void sendTokenToServer() {


        final String token = new SharedPrefUtil(getApplicationContext()).getDeviceToken();
        final String email = emaill;
        final String password = password1;

        /*if (token == null) {
            progressDialog.dismiss();
            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
            return;
        }*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // progressDialog.dismiss();

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("token", token);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }



    //storing token to mysql server
}
