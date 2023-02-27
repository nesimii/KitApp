package com.example.kitapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kitapp.R;
import com.example.kitapp.veritabani.Veritabani;

import java.util.ArrayList;

public class UyeProfilActivity extends AppCompatActivity {
    private Spinner spinnerFavori1, spinnerFavori2, spinnerFavori3;
    private TextView tUyeId, tFavori1, tFavori2, tFavori3;
    private EditText eUyeAd, eUyeSoyad, eUyeTelefon, eUyeKullaniciAdi, eUyeSifre;

    private final Veritabani veritabani = new Veritabani();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_profil);

        init();
        favoriEkle();
        bilgileriYerlestir();
    }

    private void init() {
        tUyeId = findViewById(R.id.uyeProfilUyeId);

        eUyeAd = findViewById(R.id.uyeProfilUyeAd);
        eUyeSoyad = findViewById(R.id.uyeProfilUyeSoyad);
        eUyeTelefon = findViewById(R.id.uyeProfilUyeTelefon);
        eUyeKullaniciAdi = findViewById(R.id.uyeProfilUyeKullaniciAd);
        eUyeSifre = findViewById(R.id.uyeProfilUyeSifre);

        tFavori1 = findViewById(R.id.uyeProfilUyeFavori1);
        tFavori2 = findViewById(R.id.uyeProfilUyeFavori2);
        tFavori3 = findViewById(R.id.uyeProfilUyeFavori3);

        spinnerFavori1 = findViewById(R.id.uyeProfilFavoriSec1);
        spinnerFavori2 = findViewById(R.id.uyeProfilFavoriSec2);
        spinnerFavori3 = findViewById(R.id.uyeProfilFavoriSec3);

    }

    private void toastMesaji(String mesaj) {
        Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_SHORT).show();
    }

    private void favoriEkle() {
        ArrayList<String> kategoriler = new ArrayList<>();
        ArrayAdapter arrayAdapter;
        kategoriler.add("Bilim");
        kategoriler.add("Edebiyat");
        kategoriler.add("Felsefe");
        kategoriler.add("Psikoloji");
        kategoriler.add("Sanat");
        kategoriler.add("Tarih");
        kategoriler.add("Kültür");
        kategoriler.add("Roman");
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, kategoriler);
        spinnerFavori1.setAdapter(arrayAdapter);
        spinnerFavori2.setAdapter(arrayAdapter);
        spinnerFavori3.setAdapter(arrayAdapter);
    }

    private void bilgileriYerlestir() {
        tUyeId.setText(MainActivity.uye.getId());
        eUyeAd.setText(MainActivity.uye.getAd());
        eUyeSoyad.setText(MainActivity.uye.getSoyad());
        eUyeTelefon.setText(MainActivity.uye.getTelefon());
        eUyeKullaniciAdi.setText(MainActivity.uye.getKullaniciAdi());
        eUyeSifre.setText(MainActivity.uye.getSifre());
        tFavori1.setText(MainActivity.uye.getFavori1());
        tFavori2.setText(MainActivity.uye.getFavori2());
        tFavori3.setText(MainActivity.uye.getFavori3());
    }

    public void uyeGuncelle(View view) {
        String id, ad, soyad, telefon, kullaniciAdi, sifre, favori1, favori2, favori3;

        id = tUyeId.getText().toString();
        ad = eUyeAd.getText().toString();
        soyad = eUyeSoyad.getText().toString();
        telefon = eUyeTelefon.getText().toString();
        kullaniciAdi = eUyeKullaniciAdi.getText().toString();
        sifre = eUyeSifre.getText().toString();
        favori1 = spinnerFavori1.getSelectedItem().toString();
        favori2 = spinnerFavori2.getSelectedItem().toString();
        favori3 = spinnerFavori3.getSelectedItem().toString();

        if (veritabani.uyeGuncelle(id, ad, soyad, telefon, kullaniciAdi, sifre, favori1, favori2, favori3)) {
            MainActivity.uye.setAd(eUyeAd.getText().toString());
            MainActivity.uye.setSoyad(eUyeSoyad.getText().toString());
            MainActivity.uye.setTelefon(eUyeTelefon.getText().toString());
            MainActivity.uye.setKullaniciAdi(eUyeKullaniciAdi.getText().toString());
            MainActivity.uye.setSifre(eUyeSifre.getText().toString());
            MainActivity.uye.setFavori1(spinnerFavori1.getSelectedItem().toString());
            MainActivity.uye.setFavori2(spinnerFavori2.getSelectedItem().toString());
            MainActivity.uye.setFavori3(spinnerFavori3.getSelectedItem().toString());
            bilgileriYerlestir();
            toastMesaji("Bilgileriniz Güncellendi");
        } else {
            toastMesaji("hata oluştu bilgileriniz güncellenemedi");
        }
    }

    public void UyeligiSil(View view) {
        if (veritabani.uyeligiSil(tUyeId.getText().toString())) {
            toastMesaji("Üyeliğiniz iptal edildi");
            finish();
        } else {
            toastMesaji("hata oluştu üyelik silinemedi");
        }
    }

    @Override
    public void onBackPressed() {
        Intent kitapListesi = new Intent(this, KitapListesiActivity.class);
        startActivity(kitapListesi);
        finish();
        super.onBackPressed();
    }
}