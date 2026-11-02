package com.example.books;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.books.adaptadores.ListaRecyclerAdaptador;
import com.example.books.databinding.FragmentListaLivrosBinding;
import com.example.books.modelo.Livro;
import com.example.books.modelo.SingletonGestorLivros;

import java.util.ArrayList;


public class ListaLivrosFragment extends Fragment {


    private FragmentListaLivrosBinding binding;
    private ListaRecyclerAdaptador adaptador;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListaLivrosBinding.inflate(inflater,container,false);

        ArrayList<Livro> Livros = SingletonGestorLivros.getInstance().getLivros();
        ListaRecyclerAdaptador.OnLivroClickListener listener = new ListaRecyclerAdaptador.OnLivroClickListener() {
            @Override
            public void onLivroClick(Livro livro) {
                Toast.makeText(requireContext(), livro.getTitulo(), Toast.LENGTH_SHORT).show();



            }
        };
        this.adaptador = new ListaRecyclerAdaptador(Livros,listener);

        binding.rvLivros.setLayoutManager(new LinearLayoutManager(requireContext()));
        //binding.rvLivros.setLayoutManager(new GridLayoutManager(requireContext()));
        binding.rvLivros.setAdapter(adaptador);

        return binding.getRoot();
    }
}