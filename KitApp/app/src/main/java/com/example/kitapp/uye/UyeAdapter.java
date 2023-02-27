package com.example.kitapp.uye;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitapp.R;;import java.util.ArrayList;

public class UyeAdapter extends RecyclerView.Adapter<UyeAdapter.UyeHolder> {
    private ArrayList<Uye> uyeList;
    private Context context;

    public UyeAdapter(ArrayList<Uye> uyeList, Context context) {
        this.uyeList = uyeList;
        this.context = context;
    }

    @NonNull
    @Override

    public UyeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.uye_item, parent, false);
        return new UyeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UyeAdapter.UyeHolder holder, int position) {
        Uye uye = uyeList.get(position);
        holder.setData(uye);
    }

    @Override
    public int getItemCount() {
        return uyeList.size();
    }

    class UyeHolder extends RecyclerView.ViewHolder {
        TextView textViewUyeId, textViewUyeAdSoyad, textViewUyeTelefon, textViewUyeKullaniciAdi, textViewFavori1, textViewFavori2, textViewFavori3;
        Button buttonUyeSil;

        public UyeHolder(@NonNull View itemView) {
            super(itemView);

            textViewUyeId = itemView.findViewById(R.id.uyeItemUyeId);
            textViewUyeAdSoyad = itemView.findViewById(R.id.uyeItemUyeAdSoyad);
            textViewUyeTelefon = itemView.findViewById(R.id.uyeItemUyeTelefon);
            textViewUyeKullaniciAdi = itemView.findViewById(R.id.uyeItemUyeKullaniciAdi);
            textViewFavori1 = itemView.findViewById(R.id.uyeItemUyeFavori1);
            textViewFavori2 = itemView.findViewById(R.id.uyeItemUyeFavori2);
            textViewFavori3 = itemView.findViewById(R.id.uyeItemUyeFavori3);
        }

        public void setData(Uye uye) {
            this.textViewUyeId.setText("Id: " + uye.getId());
            this.textViewUyeAdSoyad.setText(uye.getAd() + " " + uye.getSoyad());
            this.textViewUyeTelefon.setText(uye.getTelefon());
            this.textViewUyeKullaniciAdi.setText("Kullanıcı Adı:" + uye.getKullaniciAdi());
            this.textViewFavori1.setText(uye.getFavori1());
            this.textViewFavori2.setText(uye.getFavori2());
            this.textViewFavori3.setText(uye.getFavori3());
        }
    }

    //class sonu
}
