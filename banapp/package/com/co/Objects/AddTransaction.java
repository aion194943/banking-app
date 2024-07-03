package com.example.proiect_android_ionanamaria;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddTransaction  extends AppCompatActivity{

    Button btnADDTransaction;
    EditText etBeneficiary, etDateTransaction, etFBalanceValue, etIBalanceValue;
    TextView tvTransaction;
    RadioGroup radioGroup;
    Transaction transaction=new Transaction();
    ArrayList<Transaction> transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_transaction);

        btnADDTransaction=findViewById(R.id.btnADDTransaction);
        etBeneficiary=findViewById(R.id.etBeneficiary);
        etDateTransaction=findViewById(R.id.etDateTransaction);
        etIBalanceValue=findViewById(R.id.etIBalanceValue);
        etFBalanceValue=findViewById(R.id.etFBalanceValue);
        tvTransaction=findViewById(R.id.tvTransaction);
        radioGroup=findViewById(R.id.radioGroup);

        if(intent.hasExtra(MainActivity.EDIT_TRANSACTION))
        {
            Transaction transaction = (Transaction) intent.getSerializableExtra(MainActivity.EDIT_TRANSACTION);

            etBeneficiary.setText(transaction.getBeneficiary());
            etDateTransaction.setText(new SimpleDateFormat("MM/dd/yyyy", Locale.US).format(transaction.getTransactionDate()));
            etIBalanceValue.setText(String.valueOf(transaction.getInitialValue()));
            etFBalanceValue.setText(String.valueOf(transaction.getFinalValue()));
            if(transaction.getOperation().equals("Addition"))
                radioGroup.check(R.id.rbtnAddition);
            else
                radioGroup.check(R.id.rbtnSubstraction);
        }


        transactions=(ArrayList<Transaction>) getIntent().getSerializableExtra("transaction");
        btnADDTransaction.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(etBeneficiary.getText().toString().equals(""))
                    etBeneficiary.setError("Please fill in the beneficiary!");
                else
                if(etDateTransaction.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Please fill in the date!", Toast.LENGTH_LONG).show();
                else
                if(etIBalanceValue.getText().toString().isEmpty())
                    etIBalanceValue.setError("Please insert initial the value!");
                else
                if(etFBalanceValue.getText().toString().isEmpty())
                    etFBalanceValue.setError("Please insert final the value!");
                else
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                    try {
                        sdf.parse(etDateTransaction.getText().toString());
                        Date transactionDate = new Date(etDateTransaction.getText().toString());
                        String beneficiary = etBeneficiary.getText().toString();
                        float initialValue = Float.parseFloat(etIBalanceValue.getText().toString());
                        float finalValue = Float.parseFloat(etFBalanceValue.getText().toString());
                        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                        String operation = radioButton.getText().toString();

                        Transaction transaction = new Transaction(beneficiary, transactionDate, initialValue, operation,finalValue );
                        Toast.makeText(getApplicationContext(), transaction.toString(), Toast.LENGTH_LONG).show();



                        Intent intent= new Intent();
                        intent.putExtra("transaction",transaction);
                        setResult(RESULT_OK,intent);
                        transactions.add(transaction);
                        finish();
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Validation errors"+ex.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                }



        })


    ;}

}
