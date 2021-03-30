package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.mainAdapter;
import com.example.myapplication.model.DataHolder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
  private  RecyclerView recyclerView;
 private RecyclerView.LayoutManager layoutManager;
private mainAdapter Adapter;
private EditText editText;
private Button btnsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // for network call
        recyclerView=findViewById(R.id.recyclerView);
        editText=findViewById(R.id.edit_Name);
        btnsubmit=findViewById(R.id.BtnSubmit);
       // String url = "https://restcountries.eu/rest/v2/region/";
    final String[] fina=new String[1];
    fina[0]="asia";
        btnsubmit.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        // validation must be done or drop down is required

                        if(!editText.toString().isEmpty()){
                            fina[0]=editText.getText().toString();
                        }
                        else{
                            fina[0]="asia";
                        }
                        Display(fina);
                        Toast.makeText(getApplicationContext(),"hello"+fina[0],Toast.LENGTH_LONG).show();
                    }
                }
        );
        if(checkConnectivity(getApplicationContext())) {
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url = "https://restcountries.eu/rest/v2/region/"+fina[0];
            //  System.out.println(" saksham");
            //JSONObject jsonObject = new JSONObject();
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                        @Override
                        public void onResponse(JSONArray response) {
                            //  textView.setText("Response: " + response.toString());
                            System.out.println(" saksham upper try");
                            try {
                                ArrayList<DataHolder> list=new ArrayList<DataHolder>();
                                System.out.println(response.length());
                                for(int i=0;i<response.length();i++){
                                   String name="", capital="", flag="", region="",
                                            subregion="", population="", borders="" , languages="";
                                   name=response.getJSONObject(i).getString("name");
                                    capital=response.getJSONObject(i).getString("capital");
                                    flag=response.getJSONObject(i).getString("flag");
                                    region=response.getJSONObject(i).getString("region");
                                    subregion=response.getJSONObject(i).getString("subregion");
                                    population=response.getJSONObject(i).getString("population");

                                    JSONArray e=response.getJSONObject(i).getJSONArray("borders");
                                    //fetching string from json
                                    for(int j=0;j<e.length();j++){
                                        String k=e.getString(j);
                                        borders=borders+k+" , ";
                                    }

                                    JSONArray lang=response.getJSONObject(i).getJSONArray("languages");
                                    for(int j=0;j<lang.length();j++){
                                        String flag1=lang.getJSONObject(j).getString("name");
                                        languages+=flag1+" ,";
                                    }
                                    DataHolder holder=new DataHolder(name, capital, flag, region,
                                            subregion, population, borders, languages);
                                    list.add(holder);
                                }

                                recyclerView.setAdapter(new mainAdapter(getApplicationContext(),list));
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                                String obj = response.getJSONObject(0).getString("name");
//                                Toast.makeText(getApplicationContext(), obj + " uhiuyu", Toast.LENGTH_LONG).show();
//                                System.out.println(obj + " saksham");

                            } catch (Exception e) {
                                System.out.println("hello error "
                                + e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            System.out.println("hello " +error.getMessage());
                        }
                    });
            System.out.println("what is what");
            queue.add(jsonArrayRequest);
        }
        else{

        }
    }
    static public boolean checkConnectivity(Context context){
        ConnectivityManager connectivityManager= (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE) ;
        NetworkInfo activeNetwork= connectivityManager.getActiveNetworkInfo();
        if(activeNetwork.isConnected() != false){
            return activeNetwork.isConnected();
        }
        else{
            return false;
        }
    }










    void Display(String[] fina){

        if(checkConnectivity(getApplicationContext())) {
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url = "https://restcountries.eu/rest/v2/region/"+fina[0];
             System.out.println(fina[0]);
            //JSONObject jsonObject = new JSONObject();
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                        @Override
                        public void onResponse(JSONArray response) {
                            //  textView.setText("Response: " + response.toString());
                            System.out.println(" saksham upper try");
                            try {
                                ArrayList<DataHolder> list=new ArrayList<DataHolder>();
                                System.out.println(response.length());
                                for(int i=0;i<response.length();i++){
                                    String name="", capital="", flag="", region="",
                                            subregion="", population="", borders="" , languages="";
                                    name=response.getJSONObject(i).getString("name");
                                    capital=response.getJSONObject(i).getString("capital");
                                    flag=response.getJSONObject(i).getString("flag");
                                    region=response.getJSONObject(i).getString("region");
                                    subregion=response.getJSONObject(i).getString("subregion");
                                    population=response.getJSONObject(i).getString("population");

                                    JSONArray e=response.getJSONObject(i).getJSONArray("borders");
                                    //fetching string from json
                                    for(int j=0;j<e.length();j++){
                                        String k=e.getString(j);
                                        borders=borders+k+" , ";
                                    }

                                    JSONArray lang=response.getJSONObject(i).getJSONArray("languages");
                                    for(int j=0;j<lang.length();j++){
                                        String flag1=lang.getJSONObject(j).getString("name");
                                        languages+=flag1+" ,";
                                    }
                                    DataHolder holder=new DataHolder(name, capital, flag, region,
                                            subregion, population, borders, languages);
                                    list.add(holder);
                                }

                                recyclerView.setAdapter(new mainAdapter(getApplicationContext(),list));
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                                String obj = response.getJSONObject(0).getString("name");
//                                Toast.makeText(getApplicationContext(), obj + " uhiuyu", Toast.LENGTH_LONG).show();
//                                System.out.println(obj + " saksham");

                            } catch (Exception e) {
                                System.out.println("hello error "
                                        + e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            System.out.println(error.getMessage());
                        }
                    });
            System.out.println("what is what");
            queue.add(jsonArrayRequest);
        }
        else{

        }
    }
}
