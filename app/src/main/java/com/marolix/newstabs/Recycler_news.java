package com.marolix.newstabs;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

public class Recycler_news extends RecyclerView.Adapter<Recycler_news.ViewHolderClass> {
    Context context;
    ArrayList<Map<String,String>> al;
    public Recycler_news(Context Grocery, ArrayList<Map<String,String>> al) {
        context= Grocery;
        this.al=al;

    }
    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.recyclernews,viewGroup,false );
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass viewHolderClass, final int i) {
        viewHolderClass.title.setText( al.get(i).get("heading") );
        viewHolderClass.description.setText( al.get(i).get("description"));
        Picasso.with(context)
                .load(al.get(i).get("urltoimage"))
                .into(viewHolderClass.imageView);
        viewHolderClass.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,News_Details.class);
                intent.putExtra("al",al);
                intent.putExtra("position",i);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView imageView;
        LinearLayout linearlayout;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.img);
            linearlayout=itemView.findViewById(R.id.linearlayout);

        }
    }
}
