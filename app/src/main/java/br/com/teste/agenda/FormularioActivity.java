package br.com.teste.agenda;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import br.com.teste.agenda.dao.AlunoDAO;
import br.com.teste.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if(aluno != null){
            helper.preencheFormulario(aluno);
        }

        Button botaFoto = (Button) findViewById(R.id.formulario_botao_foto);
        botaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String caminhoFoto = getExternalFilesDir(null) + "/"+ System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);
                Uri fotoUri = FileProvider.getUriForFile(FormularioActivity.this,BuildConfig.APPLICATION_ID + ".provider",arquivoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivity(intentCamera);
            }
        });

    }

    //Metodo para action bar - Listando Itens Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override  //Capiturando ação do item menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:

                Aluno aluno = helper.pegaAluno();

                AlunoDAO dao = new AlunoDAO(this);
                if(aluno.getId() != null){
                    dao.altera(aluno);
                }else{
                    dao.insere(aluno);
                }

                dao.close();

                Toast.makeText(FormularioActivity.this,"Aluno " + aluno.getNome() + " salvo!",Toast.LENGTH_SHORT).show();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
