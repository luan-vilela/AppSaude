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
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainCalendario extends Activity implements Button.OnClickListener {
    private Button createNotification;
    private Button botao;

    static final int DATE_DIALOG_ID = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        botao = (Button) findViewById(R.id.btn);
        botao.setOnClickListener(this);

        createNotification = findViewById(R.id.gera_notificacao);
        createNotification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCalendario.this,
                        MainCalendario.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        MainCalendario.this, 0, intent,0);
                String CHANNEL_ID = "com.example.appnotificacao";// The internal id of the channel.
                String CHANNEL_NAME = "CHANNEL_NAME_APP_NOTIFICATION";// The public name of the channel.
                // Adicionando support
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    /* Create or update. */
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                            CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                    final NotificationManager notificationManager = (NotificationManager)
                            MainCalendario.this.getSystemService(
                                    Context.NOTIFICATION_SERVICE);
                    notificationManager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MainCalendario.this,
                                CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("Nova Mensagem")
                                .setContentText("Você recebeu uma nova mensagem")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setContentIntent( pendingIntent )
                                .setAutoCancel(true);
                NotificationCompat.InboxStyle style = new
                        NotificationCompat.InboxStyle();

                String[] desc = new String[]{"alterado agenda","alterado agenda",
                        "alterado agenda","alterado agenda"};
                for(int i=0;i<desc.length;i++){
                    style.addLine(desc[i]);
                }
                mBuilder.setStyle(style);
                NotificationManagerCompat notif =
                        NotificationManagerCompat.from(MainCalendario.this);
                notif.notify(0, mBuilder.build());
            }
        });

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
                    String data = String.valueOf(year) + "-"
                            + String.valueOf(monthOfYear + 1) + "-" + String.valueOf(dayOfMonth);
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
    public void btnCancel(View view) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
