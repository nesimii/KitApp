package com.example.kitapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kitapp.R;
import com.example.kitapp.uye.Uye;
import com.example.kitapp.veritabani.Veritabani;

public class MainActivity extends AppCompatActivity {
    private EditText editTextKullaniciAdi, editTextSifre;
    private final Veritabani veritabani = new Veritabani();
    static public Uye uye = new Uye();
    static public int yoneticiGirisKontrol = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void buttonUyeGirisi(View view) {
        if (veritabani.uyeGirisi(editTextKullaniciAdi.getText().toString(), editTextSifre.getText().toString())) {
            toastMesaji("bilgiler doğru");
            nesneleriTemizle();
            Intent addKitapIntent = new Intent(this, KitapListesiActivity.class);
            startActivity(addKitapIntent);
        } else {
            toastMesaji("kullanıcı adı veya şifre hatalı");
        }
    }

    public void buttonUyeol(View view) {
        Intent addKitapIntent = new Intent(this, UyeOlActivity.class);
        startActivity(addKitapIntent);
    }

    public void buttonYoneticiGirisi(View view) {
        Intent addKitapIntent = new Intent(this, YoneticiGirisActivity.class);
        startActivity(addKitapIntent);
    }

    private void toastMesaji(String mesaj) {
        Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_SHORT).show();
    }

    private void init() {
        editTextKullaniciAdi = findViewById(R.id.mainUyeKullaniciadi);
        editTextSifre = findViewById(R.id.mainUyeSifre);
    }

    private void nesneleriTemizle() {
        editTextKullaniciAdi.setText("");
        editTextSifre.setText("");
    }

    //class sonu
}