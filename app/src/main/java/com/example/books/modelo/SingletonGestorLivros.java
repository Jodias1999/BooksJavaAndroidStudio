package com.example.books.modelo;


import com.example.books.R;

import java.util.ArrayList;

public class SingletonGestorLivros {

    private static SingletonGestorLivros instance = null;
    private ArrayList<Livro> livros ;

    private SingletonGestorLivros() {

        livros = new ArrayList<>();
        //livros.add(new Livro(1,2019, R.drawable.programarandroid2,"Programar Android AMSI","2a Temporada","AMSI"));
        gerarLivros();


    }
    public void gerarLivros(){
        livros.add(new Livro( R.drawable.programarandroid2, 2024, "Programar em Android AMSI - 1", "AMSI TEAM", "2ª Temporada"));
        livros.add(new Livro( R.drawable.programarandroid1, 2024, "Programar em Android AMSI - 2", "AMSI TEAM","2ª Temporada"));
        livros.add(new Livro( R.drawable.logo_ulo, 2024, "Programar em Android AMSI - 3", "AMSI TEAM","2ª Temporada"));
        livros.add(new Livro( R.drawable.programarandroid2, 2024, "Programar em Android AMSI - 4", "AMSI TEAM","2ª Temporada"));
        livros.add(new Livro( R.drawable.programarandroid1, 2024, "Programar em Android AMSI - 5", "AMSI TEAM","2ª Temporada"));
        livros.add(new Livro( R.drawable.logo_ulo, 2024, "Programar em Android AMSI - 6", "AMSI TEAM","2ª Temporada"));
        livros.add(new Livro( R.drawable.programarandroid2, 2024, "Programar em Android AMSI - 7", "AMSI TEAM","2ª Temporada"));
        livros.add(new Livro( R.drawable.programarandroid1, 2024, "Programar em Android AMSI - 8", "AMSI TEAM","2ª Temporada"));
        livros.add(new Livro( R.drawable.logo_ulo, 2024, "Programar em Android AMSI - 9", "AMSI TEAM","2ª Temporada"));
        livros.add(new Livro( R.drawable.programarandroid2, 2024, "Programar em Android AMSI - 10", "AMSI TEAM","2ª Temporada"));

    }


    public static synchronized SingletonGestorLivros getInstance(){

        if (instance == null){
            instance = new SingletonGestorLivros();
        }

        return instance;
    }

    public Livro getLivro(int id){
        Livro livro = null;

        for (Livro l:livros) {
            if (l.getId()==id){
                livro= l;
            }
        }

        return livro;
    }
    public ArrayList<Livro> getLivros() {

        return new ArrayList<>(livros);
    }

    public void deleteLivro(int id){
        Livro livro = getLivro(id);
        if (livro != null){
            livros.remove(livro);
        }
    }
    public void adicionarLivro (Livro livro){
       livros.add(livro);
    }

    public void editarLivro(Livro livro){

        Livro livroAEditar = getLivro(livro.getId());

        livroAEditar.setAno(livro.getAno());
        livroAEditar.setAutor(livro.getAutor());
        livroAEditar.setSerie(livro.getSerie());
        livroAEditar.setTitulo(livro.getTitulo());
    }




}
