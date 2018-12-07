package br.com.teste.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String[] alunos = {"Bruno","Felipe","Lucas","Patricia","Desirée"};

        //Pegando Refência do XML
        ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        //convertendo String para View
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,alunos);
        listaAlunos.setAdapter(adapter);
    }
}
