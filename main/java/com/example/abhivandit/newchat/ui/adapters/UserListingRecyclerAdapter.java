package com.example.abhivandit.newchat.ui.adapters;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abhivandit.newchat.R;
import com.example.abhivandit.newchat.models.User;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;


public class UserListingRecyclerAdapter extends RecyclerView.Adapter<UserListingRecyclerAdapter.ViewHolder> {
    private List<User> mUsers;
    private int check=0;

    public static boolean isDownloadFinished = false;

    public UserListingRecyclerAdapter(List<User> users) {
        this.mUsers = users;
    }

    public void add(User user) {
        mUsers.add(user);

        notifyItemInserted(mUsers.size() - 1);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_user_listing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUsers.get(position);

//check is checking whether there is a message or not
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String alphabet = user.email.substring(0, 1);
        try{
            Log.d("currentuser",currentuser+" "+user.email);
            new AddTodatabase4(user.email,currentuser,holder,alphabet).execute();}
        catch(Exception e){
            System.out.println("asynctask exception");
        }
      //  System.out.println("checking check"+check);


               // holder.status.setText("message");




            holder.txtUsername.setText(user.email);
           holder.txtUserAlphabet.setText(alphabet);

      //  System.out.println(currentuser+" "+user.email);


    }

    @Override
    public int getItemCount() {
        if (mUsers != null) {
            return mUsers.size();
        }
        return 0;
    }

    public User getUser(int position) {
        return mUsers.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtUserAlphabet, txtUsername;
        private TextView status;//added

        ViewHolder(View itemView) {
            super(itemView);
            txtUserAlphabet = (TextView) itemView.findViewById(R.id.text_view_user_alphabet);
            txtUsername = (TextView) itemView.findViewById(R.id.text_view_username);
            status=(TextView)itemView.findViewById(R.id.status);//added
        }
    }
// to check whether we have received any messages from the user
    public class AddTodatabase4 extends AsyncTask<Void, Void, String> {

        private final String emailsender,emailreceiver;
        private ViewHolder holder;
    private String alphabet;


        public AddTodatabase4(String emailsender,String emailreceiver,ViewHolder holder,String alphabet) {
            this.emailsender=emailsender;
            this.emailreceiver=emailreceiver;
            this.holder=holder;
            this.alphabet=alphabet;

            Log.d("onpost23","onpot23");

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
                //TODO
                URL url = new URL("http://192.168.0.120/chatapp/CheckMessageReceived.php");
                String data = URLEncoder.encode("emailsender", "UTF-8")
                        + "=" + URLEncoder.encode(emailsender, "UTF-8");
                data += "&" + URLEncoder.encode("emailreceiver", "UTF-8")
                        + "=" + URLEncoder.encode(emailreceiver, "UTF-8");
            Log.d("data",data);

                URLConnection conn = url.openConnection();
               // System.out.println("here");
                conn.setDoOutput(true);
               // System.out.println("aaaja");

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
            Log.d("onpost2","onpot");
           // System.out.println(result);
            try{
            JSONObject json = new JSONObject(result);
               String checkstring =(String)json.getString("message");
                Log.d("checkstring",checkstring);
                if(new String(checkstring).equals("successful")){
                    Log.d("checksum","here");
                    check=1;
                    holder.status.setText("message");

                    isDownloadFinished=true;
                }
                else{
                    Log.d("checksum2","here");
                    check=0;
                   // holder.txtUserAlphabet.setText(alphabet);
                    isDownloadFinished=true;
                }
                System.out.println(checkstring);
            }

            catch(Exception  e){
                System.out.println("exception in userlistingrecylceradapter asynctask");
            }

            //we have to check wehter there is a message or not and accordingly set the check variable
            // } else {
             /*   mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
                status.setText(result);*/
            // }

            //
            // myJson=result;
        }

    }

}
