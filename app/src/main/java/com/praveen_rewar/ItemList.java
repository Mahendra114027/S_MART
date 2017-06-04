package com.praveen_rewar.s_mart;

import android.app.LauncherActivity;
import android.content.Intent;
import android.icu.util.ULocale;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//import static com.praveen_rewar.s_mart.R.attr.layoutManager;
//import static com.praveen_rewar.s_mart.R.id.recycler_view;

public class ItemList extends AppCompatActivity {
    private static final String URL_DATA="http://192.168.43.98/category.php";
    private String category="catName";
    public String Category;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String Item;
    private String Price;
    private String Image;
    public ItemList(){

    }
    public ItemList(String item, String price, String image) {
        this.Item = item;
        this.Price = price;
        this.Image = image;
    }

    public String getItem() {
        return Item;
    }

    public String getPrice() {
        return Price;
    }

    public String getImage() {
        return Image;
    }
    private List<ItemList> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Intent intent = getIntent();
        Category = intent.getExtras().getString("EXTRA_MESSAGE");
        System.out.println(Category);
        //category=Category;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        itemList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {

                    @Override

                    public void onResponse(String response) {

                        Log.d("suces",response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("rows");
                            System.out.println("Idhar aaya");
                            System.out.println(array);
                            if(array!=null)
                                for(int i = 0; i<array.length(); i++){
                                    JSONObject o = array.getJSONObject(i);System.out.println(o.getString("Item"));
                                    ItemList item = new ItemList(
                                            o.getString("Item"),
                                            o.getString("Price"),
                                            o.getString("Image")
                                    );
                                    itemList.add(item);

                                    //sToast.makeText(getApplication(), "Hello",Toast.LENGTH_LONG).show();
                                }
                            adapter = new RecyclerAdapter(itemList, getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError){
                        volleyError.getStackTrace();

                    }
                }){


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Creating parameters
                Map<String,String> params = new HashMap<String,String>();
                Log.d("ghanta","ghanta");
                //put params
                params.put(category,Category);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        //loadRecyclerViewData();

    }
    private void loadRecyclerViewData() {
        System.out.println("Idhar aaya2");
    }
}
