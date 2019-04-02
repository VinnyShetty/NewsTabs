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

public class Recycler_news extends RecyclerView.Adapter<Recycler_news.ViewHolderClass> {
    Context context;
    String[] title;
    String[] desc;
    int[] images;
    public Recycler_news(Context Grocery, int[] images, String[] title, String[] desc) {
        context= Grocery;
        this.images=images;
        this.title=title;
        this.desc=desc;

    }
    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.recyclernews,viewGroup,false );
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass viewHolderClass, int i) {
        viewHolderClass.title.setText( title[i] );
        viewHolderClass.description.setText( desc[i] );
        viewHolderClass.imageView.setImageResource( images[i] );
        viewHolderClass.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,News_Details.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
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
