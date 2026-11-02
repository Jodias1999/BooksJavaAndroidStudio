package com.example.books;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.books.databinding.ActivityDetalhesLivroBinding;
import com.example.books.modelo.Livro;
import com.example.books.modelo.SingletonGestorLivros;

import java.util.ArrayList;

public class DetalhesLivroActivity extends AppCompatActivity {

    public static final String LIVRO_ID = "ID DO LIVRO";

    private ActivityDetalhesLivroBinding binding;

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

        int idlivro = getIntent().getIntExtra(LIVRO_ID,-1);

        if (idlivro == -1){
            // Criação de Livro
        }else {
            // temos de preencher
            Livro livro = SingletonGestorLivros.getInstance().getLivro(idlivro);
            if (livro != null){
                binding.imgCapa.setImageResource(livro.getCapa());
                binding.etAno.setText(livro.getAno()+"");
                binding.etAutor.setText(livro.getAutor());
                binding.etSerie.setText(livro.getSerie());
                binding.etTitulo.setText(livro.getTitulo());
                setTitle("Detalhes: "+ livro.getTitulo());
                //setTitle(String.format("detalhes %s"), livro.getTitulo());

            }

        }
    }
}