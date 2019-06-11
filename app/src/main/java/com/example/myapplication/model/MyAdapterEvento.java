package com.example.myapplication.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MyAdapterEvento extends BaseAdapter {
    Context context;
    ArrayList<Evento> arr;

    public MyAdapterEvento(Context context, ArrayList<Evento> arr) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_lista_evento,parent,false);

        TextView id = (TextView) convertView.findViewById(R.id.lstIdEvento);
        TextView nome = (TextView) convertView.findViewById(R.id.lstNameEvento);
        TextView data = (TextView) convertView.findViewById(R.id.lstDataEvento);

        // Set data into textview
        id.setText(String.valueOf(arr.get(position).getId()));
        nome.setText(String.valueOf(arr.get(position).getDescricao()));
        data.setText(String.valueOf(arr.get(position).getData()));

        return convertView;
    }
}
