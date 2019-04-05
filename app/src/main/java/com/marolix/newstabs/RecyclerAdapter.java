package com.marolix.newstabs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass> {
    Context context;
    ArrayList<Map<String,String>> al;
    public RecyclerAdapter(Context context, ArrayList<Map<String,String>> al) {
        this.context= context;
        this.al=al;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler,viewGroup,false );
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass viewHolderClass, int i) {
        viewHolderClass.textView.setText( al.get(i).get("heading") );
        viewHolderClass.textView1.setText( al.get(i).get("publishdate"));
        Picasso.with(context)
                .load(al.get(i).get("urltoimage"))
                .into(viewHolderClass.imageView);
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView1;
        ImageView imageView;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.time);
            textView1=itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
