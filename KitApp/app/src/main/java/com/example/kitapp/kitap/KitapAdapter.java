package com.example.kitapp.kitap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kitapp.R;

import java.util.ArrayList;

public class KitapAdapter extends RecyclerView.Adapter<KitapAdapter.KitapHolder> {
    private ArrayList<Kitap> kitapList;
    private Context context;
    private OnItemClickListener listener;


    public KitapAdapter(ArrayList<Kitap> kitapList, Context context) {
        this.kitapList = kitapList;
        this.context = context;
    }

    @NonNull
    @Override
    public KitapHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.kitap_item, parent, false);
        return new KitapHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KitapHolder holder, int position) {
        Kitap kitap = kitapList.get(position);
        holder.setData(kitap);
    }

    @Override
    public int getItemCount() {
        return kitapList.size();
    }

    class KitapHolder extends RecyclerView.ViewHolder {
        TextView textViewKitapAdi, textViewYazari, textViewSayfaSayisi, textViewBaskiSayisi, textViewOzet;
        ImageView imageViewKitapResim;

        public KitapHolder(@NonNull View itemView) {
            super(itemView);

            textViewKitapAdi = (TextView) itemView.findViewById(R.id.kitapItemTextViewKitapAdi);
            textViewYazari = (TextView) itemView.findViewById(R.id.kitapItemTextViewKitapYazari);
            textViewSayfaSayisi = (TextView) itemView.findViewById(R.id.kitapItemTextViewKitapSayfaSayisi);
            textViewBaskiSayisi = (TextView) itemView.findViewById(R.id.kitapItemTextViewBaskiSayisi);
            textViewOzet = (TextView) itemView.findViewById(R.id.kitapItemTextViewKitapOzeti);
            imageViewKitapResim = (ImageView) itemView.findViewById(R.id.kitapItemImageViewKitapResim);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(kitapList.get(position));
                    }
                }
            });
        }

        public void setData(Kitap kitap) {
            this.textViewKitapAdi.setText(kitap.getKitapAdi());
            this.textViewYazari.setText(kitap.getKitapYazari());
            this.textViewSayfaSayisi.setText(kitap.getKitapSayfaSayisi() + " Sayfa");
            this.textViewBaskiSayisi.setText(kitap.getKitapBaskiSayisi() + " .Baskı");
            this.textViewOzet.setText(kitap.getKitapOzeti());
            this.imageViewKitapResim.setImageBitmap(kitap.getKitapResim());
        }

    }

    public interface OnItemClickListener {
        void onItemClick(Kitap kitap);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

//class sonu
}