package com.example.books;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.books.adaptadores.GridRecyclerAdaptador;
import com.example.books.databinding.FragmentGridLivrosBinding;
import com.example.books.modelo.Livro;
import com.example.books.modelo.SingletonGestorLivros;

import java.util.ArrayList;


public class GridLivrosFragment extends Fragment {

    private  FragmentGridLivrosBinding binding;
    private  GridRecyclerAdaptador adaptador;

    public GridLivrosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentGridLivrosBinding.inflate(inflater,container,false);

        SingletonGestorLivros singleton = SingletonGestorLivros.getInstance();
        ArrayList<Livro> livros = singleton.getLivros();
        // outra maneira
        // ArrayList<Livro> livros = SingletonGestorLivros.getInstance().getLivros();

        GridRecyclerAdaptador.OnLivroClickListener listener = new GridRecyclerAdaptador.OnLivroClickListener() {
            @Override
            public void onLivroClick(Livro livro) {
                Toast.makeText(requireContext(), livro.getTitulo(), Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(requireContext(), DetalhesLivroActivity.class);
                intent.putExtra(DetalhesLivroActivity.LIVRO_ID ,livro.getId());
                startActivity(intent);


            }
        };

        this.adaptador = new GridRecyclerAdaptador(livros, listener);

        binding.rvGridLivros.setLayoutManager(new GridLayoutManager(requireContext(),2));
        binding.rvGridLivros.setAdapter(adaptador);




        return binding.getRoot();
    }
}