package com.example.hp.jsondemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    Button btn;
    List<Contacts>mlist=new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        listView= (ListView) findViewById(R.id.listview);
        btn= (Button)findViewById(R.id.login_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Loginclass().execute();
            }
        });
    }
    public  class Loginclass extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            String urlString = "http://api.androidhive.info/contacts/";

            try {
                URL url=new URL(urlString);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");

                int code=connection.getResponseCode();
                if (code == 200) {
                    InputStream stream = connection.getInputStream();
                    String response = convertStreamtoString(stream);

                    JSONObject rootObject = null;
                    try {
                        rootObject = new JSONObject(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                        JSONArray array = rootObject.getJSONArray("contacts");
                        for (int i = 0; i < array.length(); i++) {

                            JSONObject contactObject = array.getJSONObject(i);

                        Contacts contact=new Contacts();

                        String idValue = contactObject.getString("id");
                        String nameValue = contactObject.getString("name");
                        String emailValue = contactObject.getString("email");
                        String addressValue = contactObject.getString("address");
                        String genderValue = contactObject.getString("gender");

                        contact.setId(idValue);
                        contact.setName(nameValue);
                        contact.setEmail(emailValue);
                        contact.setAddress(addressValue);
                        contact.setGender(genderValue);
                        mlist.add(contact);
                        Log.v("Object details : " , " : " + idValue + " : " + nameValue);
                    }
                }


        } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

        ContactsAdapter adpter=new ContactsAdapter(MainActivity.this,mlist);
            listView.setAdapter(adpter);
        }
    }

    private String convertStreamtoString(InputStream stream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "iso-8859-1"), 8);
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            stream.close();

        } catch (Exception e) {
            Log.e("Error", "Error converting result " + e.toString());
        }
        return sb.toString();


    }



}
