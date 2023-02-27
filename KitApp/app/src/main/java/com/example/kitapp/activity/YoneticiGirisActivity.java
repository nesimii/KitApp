package com.example.kitapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kitapp.R;
import com.example.kitapp.veritabani.Veritabani;

public class YoneticiGirisActivity extends AppCompatActivity {

    private final Veritabani veritabani = new Veritabani();

    private LinearLayout linearLayoutGirisEkran, linearLayoutYonetimEkran;
    private EditText editTextKullaniciAdi, editTextSifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonetici_giris);
        init();
    }

    private void init() {
        editTextKullaniciAdi = findViewById(R.id.yoneticiGirisKullaniciAdi);
        editTextSifre = findViewById(R.id.yoneticiGirisSifre);

        linearLayoutGirisEkran = findViewById(R.id.yoneticiGirisPanel);
        linearLayoutYonetimEkran = findViewById(R.id.yoneticiGirisYonetimEkran);
    }

    private void toastMesaji(String mesaj) {
        Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_SHORT).show();
    }

    public void GirisYap(View view) {
        if (veritabani.yoneticiGirisi(editTextKullaniciAdi.getText().toString(), editTextSifre.getText().toString())) {
            toastMesaji("Giriş Başarılı");
            MainActivity.yoneticiGirisKontrol = 1;
            linearLayoutGirisEkran.setVisibility(View.GONE);
            linearLayoutYonetimEkran.setVisibility(View.VISIBLE);
        } else {
            toastMesaji("Bilgileriniz Hatalı");
        }
    }

    public void uyeListesi(View view) {
        Intent addKitapIntent = new Intent(this, UyeListesiActivity.class);
        startActivity(addKitapIntent);
    }

    public void kitapListesi(View view) {
        Intent addKitapIntent = new Intent(this, KitapListesiActivity.class);
        startActivity(addKitapIntent);
    }

    @Override
    public void onBackPressed() {
        MainActivity.yoneticiGirisKontrol = 0;
        super.onBackPressed();
    }

    public void kitapEkle(View view) {
        Intent addKitapIntent = new Intent(this, KitapEkleActivity.class);
        startActivity(addKitapIntent);
    }
}
