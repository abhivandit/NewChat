package com.example.abhivandit.newchat.serverside;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Abhivandit on 14/6/2017.
 */
public class AddTodatabase2 extends AsyncTask<Void, Void, String> {

private final String email,password1,token;

   public AddTodatabase2(String email,String password,String token) {
        this.email=email;
        this.password1=password;
       this.token=token;
       Log.d("onpost2","onpot2");

        }

@Override
protected String doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.

        String result1="";

           /* for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }*/
        try {
        URL url = new URL("http://192.168.0.120/chatapp/RegisterDevice.php");
        String data = URLEncoder.encode("email", "UTF-8")
                + "=" + URLEncoder.encode(email, "UTF-8");
            data += "&" + URLEncoder.encode("password1", "UTF-8")
                    + "=" + URLEncoder.encode(password1, "UTF-8");
            data += "&" + URLEncoder.encode("token", "UTF-8")
                    + "=" + URLEncoder.encode(token, "UTF-8");
        URLConnection conn = url.openConnection();
            System.out.println("here");
        conn.setDoOutput(true);
            System.out.println("aaaja");

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            System.out.println(data);

        wr.write(data);
        wr.flush();

        BufferedReader in = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String line = null;

        // Read Server Response
        while ((line = in.readLine()) != null) {
        sb.append(line + "\n");
        break;
        }


        //result1=
        return sb.toString();
        } catch (Exception e) {
        return new String(e.getMessage());
        }

        }

@Override
protected void onPostExecute(String result) {
    Log.d("onpost","onpot");
        // } else {
             /*   mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
                status.setText(result);*/
        // }

        //
       // myJson=result;
        }

        }
