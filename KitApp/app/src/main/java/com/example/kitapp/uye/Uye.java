package com.example.kitapp.uye;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.kitapp.kitap.Kitap;

import java.util.ArrayList;

public class Uye {

    private String id, ad, soyad, telefon, kullaniciAdi, sifre, favori1, favori2, favori3;

    public Uye() {
    }

    public Uye(String id, String ad, String soyad, String telefon, String kullaniciAdi, String sifre, String favori1, String favori2, String favori3) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.favori1 = favori1;
        this.favori2 = favori2;
        this.favori3 = favori3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getFavori1() {
        return favori1;
    }

    public void setFavori1(String favori1) {
        this.favori1 = favori1;
    }

    public String getFavori2() {
        return favori2;
    }

    public void setFavori2(String favori2) {
        this.favori2 = favori2;
    }

    public String getFavori3() {
        return favori3;
    }

    public void setFavori3(String favori3) {
        this.favori3 = favori3;
    }

    static public ArrayList<Uye> uyeleriListele(Context context, String telefon) {
        Uye uye = new Uye();
        ArrayList<Uye> uyeList = new ArrayList<>();
        try {

            SQLiteDatabase database = context.openOrCreateDatabase("KitApp", Context.MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM uyeler where uyeTelefon LIKE '" + telefon + "%'", null);

            int kitapIdIndex = cursor.getColumnIndex("id");
            int kitapAdiIndex = cursor.getColumnIndex("uyeAdi");
            int kitapYazariIndex = cursor.getColumnIndex("uyeSoyadi");
            int kitapKategoriIndex = cursor.getColumnIndex("uyeTelefon");
            int kitapSayfaSayisiIndex = cursor.getColumnIndex("kullaniciAdi");
            int kitapBaskiSayisiIndex = cursor.getColumnIndex("favori1");
            int kitapISBNIndex = cursor.getColumnIndex("favori2");
            int kitapOzetiIndex = cursor.getColumnIndex("favori3");
            while (cursor.moveToNext()) {
                uye.id = cursor.getString(kitapIdIndex);
                uye.ad = cursor.getString(kitapAdiIndex);
                uye.soyad = cursor.getString(kitapYazariIndex);
                uye.telefon = cursor.getString(kitapKategoriIndex);
                uye.kullaniciAdi = cursor.getString(kitapSayfaSayisiIndex);
                uye.favori1 = cursor.getString(kitapBaskiSayisiIndex);
                uye.favori2 = cursor.getString(kitapISBNIndex);
                uye.favori3 = cursor.getString(kitapOzetiIndex);

                uyeList.add(uye);
                uye = new Uye();

//eklenme kısmı
            }
            cursor.close();
            //eklenme bitiş
            return uyeList;
        } catch (Exception e) {
            Log.e("hata", e.getMessage());
        }
        return null;
    }
}
