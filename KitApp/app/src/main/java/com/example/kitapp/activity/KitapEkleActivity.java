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
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kitapp.R;
import com.example.kitapp.veritabani.Veritabani;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class KitapEkleActivity extends AppCompatActivity {

    private EditText editTextKitapAdi, editTextKitapYazari, editTextSayfaSayisi, editTextBaskiSayisi, editTextISBN, editTextKitapOzet;
    private Spinner spinnerKategori;
    private ImageView ImageViewResimSec;
    private Bitmap secilenResim;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_ekle);
        init();
        kategoriEkle();
    }

    private void init() {
        editTextKitapAdi = findViewById(R.id.KitapEkleAdi);
        editTextKitapYazari = findViewById(R.id.KitapEkleYazari);
        spinnerKategori = findViewById(R.id.KitapEkleKategori);
        editTextSayfaSayisi = findViewById(R.id.KitapEkleSayfaSayisi);
        editTextBaskiSayisi = findViewById(R.id.KitapEkleBaskiSayisi);
        editTextISBN = findViewById(R.id.KitapEkleISBN);
        editTextKitapOzet = findViewById(R.id.KitapEkleOzeti);
        ImageViewResimSec = findViewById(R.id.KitapEkleResimSec);
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

    private void toastMesaji(String mesaj) {
        Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_SHORT).show();
    }

    //ImageView OnClick özellliği
    public void ImageViewresimSec(View v) {
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
                        secilenResim = ImageDecoder.decodeBitmap(resimSource);
                    } else {
                        secilenResim = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resimUri);
                    }
                    ImageViewResimSec.setImageBitmap(secilenResim);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void nesneleriTemizle() {
        editTextISBN.setText("");
        editTextBaskiSayisi.setText("");
        editTextSayfaSayisi.setText("");
        editTextKitapAdi.setText("");
        editTextKitapOzet.setText("");
        editTextKitapYazari.setText("");
        Bitmap resimSecYazisi = BitmapFactory.decodeResource(this.getResources(), R.drawable.kitapsec);
        ImageViewResimSec.setImageBitmap(resimSecYazisi);
        secilenResim = null;
    }

    public void ButtonKitapKaydet(View v) {
        String kitapAdi = editTextKitapAdi.getText().toString();
        String kitapYazari = editTextKitapYazari.getText().toString();
        String kitapKategori = spinnerKategori.getSelectedItem().toString();
        String kitapSayfaSayisi = editTextSayfaSayisi.getText().toString();
        String kitapBaskiSayisi = editTextBaskiSayisi.getText().toString();
        String kitapISBN = editTextISBN.getText().toString();
        String kitapOzeti = editTextKitapOzet.getText().toString();

        if (!TextUtils.isEmpty(kitapAdi)) {
            if (!TextUtils.isEmpty(kitapYazari)) {
                if (!kitapKategori.trim().equals("Kategori Seçiniz")) {
                    if (!TextUtils.isEmpty(kitapSayfaSayisi)) {
                        if (!TextUtils.isEmpty(kitapBaskiSayisi)) {
                            if (!TextUtils.isEmpty(kitapISBN)) {
                                if (!TextUtils.isEmpty(kitapOzeti)) {
                                    if (secilenResim == null) {
                                        toastMesaji("Kitap Resmi ekleyin");
                                    } else {
                                        //seçilen resmi byte dönüştürüp küçültme


                                        //resim boyut küçültme işlemi
                                        Bitmap kucultulenResim = Bitmap.createScaledBitmap(secilenResim, 169, 300, true);
                                        kucultulenResim.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                                        byte[] kaydedilecekResim = outputStream.toByteArray();

                                        Veritabani veritabani = new Veritabani();
                                        if (veritabani.kitapEkle(kitapAdi, kitapYazari, kitapKategori, kitapSayfaSayisi, kitapBaskiSayisi, kitapISBN, kitapOzeti, kaydedilecekResim)) {
                                            toastMesaji("kitap kaydedildi");
                                            nesneleriTemizle();
                                        } else {
                                            toastMesaji("hata oluştu");
                                        }
                                    }
                                } else {
                                    toastMesaji("Kitap Özeti Boş Olamaz");
                                }
                            } else {
                                toastMesaji("ISBN Boş Olamaz");
                            }
                        } else {
                            toastMesaji("Baskı Sayısı Boş Olamaz");
                        }
                    } else {
                        toastMesaji("Sayfa Sayısı Boş Olamaz");
                    }
                } else {
                    toastMesaji("Kategori Seçiniz");
                }
            } else {
                toastMesaji("Kitap Yazarı Boş Olamaz");
            }
        } else {
            toastMesaji("Kitap Adı Boş Olamaz");
        }
    }
    //class sonu
}
