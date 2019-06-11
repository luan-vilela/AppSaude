package com.example.myapplication;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.Crud;
import com.example.myapplication.model.Documento;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainDocumento extends AppCompatActivity {

    private Button btnTake;
    private Button btnSelect;
    private ImageView ivImage;
    private String foto;
    private TextView descricao;
    private Documento docUser;

    Crud db;

    //p tratamentos futuros
    private static final int PERMISSION_REQUEST_CODE = 200;
    private int GALLERY = 1, CAMERA = 2;
    //diretorio p salvar as imagens
    private static final String IMAGE_DIRECTORY = "/my_images";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documento);
        btnTake = findViewById(R.id.btnTake);
        btnSelect = findViewById(R.id.btnSelect);
        ivImage = findViewById(R.id.ivImage);
        descricao = findViewById(R.id.edtNome);
        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA);
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                            this.getContentResolver(), contentUri);
                    //salvando a imagem
                    String path = saveImage(bitmap);
                    foto = saveImage(bitmap);
                    Log.i("TAG", "Path: " + path);
                    Toast.makeText(MainDocumento.this, "Image saved",
                            Toast.LENGTH_SHORT).show();
                    ivImage.setImageBitmap(resizeImage(bitmap, 600, 700));
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainDocumento.this, "Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == CAMERA) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ivImage.setImageBitmap(resizeImage(bitmap, 600, 700));
            foto = saveImage(bitmap);
            Toast.makeText(MainDocumento.this, "Image Saved",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File directory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        //Criando o diretorio
        if (!directory.exists())
            directory.mkdirs();
        try {
            File fileImage = new File(directory,
                    Calendar.getInstance().getTimeInMillis() + ".jpg");
            fileImage.createNewFile();
            FileOutputStream fo = new FileOutputStream(fileImage);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this, new String[]{fileImage.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            return fileImage.getAbsolutePath();
        } catch (IOException el) {
            el.printStackTrace();
        }
        return "";
    }

    public static Bitmap resizeImage(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap output = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Matrix m = new Matrix();
        m.setScale((float) newWidth / bitmap.getWidth(), (float) newHeight / bitmap.getHeight());
        canvas.drawBitmap(bitmap, m, new Paint());
        return output;
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    // main logic
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    public void btnCancel(View v) {
        Intent it = new Intent(this, MeusDocumentos.class);
        startActivity(it);
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainDocumento.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermission()) {
            // Success
        } else {
            requestPermission();
        }
    }


    public void btnSave(View view) {
        int teste = 1;

        String userDescricao = descricao.getText().toString();

        if (userDescricao == null || userDescricao.equals("")) {
            descricao.setError(getString(R.string.errorDescricao));
            teste = 0;
        }
        if (foto == null || userDescricao.equals("")) {
            descricao.setError(getString(R.string.errorFoto));
            teste = 0;
        }

        if (teste == 1) {
            docUser = new Documento(userDescricao, foto);
            db.addDoc(docUser);
            Toast.makeText(this, "Adicionado com sucesso", Toast.LENGTH_LONG).show();
        }
    }
}
