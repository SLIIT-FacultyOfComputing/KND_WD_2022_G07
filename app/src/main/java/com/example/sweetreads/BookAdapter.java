package com.example.sweetreads;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookMyViewHolder>
{
    ArrayList<BookModel> book_data_list;

    public BookAdapter(ArrayList<BookModel> book_data_list) {
        this.book_data_list = book_data_list;
    }

    @NonNull
    @Override
    public BookMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);

        return new BookMyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookMyViewHolder holder, int position) {
        holder.t1.setText("Name : " + book_data_list.get(position).getBook_name());
        holder.t2.setText("Price : " + book_data_list.get(position).getBook_price() + " LKR");
        holder.t3.setText("Url : " + book_data_list.get(position).getBook_url());
    }

    @Override
    public int getItemCount() {
        return book_data_list.size();
    }

    class BookMyViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3;
        public BookMyViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.t1);
            t2=itemView.findViewById(R.id.t2);
            t3=itemView.findViewById(R.id.t3);

        }
    }
}
