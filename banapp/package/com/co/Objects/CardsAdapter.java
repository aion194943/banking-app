package com.example.proiect_android_ionanamaria;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CardsAdapter extends ArrayAdapter<Card> {

    public CardsAdapter(@NonNull Context context, @NonNull List<Card> objects) {
        super(context, 0, objects);

    }

    private static class ViewHolder{
        TextView tvBankName, tvCardNumber, tvCardCVV, tvYr, tvMth, tvBalance, tvCurrency;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.card_list,parent,false);
            viewHolder= new ViewHolder();
            viewHolder.tvBankName= convertView.findViewById(R.id.tvLvBankName);
            viewHolder.tvCardNumber=convertView.findViewById(R.id.tvLvCardNumber);
            viewHolder.tvCardCVV=convertView.findViewById(R.id.tvLvCVV);
            viewHolder.tvYr=convertView.findViewById(R.id.tvLvYr);
            viewHolder.tvMth=convertView.findViewById(R.id.tvLvMth);
            viewHolder.tvBalance=convertView.findViewById(R.id.tvLvBalance);
            viewHolder.tvCurrency=convertView.findViewById(R.id.tvLvCurrency);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        Card card= getItem(position);

        viewHolder.tvBankName.setText(card.getBankName());
      //  viewHolder.tvCardNumber.setText(card.getCardNum().toString);
        viewHolder.tvCardNumber.setText(String.valueOf(card.getCardNum()));
        viewHolder.tvCardCVV.setText(String.valueOf(card.getCardNum()));
        viewHolder.tvYr.setText(String.valueOf(card.getExpYr()));
        viewHolder.tvMth.setText(String.valueOf(card.getExpMth()));
        viewHolder.tvBalance.setText(String.valueOf(card.getBalance()));
        viewHolder.tvCurrency.setText(card.getCurrency());


        return convertView;
    }
}

