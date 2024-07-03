package com.example.proiect_android_ionanamaria;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCard extends AppCompatActivity {

    Button btnAdd;
    EditText etBankName, etCardNum, etCVV, etYr, etMth, etBalance, etCurrency;
    TextView tvTitle;
    Card card=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_card);

        btnAdd=findViewById(R.id.btnAdd);
        etCardNum=findViewById(R.id.etCardNumber);
        etBankName=findViewById(R.id.etBankName);

        etCVV=findViewById(R.id.etCVV);
        etYr=findViewById(R.id.etYr);
        etMth=findViewById(R.id.etMth);
        etBalance=findViewById(R.id.etBalance);
        etCurrency=findViewById(R.id.etCurrency);
        tvTitle=findViewById(R.id.tvAddText);


        int pos=(int) getIntent().getIntExtra("Pos",-1);
        card=(Card) getIntent().getSerializableExtra("Card");
        if(card!=null && pos!=-1){
            popData();
            //tvTitle.setText(R.string.add_activity_title_change);
            //btnAdd.setText(R.string.add_activity_btn_change);
        }

        btnAdd.setOnClickListener( view -> {
            if(validation()){
                initCard();
                Intent intent=new Intent();
               intent.putExtra("Card",card);
                intent.putExtra("Pos", pos);

                setResult(RESULT_OK, intent);

                Toast.makeText(this, "Card created !", Toast.LENGTH_SHORT).show();

                finish();
            }else{
                Toast.makeText(this, "Complete all the data!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private boolean validation() {
        boolean valid = true;
        if (etBankName.getText().toString().isEmpty()) {
            valid = false;
            etBankName.setError("Bank name is missing!");
        }
        if (etCardNum.length() == 0) {
            valid = false;
            etCardNum.setError("Card number is missing!");
        }

        if(etCVV.length()==0){
        valid=false;
        etCVV.setError("CVV is missing!");
    }
        if(etYr.length()==0){
        valid=false;
        etYr.setError("The year is missing!");
    }
        if(etMth.length()==0){
        valid=false;
        etMth.setError("The Month is missing!");
    }
        if(etBalance.length()==0){
            valid=false;
            etBalance.setError("Balance is missing is missing!");
        }
        if (etCurrency.getText().toString().isEmpty()) {
            valid = false;
            etCurrency.setError("Write the currency!");
        }


        return valid;
}


    void initCard(){
        if(card==null) {
            card = new Card();
        }

              //  parseInt(etCardNumber.getText().toString()));
        card.setBankName(etBankName.getText().toString());
        card.setCardCVV(Integer.parseInt(etCVV.getText().toString()));
        card.setCardNum(Long.valueOf(etCardNum.getText().toString()));
        card.setExpYr(Integer.parseInt(etYr.getText().toString()));
        card.setExpMth(Integer.parseInt(etMth.getText().toString()));
        card.setBalance(Integer.parseInt(etBalance.getText().toString()));
        card.setCurrency(etCurrency.getText().toString());



    }

    void popData(){
        etBankName.setText(card.getBankName());
        etCardNum.setText(String.valueOf(card.getCardNum()));
        etYr.setText(String.valueOf(card.getExpYr()));
        etCVV.setText(String.valueOf(card.getCardCVV()));
        etMth.setText(String.valueOf(card.getExpMth()));
        etBalance.setText(String.valueOf(card.getBalance()));
        etCurrency.setText(String.valueOf(card.getCurrency()));


    }
}

