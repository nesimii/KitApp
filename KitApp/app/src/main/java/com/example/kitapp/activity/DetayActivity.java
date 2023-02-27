package com.example.kitapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kitapp.R;
import com.example.kitapp.veritabani.Veritabani;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DetayActivity extends AppCompatActivity {
    private ImageView imgKitapResim;
    private EditText editTextKitapAdi, editTextYazari, editTextSayfa, editTextBaski, editTextISBN, editTextOzet;
    private TextView textViewKategori;
    private String kitapId, kitapAdi, kitapYazari, kitapKategori, kitapSayfa, kitapBaski, kitaPISBN, kitapOzet;
    private Bitmap kitapResim, kucultulenResim;
    private int yeniResimKontrolu = 0;

    private Button buttonResimSec, buttonKitapDuzenle, buttonKitapKaydet, buttonKitapSil;
    private Spinner spinnerKategori;

    private final Veritabani veritabani = new Veritabani();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);
        init();
        kategoriEkle();
        kitabiDoldur();

        if (MainActivity.yoneticiGirisKontrol == 1) {
            buttonKitapDuzenle.setVisibility(View.VISIBLE);
            buttonKitapSil.setVisibility(View.VISIBLE);
        }
    }

    private void init() {
        imgKitapResim = findViewById(R.id.detayActivityİmageKitapResim);
        editTextKitapAdi = findViewById(R.id.detayActivityEditTextKitapAdi);
        editTextYazari = findViewById(R.id.detayActivityEditTextYazari);
        textViewKategori = findViewById(R.id.detayActivityTextViewKategori);
        editTextSayfa = findViewById(R.id.detayActivityEditTextSayfa);
        editTextBaski = findViewById(R.id.detayActivityEditTextBaski);
        editTextISBN = findViewById(R.id.detayActivityEditTextISBN);
        editTextOzet = findViewById(R.id.detayActivityEditTextOzet);

        buttonResimSec = findViewById(R.id.detayActivityButtonResimSec);
        buttonKitapDuzenle = findViewById(R.id.detayActivityButtonKitapDuzenle);
        buttonKitapKaydet = findViewById(R.id.detayActivityButtonKitapKaydet);
        buttonKitapSil = findViewById(R.id.detayActivityButtonKitapSil);
        spinnerKategori = findViewById(R.id.detayActivitySpinnerKategori);
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
        spinnerKategori.setAdapter(arrayAdapter);
    }

    private void kitabiDoldur() {
        Intent veri = getIntent();

        String text = veri.getStringExtra("liste");
        if (text.equals("1")) {
            kitapId = KitapListesiActivity.kitapDetayi.getKitapId();
            kitapResim = KitapListesiActivity.kitapDetayi.getKitapResmi();
            kitapAdi = KitapListesiActivity.kitapDetayi.getKitapAdi();
            kitapYazari = KitapListesiActivity.kitapDetayi.getKitapYazari();
            kitapKategori = KitapListesiActivity.kitapDetayi.getKitapKategori();
            kitapSayfa = KitapListesiActivity.kitapDetayi.getKitapSayfa();
            kitapBaski = KitapListesiActivity.kitapDetayi.getKitapBaski();
            kitaPISBN = KitapListesiActivity.kitapDetayi.getKitaPISBN();
            kitapOzet = KitapListesiActivity.kitapDetayi.getKitapOzet();
        } else if (text.equals("2")) {
            kitapId = KitapOnerilerimActivity.kitapDetayi.getKitapId();
            kitapResim = KitapOnerilerimActivity.kitapDetayi.getKitapResmi();
            kitapAdi = KitapOnerilerimActivity.kitapDetayi.getKitapAdi();
            kitapYazari = KitapOnerilerimActivity.kitapDetayi.getKitapYazari();
            kitapKategori = KitapOnerilerimActivity.kitapDetayi.getKitapKategori();
            kitapSayfa = KitapOnerilerimActivity.kitapDetayi.getKitapSayfa();
            kitapBaski = KitapOnerilerimActivity.kitapDetayi.getKitapBaski();
            kitaPISBN = KitapOnerilerimActivity.kitapDetayi.getKitaPISBN();
            kitapOzet = KitapOnerilerimActivity.kitapDetayi.getKitapOzet();
        }

        if (!TextUtils.isEmpty(kitapAdi) && !TextUtils.isEmpty(kitapYazari) && !TextUtils.isEmpty(kitapKategori) && !TextUtils.isEmpty(kitapSayfa) && !TextUtils.isEmpty(kitapBaski) && !TextUtils.isEmpty(kitaPISBN) && !TextUtils.isEmpty(kitapOzet)) {
            editTextKitapAdi.setText(kitapAdi);
            editTextYazari.setText(kitapYazari);
            textViewKategori.setText(kitapKategori);
            editTextSayfa.setText(kitapSayfa);
            editTextBaski.setText(kitapBaski);
            editTextISBN.setText(kitaPISBN);
            editTextOzet.setText(kitapOzet);
            imgKitapResim.setImageBitmap(kitapResim);
        }
    }

    private void toastMesaji(String mesaj) {
        Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_SHORT).show();
    }

    private void gorunumAc() {
        editTextKitapAdi.setEnabled(true);
        editTextYazari.setEnabled(true);
        textViewKategori.setEnabled(true);
        editTextSayfa.setEnabled(true);
        editTextBaski.setEnabled(true);
        editTextISBN.setEnabled(true);
        editTextOzet.setEnabled(true);
        imgKitapResim.setClickable(true);
        spinnerKategori.setVisibility(View.VISIBLE);
        buttonResimSec.setVisibility(View.VISIBLE);

        buttonKitapDuzenle.setVisibility(View.GONE);
        buttonKitapKaydet.setVisibility(View.VISIBLE);
    }

    private void gorunumKapat() {
        editTextKitapAdi.setEnabled(false);
        editTextYazari.setEnabled(false);
        textViewKategori.setEnabled(false);
        editTextSayfa.setEnabled(false);
        editTextBaski.setEnabled(false);
        editTextISBN.setEnabled(false);
        editTextOzet.setEnabled(false);
        imgKitapResim.setClickable(false);
        spinnerKategori.setVisibility(View.GONE);
        buttonResimSec.setVisibility(View.GONE);

        buttonKitapDuzenle.setVisibility(View.VISIBLE);
        buttonKitapKaydet.setVisibility(View.GONE);
    }

    public void butonKitapDuzenle(View view) {
        gorunumAc();
    }

    public void butonKitapKaydet(View view) {
        String ad, yazar, kategori, sayfa, baski, isbn, ozet;
        ad = editTextKitapAdi.getText().toString();
        yazar = editTextYazari.getText().toString();
        kategori = spinnerKategori.getSelectedItem().toString();
        sayfa = editTextSayfa.getText().toString();
        baski = editTextBaski.getText().toString();
        isbn = editTextISBN.getText().toString();
        ozet = editTextOzet.getText().toString();

        toastMesaji("kitap id: " + kitapId);

        if (yeniResimKontrolu == 1) {
            //resim boyut küçültme işlemi
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            kucultulenResim = Bitmap.createScaledBitmap(kitapResim, 169, 300, true);
            kucultulenResim.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] kaydedilecekResim = outputStream.toByteArray();
            if (veritabani.kitapGuncelle(kitapId, ad, yazar, kategori, sayfa, baski, isbn, ozet, kaydedilecekResim)) {
                toastMesaji("Kitap güncellendi");
                yeniResimKontrolu = 0;
                gorunumKapat();
                textViewKategori.setText(spinnerKategori.getSelectedItem().toString());
            } else {
                toastMesaji("hata oluştu");
            }
        } else {
            if (veritabani.kitapGuncelle(kitapId, ad, yazar, kategori, sayfa, baski, isbn, ozet)) {
                toastMesaji("Kitap güncellendi");
                gorunumKapat();
            } else {
                toastMesaji("hata oluştu");
            }

        }
    }

    public void butonKitapSil(View view) {
        if (veritabani.kitapSil(kitapId)) {
            toastMesaji("kitap silindi");
            Intent kitapListesi = new Intent(DetayActivity.this, KitapListesiActivity.class);
            startActivity(kitapListesi);
            finish();
        } else {
            toastMesaji("kitap silinemedi hata oluştu");
        }
    }

    public void butonResimSec(View view) {
        //izin kontrolü
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        } else {
            Intent resmiAl = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(resmiAl, 1);
        }
    }


    //kullanıcı galeri yönlendirme bölümü
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent resmiAl = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(resmiAl, 1);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //resmi galeriden alıp yerleştirme kodu
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                Uri resimUri = data.getData();
                try {
                    //sdk 28 ve üstü sürümlerde geçerli else kısmı alt sürümler
                    if (Build.VERSION.SDK_INT >= 28) {
                        ImageDecoder.Source resimSource = ImageDecoder.createSource(this.getContentResolver(), resimUri);
                        kitapResim = ImageDecoder.decodeBitmap(resimSource);
                    } else {
                        kitapResim = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resimUri);
                    }
                    imgKitapResim.setImageBitmap(kitapResim);
                    yeniResimKontrolu = 1;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        Intent kitapListesi = new Intent(this, KitapListesiActivity.class);
        startActivity(kitapListesi);
        finish();
        super.onBackPressed();
    }

}