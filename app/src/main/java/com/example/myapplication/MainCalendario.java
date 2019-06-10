package com.example.myapplication;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
public class MainCalendario extends Activity implements Button.OnClickListener{

    private Button botao;

    static final int DATE_DIALOG_ID = 0;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calendario);
            botao = (Button) findViewById(R.id.btn);
            botao.setOnClickListener(this);
        }

        @Override
        protected Dialog onCreateDialog(int id) {
            Calendar calendario = Calendar.getInstance();

            int ano = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            switch (id) {
                case DATE_DIALOG_ID:
                    return new DatePickerDialog(this, mDateSetListener, ano, mes,
                            dia);
            }
            return null;
        }

        private DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        String data =  String.valueOf(year) +"-"
                                + String.valueOf(monthOfYear+1) + "-" +  String.valueOf(dayOfMonth);
                        Toast.makeText(com.example.myapplication.MainCalendario.this,
                                "DATA = " + data, Toast.LENGTH_SHORT)
                                .show();
                    }
                };

        @Override
        public void onClick(View v) {
            if (v == botao)
                showDialog(DATE_DIALOG_ID);
        }

        //Botão Cancelar
        public void btnCancel(View view){
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        }
    }
