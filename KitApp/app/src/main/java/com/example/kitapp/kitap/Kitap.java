package com.example.kitapp.kitap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.kitapp.activity.MainActivity;

import java.util.ArrayList;
import java.util.Random;

public class Kitap {
    private String kitapId, kitapAdi, kitapYazari, kitapKategori, kitapSayfaSayisi, kitapBaskiSayisi, kitapISBN, kitapOzeti;
    private Bitmap kitapResim;

    public Kitap() {
    }

    public Kitap(String kitapId, String kitapAdi, String kitapYazari, String kitapKategori, String kitapSayfaSayisi, String kitapBaskiSayisi, String kitapISBN, String kitapOzeti, Bitmap kitapResim) {
        this.kitapId = kitapId;
        this.kitapAdi = kitapAdi;
        this.kitapYazari = kitapYazari;
        this.kitapKategori = kitapKategori;
        this.kitapSayfaSayisi = kitapSayfaSayisi;
        this.kitapBaskiSayisi = kitapBaskiSayisi;
        this.kitapISBN = kitapISBN;
        this.kitapOzeti = kitapOzeti;
        this.kitapResim = kitapResim;
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

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public String getKitapYazari() {
        return kitapYazari;
    }

    public void setKitapYazari(String kitapYazari) {
        this.kitapYazari = kitapYazari;
    }

    public String getKitapKategori() {
        return kitapKategori;
    }

    public void setKitapKategori(String kitapKategori) {
        this.kitapKategori = kitapKategori;
    }

    public String getKitapSayfaSayisi() {
        return kitapSayfaSayisi;
    }

    public void setKitapSayfaSayisi(String kitapSayfaSayisi) {
        this.kitapSayfaSayisi = kitapSayfaSayisi;
    }

    public String getKitapBaskiSayisi() {
        return kitapBaskiSayisi;
    }

    public void setKitapBaskiSayisi(String kitapBaskiSayisi) {
        this.kitapBaskiSayisi = kitapBaskiSayisi;
    }

    public String getKitapISBN() {
        return kitapISBN;
    }

    public void setKitapISBN(String kitapISBN) {
        this.kitapISBN = kitapISBN;
    }

    public String getKitapOzeti() {
        return kitapOzeti;
    }

    public void setKitapOzeti(String kitapOzeti) {
        this.kitapOzeti = kitapOzeti;
    }

    public Bitmap getKitapResim() {
        return kitapResim;
    }

    public void setKitapResim(Bitmap kitapResim) {
        this.kitapResim = kitapResim;
    }

    static public ArrayList<Kitap> kitaplariListele() {
        Kitap kitap = new Kitap();
        ArrayList<Kitap> kitapList = new ArrayList<>();

        byte[] gelenResimByte;

        /*
        Bitmap gelenResimBitmap;
        try {
            SQLiteDatabase database = context.openOrCreateDatabase("KitApp", Context.MODE_PRIVATE, null);

            Cursor cursor = database.rawQuery("SELECT * FROM kitaplar", null);
            int kitapIdIndex = cursor.getColumnIndex("id");
            int kitapAdiIndex = cursor.getColumnIndex("kitapAdi");
            int kitapYazariIndex = cursor.getColumnIndex("yazari");
            int kitapKategoriIndex = cursor.getColumnIndex("kategori");
            int kitapSayfaSayisiIndex = cursor.getColumnIndex("sayfaSayisi");
            int kitapBaskiSayisiIndex = cursor.getColumnIndex("baskiSayisi");
            int kitapISBNIndex = cursor.getColumnIndex("isbn");
            int kitapOzetiIndex = cursor.getColumnIndex("ozeti");
            int kitapResimIndex = cursor.getColumnIndex("resim");
            while (cursor.moveToNext()) {
                kitap.kitapId = cursor.getString(kitapIdIndex);
                kitap.kitapAdi = cursor.getString(kitapAdiIndex);
                kitap.kitapYazari = cursor.getString(kitapYazariIndex);
                kitap.kitapKategori = cursor.getString(kitapKategoriIndex);
                kitap.kitapSayfaSayisi = cursor.getString(kitapSayfaSayisiIndex);
                kitap.kitapBaskiSayisi = cursor.getString(kitapBaskiSayisiIndex);
                kitap.kitapISBN = cursor.getString(kitapISBNIndex);
                kitap.kitapOzeti = cursor.getString(kitapOzetiIndex);

                gelenResimByte = cursor.getBlob(kitapResimIndex);
                kitap.kitapResim = BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length);

                kitapList.add(kitap);
                kitap = new Kitap();
//eklenme kısmı
            }
            cursor.close();


            //eklenme bitiş
            return kitapList;
        } catch (Exception e) {
            Log.e("hata", e.getMessage());
        }*/

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    static public ArrayList<Kitap> kitapAra(Context context, String kategori, String kitappAdi) {
        Kitap kitap = new Kitap();
        ArrayList<Kitap> kitapList = new ArrayList<>();

        byte[] gelenResimByte;
        Bitmap gelenResimBitmap;
        try {

            SQLiteDatabase database = context.openOrCreateDatabase("KitApp", Context.MODE_PRIVATE, null);
            Cursor cursor;
            if (kategori.equals("Kategori Seçiniz")) {
                cursor = database.rawQuery("SELECT * FROM kitaplar where kitapAdi LIKE '" + kitappAdi + "%'", null);
            } else {
                cursor = database.rawQuery("SELECT * FROM kitaplar where kategori='" + kategori + "' and kitapAdi like '" + kitappAdi + "%'", null);
            }
            //Cursor cursor = database.rawQuery("SELECT * FROM kitaplar", null);
            int kitapIdIndex = cursor.getColumnIndex("id");
            int kitapAdiIndex = cursor.getColumnIndex("kitapAdi");
            int kitapYazariIndex = cursor.getColumnIndex("yazari");
            int kitapKategoriIndex = cursor.getColumnIndex("kategori");
            int kitapSayfaSayisiIndex = cursor.getColumnIndex("sayfaSayisi");
            int kitapBaskiSayisiIndex = cursor.getColumnIndex("baskiSayisi");
            int kitapISBNIndex = cursor.getColumnIndex("isbn");
            int kitapOzetiIndex = cursor.getColumnIndex("ozeti");
            int kitapResimIndex = cursor.getColumnIndex("resim");
            while (cursor.moveToNext()) {
                kitap.kitapId = cursor.getString(kitapIdIndex);
                kitap.kitapAdi = cursor.getString(kitapAdiIndex);
                kitap.kitapYazari = cursor.getString(kitapYazariIndex);
                kitap.kitapKategori = cursor.getString(kitapKategoriIndex);
                kitap.kitapSayfaSayisi = cursor.getString(kitapSayfaSayisiIndex);
                kitap.kitapBaskiSayisi = cursor.getString(kitapBaskiSayisiIndex);
                kitap.kitapISBN = cursor.getString(kitapISBNIndex);
                kitap.kitapOzeti = cursor.getString(kitapOzetiIndex);

                gelenResimByte = cursor.getBlob(kitapResimIndex);
                kitap.kitapResim = BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length);

                kitapList.add(kitap);
                kitap = new Kitap();

//eklenme kısmı
            }
            cursor.close();
            //eklenme bitiş
            return kitapList;
        } catch (Exception e) {
            Log.e("hata", e.getMessage());
        }
        return null;
    }


    static public ArrayList<Kitap> favoriKitaplarim(Context context) {
        Kitap kitap = new Kitap();
        ArrayList<Kitap> kitapList = new ArrayList<>();
        byte[] gelenResimByte;
        Cursor cursor;

        //Bitmap gelenResimBitmap;
        Random rastgele = new Random();
        int rastgeleSayi;

        try {
            SQLiteDatabase database = context.openOrCreateDatabase("KitApp", Context.MODE_PRIVATE, null);

            cursor = database.rawQuery("SELECT * FROM kitaplar where kategori='" + MainActivity.uye.getFavori1() + "'", null);
            rastgeleSayi = rastgele.nextInt(cursor.getCount() + 1);
            if (rastgeleSayi >= 1) {
                cursor = database.rawQuery("SELECT * FROM kitaplar where id=" + rastgeleSayi, null);
            }
            int kitapIdIndex = cursor.getColumnIndex("id");
            int kitapAdiIndex = cursor.getColumnIndex("kitapAdi");
            int kitapYazariIndex = cursor.getColumnIndex("yazari");
            int kitapKategoriIndex = cursor.getColumnIndex("kategori");
            int kitapSayfaSayisiIndex = cursor.getColumnIndex("sayfaSayisi");
            int kitapBaskiSayisiIndex = cursor.getColumnIndex("baskiSayisi");
            int kitapISBNIndex = cursor.getColumnIndex("isbn");
            int kitapOzetiIndex = cursor.getColumnIndex("ozeti");
            int kitapResimIndex = cursor.getColumnIndex("resim");
            while (cursor.moveToNext()) {
                kitap.kitapId = cursor.getString(kitapIdIndex);
                kitap.kitapAdi = cursor.getString(kitapAdiIndex);
                kitap.kitapYazari = cursor.getString(kitapYazariIndex);
                kitap.kitapKategori = cursor.getString(kitapKategoriIndex);
                kitap.kitapSayfaSayisi = cursor.getString(kitapSayfaSayisiIndex);
                kitap.kitapBaskiSayisi = cursor.getString(kitapBaskiSayisiIndex);
                kitap.kitapISBN = cursor.getString(kitapISBNIndex);
                kitap.kitapOzeti = cursor.getString(kitapOzetiIndex);
                gelenResimByte = cursor.getBlob(kitapResimIndex);
                kitap.kitapResim = BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length);
                kitapList.add(kitap);
                kitap = new Kitap();
//eklenme kısmı
            }
            cursor = database.rawQuery("SELECT * FROM kitaplar where kategori='" + MainActivity.uye.getFavori2() + "'", null);
            rastgeleSayi = rastgele.nextInt(cursor.getCount() + 1);
            if (rastgeleSayi >= 1) {
                cursor = database.rawQuery("SELECT * FROM kitaplar where id=" + rastgeleSayi, null);
            }

            while (cursor.moveToNext()) {
                kitap.kitapId = cursor.getString(kitapIdIndex);
                kitap.kitapAdi = cursor.getString(kitapAdiIndex);
                kitap.kitapYazari = cursor.getString(kitapYazariIndex);
                kitap.kitapKategori = cursor.getString(kitapKategoriIndex);
                kitap.kitapSayfaSayisi = cursor.getString(kitapSayfaSayisiIndex);
                kitap.kitapBaskiSayisi = cursor.getString(kitapBaskiSayisiIndex);
                kitap.kitapISBN = cursor.getString(kitapISBNIndex);
                kitap.kitapOzeti = cursor.getString(kitapOzetiIndex);
                gelenResimByte = cursor.getBlob(kitapResimIndex);
                kitap.kitapResim = BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length);
                kitapList.add(kitap);
                kitap = new Kitap();
//eklenme kısmı
            }
            cursor = database.rawQuery("SELECT * FROM kitaplar where kategori='" + MainActivity.uye.getFavori3() + "'", null);
            rastgeleSayi = rastgele.nextInt(cursor.getCount() + 1);
            if (rastgeleSayi >= 1) {
                cursor = database.rawQuery("SELECT * FROM kitaplar where id=" + rastgeleSayi, null);
            }

            while (cursor.moveToNext()) {
                kitap.kitapId = cursor.getString(kitapIdIndex);
                kitap.kitapAdi = cursor.getString(kitapAdiIndex);
                kitap.kitapYazari = cursor.getString(kitapYazariIndex);
                kitap.kitapKategori = cursor.getString(kitapKategoriIndex);
                kitap.kitapSayfaSayisi = cursor.getString(kitapSayfaSayisiIndex);
                kitap.kitapBaskiSayisi = cursor.getString(kitapBaskiSayisiIndex);
                kitap.kitapISBN = cursor.getString(kitapISBNIndex);
                kitap.kitapOzeti = cursor.getString(kitapOzetiIndex);
                gelenResimByte = cursor.getBlob(kitapResimIndex);
                kitap.kitapResim = BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length);
                kitapList.add(kitap);
                kitap = new Kitap();
//eklenme kısmı
            }
            cursor.close();
            //eklenme bitiş
            return kitapList;
        } catch (Exception e) {
            Log.e("hata", e.getMessage());
        }
        return null;
    }
}
