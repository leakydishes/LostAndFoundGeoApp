package com.example.lostandfoundapp.Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostandfoundapp.Activities.LostFoundItemsActivity;
import com.example.lostandfoundapp.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{

    List<Item> item;
    private ItemClickListener clickListener; // interface global

    public ItemAdapter(List<Item> item, LostFoundItemsActivity clickListener) {
        this.item = item;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_lists, parent,false);
        return new MyViewHolder(view); //return view instance of the recycler
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.MyViewHolder holder, int position) {
        holder.post.setText((String.valueOf(item.get(position).getPost())));
        holder.date.setText((String.valueOf(item.get(position).getDate())));
        holder.location.setText((String.valueOf(item.get(position).getLocation())));
        holder.name.setText((String.valueOf(item.get(position).getName())));
        holder.phone.setText((String.valueOf(item.get(position).getPhone())));
        holder.detail.setText((String.valueOf(item.get(position).getDetail())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(item.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return item == null ? 0 : item.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView post, date, location, name, phone, detail;
        public MyViewHolder(View itemView) {
            super(itemView);
            // set views
            post = itemView.findViewById(R.id.item_postType);
            date = itemView.findViewById(R.id.item_date);
            location = itemView.findViewById(R.id.item_location);
            name = itemView.findViewById(R.id.item_name);
            phone = itemView.findViewById(R.id.item_phone);
            detail = itemView.findViewById(R.id.item_detail);
        }
    }
    public interface ItemClickListener {
        void onItemClick(Item item);
    }

}
