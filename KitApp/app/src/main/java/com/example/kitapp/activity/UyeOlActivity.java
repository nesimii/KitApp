package com.example.kitapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kitapp.R;
import com.example.kitapp.veritabani.Veritabani;

import java.util.ArrayList;

public class UyeOlActivity extends AppCompatActivity {
    private EditText editTextAd, editTextSoyad, editTextTelefon, editTextKullaniciAdi, editTextSifre, editTextSifreTekrar;
    private Spinner sfavori1, sfavori2, sfavori3;
    private final Veritabani veritabani = new Veritabani();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_ol);
        init();
        kategoriEkle();
    }
    private void kategoriEkle() {
        ArrayList<String> kategoriler = new ArrayList<>();
        ArrayAdapter arrayAdapter;
        kategoriler.add("Kategori Seçiniz");
        kategoriler.add("Bilim");
        kategoriler.add("Edebiyat");
        kategoriler.add("Felsefe");
        kategoriler.add("Psikoloji");
        kategoriler.add("Sanat");
        kategoriler.add("Tarih");
        kategoriler.add("Kültür");
        kategoriler.add("Roman");
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, kategoriler);
        sfavori1.setAdapter(arrayAdapter);
        sfavori2.setAdapter(arrayAdapter);
        sfavori3.setAdapter(arrayAdapter);
    }

    private void init() {
        editTextAd = findViewById(R.id.uyeOlAdi);
        editTextSoyad = findViewById(R.id.uyeOlSoyadi);
        editTextTelefon = findViewById(R.id.uyeOlTelefon);
        editTextKullaniciAdi = findViewById(R.id.uyeOlKullaniciAdi);
        editTextSifre = findViewById(R.id.uyeOlSifre);
        editTextSifreTekrar = findViewById(R.id.uyeOlSifreTekrar);

        sfavori1 = findViewById(R.id.uyeOlFavori1);
        sfavori2 = findViewById(R.id.uyeOlFavori2);
        sfavori3 = findViewById(R.id.uyeOlFavori3);

    }

    private void toastMesaji(String mesaj) {
        Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_SHORT).show();
    }

    public void butonUyeKaydet(View view) {
        String ad = editTextAd.getText().toString();
        String soyad = editTextSoyad.getText().toString();
        String telefon = editTextTelefon.getText().toString();
        String kullaniciAdi = editTextKullaniciAdi.getText().toString();
        String sifre = editTextSifre.getText().toString();
        String sifreTekrar = editTextSifreTekrar.getText().toString();
        String favori1 = sfavori1.getSelectedItem().toString();
        String favori2 = sfavori2.getSelectedItem().toString();
        String favori3 = sfavori3.getSelectedItem().toString();

        if (!TextUtils.isEmpty(ad)) {
            if (!TextUtils.isEmpty(soyad)) {
                if (!TextUtils.isEmpty(telefon)) {
                    if (!TextUtils.isEmpty(kullaniciAdi)) {
                        if (!TextUtils.isEmpty(sifre)) {
                            if (sifre.equals(sifreTekrar)) {

                                if (veritabani.uyeKayit(ad, soyad, telefon, kullaniciAdi, sifre, favori1, favori2, favori3)) {
                                    toastMesaji("tebrikler başarıyla kayıt oldunuz");
                                    finish();
                                } else {
                                    toastMesaji("kayıt hatası oluştu");
                                }
                            } else {
                                toastMesaji("Şifreler Eşleşmiyor");
                            }
                        } else {
                            toastMesaji("Şifrenizi Giriniz");
                        }
                    } else {
                        toastMesaji("Kullanıcı Adını Giriniz");
                    }
                } else {
                    toastMesaji("Telefonunuzu Giriniz");
                }
            } else {
                toastMesaji("Soyadınızı Giriniz");
            }
        } else {
            toastMesaji("Adınızı Giriniz");
        }
    }
}