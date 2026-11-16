package com.example.books;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.books.adaptadores.ListaRecyclerAdaptador;
import com.example.books.databinding.ActivityDetalhesLivroBinding;
import com.example.books.modelo.Livro;
import com.example.books.modelo.SingletonGestorLivros;

import java.util.ArrayList;

public class DetalhesLivroActivity extends AppCompatActivity {

    public static final String LIVRO_ID = "ID DO LIVRO";

    private ActivityDetalhesLivroBinding binding;

    private Livro livro;
    private int idlivro;
    private String titulo, ano, autor,serie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_detalhes_livro);


        binding = ActivityDetalhesLivroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        idlivro = getIntent().getIntExtra(LIVRO_ID,-1);

        if (idlivro == -1){
            // Criação de Livro
            binding.fabGuardar.setImageResource(R.drawable.ic_action_adicionar);
            binding.fabGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    criarLivro();
                }
            });
        }else {
            // temos de preencher
                livro = SingletonGestorLivros.getInstance().getLivro(idlivro);
            if (livro != null){
                binding.imgCapa.setImageResource(livro.getCapa());
                binding.etAno.setText(livro.getAno()+"");
                binding.etAutor.setText(livro.getAutor());
                binding.etSerie.setText(livro.getSerie());
                binding.etTitulo.setText(livro.getTitulo());
                setTitle("Detalhes: "+ livro.getTitulo());
                //setTitle(String.format("detalhes %s"), livro.getTitulo());

                binding.fabGuardar.setImageResource(R.drawable.ic_action_guardar);
                binding.fabGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editarLivro(livro);
                    }
                });

            }

        }
    }

    private boolean validarCamposLivros(){

        ano = binding.etAno.getText().toString();
        autor = binding.etAutor.getText().toString();
        serie = binding.etSerie.getText().toString();
        titulo = binding.etTitulo.getText().toString();

        if (titulo.length() <3){
            binding.etTitulo.setError("Titulo invalido");
            return false;

            } else if (!isAnoValido(ano)) {
            return false;

        }
        return true;

    }

    private boolean isAnoValido(String ano) {
        int anoInt;
        if (ano.length() != 4){
            return false;
        }

        try {
            anoInt = Integer.parseInt(ano);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    ;

    private void criarLivro() {


        if (validarCamposLivros()){
            Livro livroAuxiliar = new Livro(R.drawable.logo_ulo,Integer.parseInt(ano),titulo,autor,serie);

            SingletonGestorLivros.getInstance().adicionarLivro(livroAuxiliar);
            finish();

            // A forma que vamos fazer é através do resultado quando recebe no activity result vai pro livro
        }

    }

    private void editarLivro(Livro livroAEditar) { // primeira opção foi com int id por parametro

       /* Livro livroAEditar  = SingletonGestorLivros.getInstance().getLivro(id);
        ano = binding.etAno.getText().toString();
        autor = binding.etAutor.getText().toString();
        serie = binding.etSerie.getText().toString();
        titulo = binding.etTitulo.getText().toString();
        if (validarCamposLivros()){
            livroAEditar.setAno(Integer.parseInt(ano));
            livroAEditar.setAutor(autor);
            livroAEditar.setSerie(serie);
            livroAEditar.setTitulo(titulo);

        }*/

        if (validarCamposLivros()){
            livroAEditar.setAno(Integer.parseInt(ano));
            livroAEditar.setAutor(autor);
            livroAEditar.setSerie(serie);
            livroAEditar.setTitulo(titulo);
            SingletonGestorLivros.getInstance().editarLivro(livroAEditar);
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_remover,menu);
        MenuItem itemRemover = menu.findItem(R.id.itemRemover);

        itemRemover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                SingletonGestorLivros.getInstance().deleteLivro(idlivro);
                Toast.makeText(DetalhesLivroActivity.this, "Resultou", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}