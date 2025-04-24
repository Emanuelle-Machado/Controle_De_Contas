package com.example.controle_de_contas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    LinkedList<Categoria> categorias;
    ArrayAdapter<Categoria> adapter;
    ListView lista;
    int posEdicao = -1;
    ActivityResultLauncher<Intent> listagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        categorias = new LinkedList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categorias);
        lista = findViewById(R.id.lista_categorias);
        lista.setAdapter(adapter);
        lista.setChoiceMode( AbsListView.CHOICE_MODE_SINGLE );

        //TODO - verificar se conta pertence aquela categoria
        //Test {
        Categoria c = new Categoria("Alimentação");
        c.contas.add(new Conta("conta teste", new Date(), 20.0, true));
        adapter.add(c);
        adapter.notifyDataSetChanged();
        // } Test

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View view, int position, long id) {
                Categoria c = categorias.get( position );
                if (c.descricao != null) {
                    Intent it = new Intent(MainActivity.this, TelaConta.class);
                    it.putExtra("categoria", c);
                    startActivity( it );
                }
                return false;
            }
        });

        listagem = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult res) {
                        if (res.getResultCode() == RESULT_OK) {
                            Categoria c = (Categoria) res.getData()
                                    .getSerializableExtra("categoria");
                            if (posEdicao == -1) {
                                adapter.add(c);
                            } else {
                                categorias.set(posEdicao, c);
                                adapter.notifyDataSetChanged();
                            }
                        }
                        posEdicao = -1;
                    }
                }
        );
    }

    public void adicionar(View v){

    }

    public void editar(View v){

    }

    public void remover(View v){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Categoria retorno = (Categoria) data.getSerializableExtra("categoria");
            if (requestCode == 123) {
                adapter.add(retorno);
            } else if (requestCode == 234) {
                categorias.set( posEdicao, retorno);
                adapter.notifyDataSetChanged();
                posEdicao = -1;
            }
        }
    }
}