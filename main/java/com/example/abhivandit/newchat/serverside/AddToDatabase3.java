package com.example.abhivandit.newchat.serverside;

/**
 * Created by Abhivandit on 14/6/2017.
 */

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

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
public class AddToDatabase3 extends AsyncTask<Void, Void, String> {

    private final String receiver,receiverId,sender,senderId,message;
    private String counter="1";

    public AddToDatabase3(String receiver,String receiverUid,String sender,String senderUid,String message) {
     this.receiver=receiver;
        this.receiverId=receiverUid;
        this.sender=sender;
        this.senderId=senderUid;
        this.message=message;

        Log.d("onpost33","onpot233");

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
            URL url = new URL("http://192.168.0.120/chatapp/ChatLog.php");
            String data = URLEncoder.encode("receiver", "UTF-8")
                    + "=" + URLEncoder.encode(receiver, "UTF-8");
            data += "&" + URLEncoder.encode("receiverId", "UTF-8")
                    + "=" + URLEncoder.encode(receiverId, "UTF-8");
            data += "&" + URLEncoder.encode("sender", "UTF-8")
                    + "=" + URLEncoder.encode(sender, "UTF-8");
            data += "&" + URLEncoder.encode("senderId", "UTF-8")
                    + "=" + URLEncoder.encode(senderId, "UTF-8");
            data += "&" + URLEncoder.encode("message", "UTF-8")
                    + "=" + URLEncoder.encode(message, "UTF-8");
            data += "&" + URLEncoder.encode("counter", "UTF-8")
                    + "=" + URLEncoder.encode(counter, "UTF-8");
            URLConnection conn = url.openConnection();
            System.out.println("heredsada");
            conn.setDoOutput(true);
            System.out.println("aaajasadasd");
            Log.d("addedforever",data);
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
//userlistingrecyleradapter mei hai ek