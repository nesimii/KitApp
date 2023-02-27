package com.example.kitapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.kitapp.R;
import com.example.kitapp.uye.UyeAdapter;
import com.example.kitapp.veritabani.Veritabani;

public class UyeListesiActivity extends AppCompatActivity {
    private RecyclerView mRecRecyclerView;
    private EditText editTextUyeTelefon;
    private final Veritabani veritabani = new Veritabani();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_listesi);
        init();
        uyeleriListele();

    }

    private void init() {
        mRecRecyclerView = findViewById(R.id.UyeListesiActivityRecyclerView);
        editTextUyeTelefon = findViewById(R.id.uyeListesiUyeTelefon);
    }

    private void uyeleriListele() {
        UyeAdapter adapter = new UyeAdapter(veritabani.uyeleriListele(editTextUyeTelefon.getText().toString()), this);

        mRecRecyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecRecyclerView.setLayoutManager(manager);
        mRecRecyclerView.setAdapter(adapter);
    }

    public void UyeAra(View view) {
        uyeleriListele();
    }

}