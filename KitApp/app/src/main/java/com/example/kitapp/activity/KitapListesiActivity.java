package com.example.kitapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kitapp.R;
import com.example.kitapp.kitap.Kitap;
import com.example.kitapp.kitap.KitapAdapter;
import com.example.kitapp.kitap.KitapDetayi;
import com.example.kitapp.veritabani.Veritabani;

import java.util.ArrayList;

public class KitapListesiActivity extends AppCompatActivity {
    private RecyclerView mRecRecyclerView;
    private KitapAdapter adapter;
    static public KitapDetayi kitapDetayi;
    private final Veritabani veritabani = new Veritabani();

    private Spinner spinnerKategori;
    private EditText editTextKitapAdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_listesi);

        init();
        kategoriEkle();
        kitaplariListele();
        kitapDetaylari();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.uyeprofilbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuBarUyeProfil) {
            Intent uyeProfilIntent = new Intent(this, UyeProfilActivity.class);
            startActivity(uyeProfilIntent);
            finish();
        } else if (item.getItemId() == R.id.menuBarKitapOnerim) {
            Intent uyeProfilIntent = new Intent(this, KitapOnerilerimActivity.class);
            startActivity(uyeProfilIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        mRecRecyclerView = findViewById(R.id.kitapListesiActivityRecyclerView);
        spinnerKategori = findViewById(R.id.kitapListesiKategori);
        editTextKitapAdi = findViewById(R.id.kitapListesiKitapAdi);
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

    private void kitapDetaylari() {

        adapter.setOnItemClickListener(kitap -> {
            kitapDetayi = new KitapDetayi(kitap.getKitapId(), kitap.getKitapAdi(), kitap.getKitapYazari(), kitap.getKitapKategori(), kitap.getKitapSayfaSayisi(), kitap.getKitapBaskiSayisi(), kitap.getKitapISBN(), kitap.getKitapOzeti(), kitap.getKitapResim());
            Intent detayIntent = new Intent(KitapListesiActivity.this, DetayActivity.class);
            detayIntent.putExtra("liste", "1");
            startActivity(detayIntent);
            finish();
        });
    }

    private void kitaplariListele() {
        adapter = new KitapAdapter(veritabani.kitaplariListele(), this);

        mRecRecyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecRecyclerView.setLayoutManager(manager);
        mRecRecyclerView.setAdapter(adapter);
    }

    public void kitapAra(View view) {
        adapter = new KitapAdapter(veritabani.kitapAra(spinnerKategori.getSelectedItem().toString(), editTextKitapAdi.getText().toString()), this);

        mRecRecyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecRecyclerView.setLayoutManager(manager);
        mRecRecyclerView.setAdapter(adapter);
        kitapDetaylari();
    }
}