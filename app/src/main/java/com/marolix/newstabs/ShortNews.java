package com.marolix.newstabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ShortNews extends Fragment {
    RecyclerView Category_recycler;
    RequestQueue queue;
    ImageView img;
    TextView titles;
    ArrayList<Map<String,String>> al=new ArrayList<>();
    String category[]={"Bussiness","Politics","Entertainment","Sports","Technology"};
    Category_recycler_adapter category_recycler_adapter;
    ArrayAdapter<String> adapter;
    Recycler_news recycler_news;
    RecyclerView recyclernews;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shortnews, container, false);
        Log.i("onAttach", "called");
        queue = Volley.newRequestQueue(getContext());
        Category_recycler=view.findViewById(R.id.Category_recycler);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        Category_recycler.setLayoutManager(horizontalLayoutManager);
        category_recycler_adapter = new  Category_recycler_adapter(getActivity(), category);
        Category_recycler.setAdapter(category_recycler_adapter);
        img=view.findViewById(R.id.image);
         titles=view.findViewById(R.id.title);
        recyclernews = view.findViewById( R.id.recycler );
        recyclernews.setLayoutManager( new LinearLayoutManager( getActivity() ) );

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://raw.githubusercontent.com/ashaanusha/newsTabs/master/tabs_news", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray news_array=response.getJSONArray("News_list");
                    for(int i=0;i<news_array.length();i++)
                    {
                        Map<String,String> map=new HashMap<>() ;
                        JSONObject news_details=news_array.getJSONObject(i);
                        String category=news_details.getString("category");
                        String heading=news_details.getString("heading");
                        String description=news_details.getString("description");
                        String urltoimage=news_details.getString("urltoimage");
                        String publishdate=news_details.getString("publishdate");
                        map.put("category",category);
                        map.put("heading",heading);
                        map.put("description",description);
                        map.put("urltoimage",urltoimage);
                        map.put("publishdate",publishdate);
                        al.add(map);
                    }
                    Picasso.with(getContext())
                            .load(al.get(0).get("urltoimage"))
                            .into(img);
                    titles.setText(al.get(0).get("heading"));
                    al.remove(0);
                    recycler_news = new Recycler_news( getActivity(),al);

                    recyclernews.setAdapter( recycler_news );
                }  catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Log.e("error", error.getMessage());
            }
        }
        );
        queue.add(request);

        return view;
    }
}
