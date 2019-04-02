package com.marolix.newstabs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Category_recycler_adapter extends RecyclerView.Adapter<Category_recycler_adapter.ViewHolderClass> {
    Context context;
    String[] category;
    Category_recycler_adapter(Context context,String[] category)
    {
        this.context=context;
        this.category=category;

    }
    @NonNull
    @Override
    public Category_recycler_adapter.ViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( context ).inflate( R.layout.category_recycler_adapter, viewGroup, false );
        Category_recycler_adapter.ViewHolderClass viewHolderClass = new Category_recycler_adapter.ViewHolderClass( view );
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull Category_recycler_adapter.ViewHolderClass viewHolderClass, int i) {
        viewHolderClass.Category.setText(category[i]);
    }

    @Override
    public int getItemCount() {
        return category.length;
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView Category;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            Category=itemView.findViewById(R.id.Category);

        }
    }
}
