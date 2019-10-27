package com.example.myapplication;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.model.Crud;
import com.example.myapplication.model.Evento;
import com.example.myapplication.model.MedicoListView;
import com.example.myapplication.model.MyAdapterEvento;
import com.example.myapplication.model.MyAdapterMedico;
import com.example.myapplication.model.Profile;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import com.example.myapplication.model.Data;



public class MainCalendario extends Activity {

    private ListView lista;
    Crud db = new Crud(this);
    // vai salvar os objetos customizados para lista
    ArrayList<Evento> listaEventos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        db = new Crud(this);

        lista = findViewById(R.id.listViewEvento);

        listaEventos = new ArrayList<>();
        listaEventos = db.listaTodosEventos();
        MyAdapterEvento adapter = new MyAdapterEvento(this, listaEventos);
        lista.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent it = new Intent(this, MainActivity.class);
        finish();
        startActivity(it);
    }
}
