package com.example.myapplication;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.model.Documento;

import java.util.List;

public class AdapterPerso extends BaseAdapter {

    private final List<Documento> documentos;
    private final Activity act;

    public AdapterPerso(List<Documento> documentos, Activity act) {
        this.documentos = documentos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return documentos.size();
    }

    @Override
    public Object getItem(int i) {
        return documentos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = act.getLayoutInflater().inflate(R.layout.row, viewGroup, false);
        Documento documento = documentos.get(i);

        TextView descricao = (TextView) v.findViewById(R.id.tvDescricao);
        ImageView image = (ImageView) v.findViewById(R.id.ivFoto);

        descricao.setText(documento.getDescricao());
        //image.setImageBitmap(documento.getFoto());
        return v;
    }
}
