package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    List<User> userlist;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    RequestQueue requestQueue;
    private String url="https://run.mocky.io/v3/8fe05c8c-3ef7-43e2-a7e8-0e43454d1f4e";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue=Volley.newRequestQueue(this);
        recyclerView=(RecyclerView) findViewById(R.id.recycleViewContainer);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        userlist=new ArrayList<>();
        sendRequest();
    }

    private void sendRequest() {

          JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
              @Override
              public void onResponse(JSONArray response) {
                  for (int i = 0; i < response.length(); i++) {
                      User user = new User();
                      try {
                          JSONObject jsonObject = response.getJSONObject(i);
                          user.setId(jsonObject.getString("id"));
                          user.setName(jsonObject.getString("name"));
                          user.setEmail(jsonObject.getString("email"));
                          user.setGender(jsonObject.getString("gender"));

                      } catch (JSONException e) {
                          e.printStackTrace();
                      }
                      userlist.add(user);
                  }
                  mAdapter = new CustomAdapter(MainActivity.this, userlist);
                  recyclerView.setAdapter(mAdapter);
              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                 Log.i("Volley Error : ", String.valueOf(error));
              }
          });

      requestQueue.add(jsonArrayRequest);

    }
}
/*/*GsonBuilder gsonBuilder=new GsonBuilder();
                Gson gson=gsonBuilder.create();  //create object of GSON
                User[] user= gson.fromJson(response, User[].class); */ //parse the response   //Response is of user type*/

//private String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";
//private String url = "http://www.google.com";
//private String url = "https://run.mocky.io/v3/863c7e49-1db5-4ce4-9761-245df06808d1";
//private String url="https://api.github.com/users";
//private String url="https://run.mocky.io/v3/01950b86-9d9a-4ce8-8c8e-7f7ad1e5da95";