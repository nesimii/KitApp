package com.example.kitapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.kitapp.R;
import com.example.kitapp.kitap.KitapAdapter;
import com.example.kitapp.kitap.KitapDetayi;
import com.example.kitapp.veritabani.Veritabani;

public class KitapOnerilerimActivity extends AppCompatActivity {
    private KitapAdapter adapter;
    static public KitapDetayi kitapDetayi;
    private final Veritabani veritabani = new Veritabani();
    private RecyclerView mRecRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_onerilerim);
        favoriKitaplarim();
        kitapDetaylari();
    }

    private void kitapDetaylari() {

        adapter.setOnItemClickListener(kitap -> {
            kitapDetayi = new KitapDetayi(kitap.getKitapId(), kitap.getKitapAdi(), kitap.getKitapYazari(), kitap.getKitapKategori(), kitap.getKitapSayfaSayisi(), kitap.getKitapBaskiSayisi(), kitap.getKitapISBN(), kitap.getKitapOzeti(), kitap.getKitapResim());
            Intent detayIntent = new Intent(KitapOnerilerimActivity.this, DetayActivity.class);
            detayIntent.putExtra("liste", "2");
            startActivity(detayIntent);
            finish();
        });
    }

    private void favoriKitaplarim() {
        mRecRecyclerView = findViewById(R.id.kitapListesiActivityRecyclerView);
        adapter = new KitapAdapter(veritabani.favoriKitap(), this);

        mRecRecyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecRecyclerView.setLayoutManager(manager);
        mRecRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(kitap -> {
            kitapDetayi = new KitapDetayi(kitap.getKitapId(), kitap.getKitapAdi(), kitap.getKitapYazari(), kitap.getKitapKategori(), kitap.getKitapSayfaSayisi(), kitap.getKitapBaskiSayisi(), kitap.getKitapISBN(), kitap.getKitapOzeti(), kitap.getKitapResim());

            Intent detayIntent = new Intent(KitapOnerilerimActivity.this, DetayActivity.class);
            startActivity(detayIntent);
        });
    }
}