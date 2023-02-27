package com.example.kitapp.kitap;

import android.graphics.Bitmap;

public class KitapDetayi {
    private String kitapId, kitapAdi, kitapYazari, kitapKategori, kitapSayfa, kitapBaski, kitaPISBN, kitapOzet;

    private Bitmap kitapResmi;

    public KitapDetayi(String kitapId, String kitapAdi, String kitapYazari, String kitapKategori, String kitapSayfa, String kitapBaski, String kitaPISBN, String kitapOzet, Bitmap kitapResmi) {
        this.kitapId = kitapId;
        this.kitapAdi = kitapAdi;
        this.kitapYazari = kitapYazari;
        this.kitapKategori = kitapKategori;
        this.kitapSayfa = kitapSayfa;
        this.kitapBaski = kitapBaski;
        this.kitaPISBN = kitaPISBN;
        this.kitapOzet = kitapOzet;
        this.kitapResmi = kitapResmi;
    }

    public String getKitapId() {
        return kitapId;
    }

    public void setKitapId(String kitapId) {
        this.kitapId = kitapId;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public String getKitapYazari() {
        return kitapYazari;
    }

    public String getKitapKategori() {
        return kitapKategori;
    }

    public String getKitapSayfa() {
        return kitapSayfa;
    }

    public String getKitapBaski() {
        return kitapBaski;
    }

    public String getKitaPISBN() {
        return kitaPISBN;
    }

    public String getKitapOzet() {
        return kitapOzet;
    }

    public Bitmap getKitapResmi() {
        return kitapResmi;
    }
}
