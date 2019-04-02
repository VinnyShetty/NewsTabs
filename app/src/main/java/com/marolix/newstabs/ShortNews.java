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

import java.util.Locale;

public class ShortNews extends Fragment {
    RecyclerView Category_recycler;
    String category[]={"Bussiness","Politics","Entertainment","Sports","Technology"};
    Category_recycler_adapter category_recycler_adapter;
    int[] images = {R.drawable.sports, R.drawable.sports, R.drawable.sports,R.drawable.sports,R.drawable.sports,R.drawable.sports,R.drawable.sports,R.drawable.sports};
    String[] title={"Fruits and vegetables","Bread,Dairy and Eggs","Bevarages","Personal Care","Fruits and vegetables","Bread,Dairy and Eggs","Bevarages","Personal Care"};
    String[] desc={"Description sdhgsvkfc fcbdkfvh bfck adsbjfkdfgdg sdhgf gfrkefr gkfrql qrerglq hehrl egrqgh rgele grehrj rler   relt","Description sdhgsvkfc fcbdkfvh bfck adsbjfkdfgdg sdhgf gfrkefr gkfrql qrerglq hehrl egrqgh rgele grehrj rler   relt","Description sdhgsvkfc fcbdkfvh bfck adsbjfkdfgdg sdhgf gfrkefr gkfrql qrerglq hehrl egrqgh rgele grehrj rler   relt","Description sdhgsvkfc fcbdkfvh bfck adsbjfkdfgdg sdhgf gfrkefr gkfrql qrerglq hehrl egrqgh rgele grehrj rler   relt","Description sdhgsvkfc fcbdkfvh bfck adsbjfkdfgdg sdhgf gfrkefr gkfrql qrerglq hehrl egrqgh rgele grehrj rler   relt","Description sdhgsvkfc fcbdkfvh bfck adsbjfkdfgdg sdhgf gfrkefr gkfrql qrerglq hehrl egrqgh rgele grehrj rler   relt","Description sdhgsvkfc fcbdkfvh bfck adsbjfkdfgdg sdhgf gfrkefr gkfrql qrerglq hehrl egrqgh rgele grehrj rler   relt","Description sdhgsvkfc fcbdkfvh bfck adsbjfkdfgdg sdhgf gfrkefr gkfrql qrerglq hehrl egrqgh rgele grehrj rler   relt"};
    ArrayAdapter<String> adapter;
    Recycler_news recycler_news;
    RecyclerView recyclernews;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shortnews, container, false);
        Log.i("onAttach", "called");
        Category_recycler=view.findViewById(R.id.Category_recycler);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        Category_recycler.setLayoutManager(horizontalLayoutManager);
        category_recycler_adapter = new  Category_recycler_adapter(getActivity(), category);
        Category_recycler.setAdapter(category_recycler_adapter);

        recyclernews = view.findViewById( R.id.recycler );
        recyclernews.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        recycler_news = new Recycler_news( getActivity(),images,title,desc);

        recyclernews.setAdapter( recycler_news );

        return view;
    }
}
