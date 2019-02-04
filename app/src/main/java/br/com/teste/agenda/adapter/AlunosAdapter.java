package br.com.teste.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.teste.agenda.ListaAlunosActivity;
import br.com.teste.agenda.R;
import br.com.teste.agenda.modelo.Aluno;

public class AlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos;
    private final Context context;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override //Quantidade de itens na lista
    public int getCount() {
        return alunos.size();
    }

    @Override //o item na posição
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override //o id do item
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override // cada item na tela
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = alunos.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView campoNome = view.findViewById(R.id.item_nome);
        campoNome.setText(aluno.getNome());

        TextView campoTelefone = view.findViewById(R.id.item_telefone);
        campoTelefone.setText(aluno.getTelefone());

        ImageView campoFoto = view.findViewById(R.id.item_foto);
        String caminhoFoto = aluno.getCaminhoFoto();

        TextView campoEndereco = view.findViewById(R.id.item_endereco);
        if(campoEndereco != null){
            campoEndereco.setText(aluno.getEndereco());
        }

        TextView campoSite = view.findViewById(R.id.item_site);
        if(campoSite != null){
            campoSite.setText(aluno.getSite());
        }

        if(caminhoFoto != null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return view;
    }
}
