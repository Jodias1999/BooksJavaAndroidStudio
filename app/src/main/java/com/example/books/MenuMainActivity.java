package com.example.books;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.books.databinding.ActivityMenuMainBinding;
import com.example.books.databinding.NavHeaderMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MenuMainActivity extends AppCompatActivity {

    public static final String EMAIL = "EMAIL";
    ActivityMenuMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        /*setContentView(R.layout.activity_menu_main);*/
        binding = ActivityMenuMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.drawerLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        String email = getIntent().getStringExtra(EMAIL);
        NavHeaderMainBinding headerBinding = NavHeaderMainBinding.bind(binding.navigationView.getHeaderView(0));
        headerBinding.tvEmail.setText(email);
        carregarToolbar();
        carregarFragmento(new ListaLivrosFragment());

        binding.navigationView.setCheckedItem(R.id.navLivrosLista);

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return selecionarItemMenu(menuItem);
            }
        });

    }

    private boolean selecionarItemMenu(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.navLivrosLista) {
            binding.toolbar.setTitle(R.string.txt_livros_lista);
            Toast.makeText(this, R.string.txt_livros_lista, Toast.LENGTH_SHORT).show();
            carregarFragmento(new ListaLivrosFragment());
            //carregarFragmento(new EstaticoFragment());
//TODO: apresentar o título e escrever na consola a opção selecionada
        } else if(id == R.id.navLivrosGrelha) {
            binding.toolbar.setTitle(R.string.txt_livros_grelha);

            Toast.makeText(this, R.string.txt_livros_grelha, Toast.LENGTH_SHORT).show();
            //carregarFragmento(new DinamicoFragment());
            carregarFragmento(new GridLivrosFragment());


        } else {
            Toast.makeText(this, R.string.enviar_email, Toast.LENGTH_SHORT).show();
        }
//TODO: completar para as 2 restantes opções (navDinamico e navEmail)
            menuItem.setChecked(true);
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void carregarToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                binding.drawerLayout, binding.toolbar, R.string.menu_abrir,
                R.string.menu_fechar);
        binding.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        drawerToggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this
                , android.R.color.white));
    }

    private void carregarFragmento(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}