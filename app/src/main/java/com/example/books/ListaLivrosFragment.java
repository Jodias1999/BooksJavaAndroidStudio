package com.example.books;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private ListaRecyclerAdaptador.OnLivroClickListener listener;



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

  // Alta insert para o over ride
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        binding.fabLista.setOnClickListener(new View.OnClickListener() { // escrevo new dentro do setOn e aparecer a função
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(requireContext(), DetalhesLivroActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_pesquisa,menu);
        MenuItem itemPesquisa = menu.findItem(R.id.itemPesquisa);
        SearchView searchView = (SearchView) itemPesquisa.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                    ArrayList<Livro> livrosOriginais = SingletonGestorLivros.getInstance().getLivros();
                    ArrayList<Livro> livrosFiltrados = new ArrayList<>();

                for (Livro livro : livrosOriginais) {
                    if (livro.getTitulo().toLowerCase().contains(newText.toLowerCase())){
                        livrosFiltrados.add(livro);
                    }

                }
                ListaRecyclerAdaptador.OnLivroClickListener listener = new ListaRecyclerAdaptador.OnLivroClickListener() {
                    @Override
                    public void onLivroClick(Livro livro) {
                        Toast.makeText(requireContext(), livro.getTitulo(), Toast.LENGTH_SHORT).show();



                    }
                };

                adaptador = new ListaRecyclerAdaptador(livrosFiltrados,listener);

                binding.rvLivros.setLayoutManager(new LinearLayoutManager(requireContext()));
                //binding.rvLivros.setLayoutManager(new GridLayoutManager(requireContext()));
                binding.rvLivros.setAdapter(adaptador);
                return true;
            }
        });



    }
}