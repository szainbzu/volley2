package edu.cs.birzeit.volley2;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
    }

    public void btn_OnClick(View view) {
        final TextView txt = findViewById(R.id.textView);
        String url = "https://jsonplaceholder.typicode.com/todos";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String msg = "";
                for (int i = 0; i < 10; i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        msg+= obj.getString("title") + "\n";
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }
                txt.setText(msg);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txt.setText("Error: " + error.toString());
            }
        });

        queue.add(request);


    }
}