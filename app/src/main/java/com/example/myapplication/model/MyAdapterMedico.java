package com.example.myapplication.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MyAdapterMedico extends BaseAdapter {




    Context context;
    ArrayList<MedicoListView> arr;

    public MyAdapterMedico(Context context, ArrayList<MedicoListView> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_lista_medicos,parent,false);

        TextView id = (TextView) convertView.findViewById(R.id.lstIdMedico);
        TextView nome = (TextView) convertView.findViewById(R.id.lstNameMedico);
        TextView especialidade = (TextView) convertView.findViewById(R.id.lstEspecialidade);
        TextView exames = (TextView) convertView.findViewById(R.id.lstExameMedico);
        TextView data = (TextView) convertView.findViewById(R.id.lstDataMedico);

        // Set data into textview
        id.setText(String.valueOf(arr.get(position).getId()));
        nome.setText(String.valueOf(arr.get(position).getNome()));
        especialidade.setText(String.valueOf(arr.get(position).getEspecialidade()));
        exames.setText(String.valueOf(arr.get(position).getExames()));
        data.setText(String.valueOf(arr.get(position).getData()));

        return convertView;
    }
}
