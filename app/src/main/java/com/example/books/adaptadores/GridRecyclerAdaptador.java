package com.example.books.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.books.R;
import com.example.books.databinding.ItemGridLivroBinding;
import com.example.books.modelo.Livro;

import java.util.ArrayList;

public class GridRecyclerAdaptador extends RecyclerView.Adapter<GridRecyclerAdaptador.ItemViewHolder> {


    private ArrayList <Livro> livros;
    private OnLivroClickListener listener;
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemGridLivroBinding binding;
        public ItemViewHolder(ItemGridLivroBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind (Livro livro){
            binding.imgCapa.setImageResource(livro.getCapa());
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onLivroClick(livro);

                }
            });

        }

    }

    public  GridRecyclerAdaptador(ArrayList<Livro> livros, OnLivroClickListener listener){
        this.livros = livros;
        this.listener = listener;
    }
    public interface OnLivroClickListener{

        void onLivroClick(Livro livro);
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       ItemGridLivroBinding binding =  ItemGridLivroBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);


        return new ItemViewHolder(binding) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Livro livro = livros.get(position);
        holder.bind(livro);

    }

    @Override
    public int getItemCount() {
        return livros.size();
    }


}
