package com.marolix.newstabs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass> {
    Context context;
    String[] arr;
    String[] arr1;
    int[] images;
    public RecyclerAdapter(Context context, String[] arr, String[] arr1, int[] images) {
        this.context= context;
        this.arr=arr;
        this.arr1=arr1;
        this.images=images;
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
        viewHolderClass.textView.setText( arr[i] );
        viewHolderClass.textView1.setText( arr1[i] );
        viewHolderClass.imageView.setImageResource( images[i] );
    }

    @Override
    public int getItemCount() {
        return arr.length;
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
