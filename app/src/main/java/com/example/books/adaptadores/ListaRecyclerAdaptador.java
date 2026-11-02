package com.example.books.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.books.R;
import com.example.books.databinding.ItemListaLivroBinding;
import com.example.books.modelo.Livro;

import java.util.ArrayList;

public class ListaRecyclerAdaptador extends RecyclerView.Adapter<ListaRecyclerAdaptador.ItemViewHolder> {

    private ArrayList<Livro> livros ;
    private OnLivroClickListener listener;

    public ListaRecyclerAdaptador(ArrayList<Livro> livros, OnLivroClickListener listener) {
        this.livros = livros;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListaLivroBinding binding = ItemListaLivroBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                        false);
        return new ItemViewHolder(binding);
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

    public interface OnLivroClickListener{

        void onLivroClick(Livro livro);
    }

    public class ItemViewHolder extends  RecyclerView.ViewHolder{

        private  final  ItemListaLivroBinding binding;

        public ItemViewHolder(ItemListaLivroBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Livro livro){
            //binding.tvTitulo.setText(livro.getTitulo());
            //TODO: completar
            binding.tvResTitulo.setText(livro.getTitulo());
            binding.tvResSerie.setText(livro.getSerie());
            binding.tvResAutor.setText(livro.getAutor());
            binding.tvResAno.setText(livro.getAno()+"");
            binding.imgCapa.setImageResource(livro.getCapa());

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onLivroClick(livro);

                }
            });


        }

    }
}
