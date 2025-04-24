package com.example.controle_de_contas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;
import java.util.LinkedList;

public class TelaConta extends AppCompatActivity {
    LinkedList<Conta> contas;
    ArrayAdapter<Conta> adapter;
    ListView lista;
    EditText edDescricao, edValor, edVencimento;
    TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_conta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        titulo = findViewById(R.id.txt_titulo);
        edDescricao = findViewById(R.id.ed_descricao);
        edValor = findViewById(R.id.ed_valor);
        edVencimento = findViewById(R.id.ed_data);

        contas = new LinkedList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contas);

        lista = findViewById(R.id.lista_contas);
        lista.setAdapter(adapter);
        lista.setChoiceMode( AbsListView.CHOICE_MODE_SINGLE );

        Categoria c = (Categoria) getIntent().getSerializableExtra("categoria");

        if (c != null) {
            titulo.setText(c.descricao);
            adapter.addAll(c.contas);
        }
    }

    public void adicionar(View v){

    }

    public void voltar(View v){
        Categoria categoria = new Categoria(edDescricao.getText().toString());
        double valor = 0;
        if(!edValor.getText().toString().isEmpty()){
             valor = Double.parseDouble(edValor.getText().toString());
        }
        Date vencimento = new Date();
        if(!edVencimento.getText().toString().isEmpty()){
            vencimento = new Date(edVencimento.getText().toString());
        }

        Conta c = new Conta(edDescricao.getText().toString(), vencimento, valor,false);
        Intent resposta = new Intent();
        resposta.putExtra("conta", c);
        setResult(RESULT_OK, resposta);
        finish();
    }

}