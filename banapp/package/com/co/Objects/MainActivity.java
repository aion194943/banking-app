package com.example.proiect_android_ionanamaria;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    public static final String EDIT_TRANSACTION = "editTransaction";
    ArrayList<Card> listCards = new ArrayList<>();
    ArrayList<User> users= new ArrayList<>();
    ArrayList<Transaction> transactions= new ArrayList<>();
    CardDatabase database;
    CardDao cardDao;
    CardsAdapter adapter;
    ListView lvCards;


    ActivityResultLauncher<Intent> getCard = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),

            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {

                        Card card = (Card) result.getData().getSerializableExtra("Card");
                        int pos = (int) result.getData().getIntExtra("Pos", -1);

                        if (pos != -1) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                cardDao.update(card);
                                listCards.set(pos, card);

                            });

                        } else {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                long id = cardDao.addCard(card);
                                card.setId(id);
                                listCards.add(card);

                            });

                        }

                        adapter.notifyDataSetChanged();

                    }
                }
            }

    );

 ActivityResultLauncher<Intent> getUser= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),

            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==RESULT_OK){

                        User user= (User) result.getData().getSerializableExtra("user");
                        users.add(user);

                    }
                }
            }

    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        Boolean nightMode = sharedPreferences.getBoolean("nightMode", false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }


        database = CardDatabase.getInstance(getApplicationContext());
        cardDao = database.getCardDao();
        FloatingActionButton fabAd = findViewById(R.id.fabAd);
        lvCards = findViewById(R.id.lvCard);

        adapter = new CardsAdapter(this, listCards);
        lvCards.setAdapter(adapter);


        fabAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCard.class);
                getCard.launch(intent);
            }
        });

        lvCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Card card = (Card) adapterView.getAdapter().getItem(i);
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).setMessage(R.string.message)
                        .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int o) {
                                Intent intent = new Intent(MainActivity.this, AddCard.class);
                                intent.putExtra("Card", card);
                                intent.putExtra("Pos", i);

                                getCard.launch(intent);
                            }
                        }).setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Executors.newSingleThreadExecutor().execute(() -> {
                                    cardDao.deleteCard(card);
                                    listCards.remove(card);
                                });
                                adapter.notifyDataSetChanged();

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create();
                alertDialog.show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    void addDatabase() {
        Executors.newSingleThreadExecutor().execute(() -> {
                    listCards.addAll(cardDao.getAllCards());
                    MainActivity.this.runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
                    });
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.main_menu_delete) {
            listCards.clear();
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Cards Deleted", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.main_menu_JSON){
            new    Thread(){
                @Override
                public void run() {
                    try {
                        listCards.addAll(Network.JSON_load());

                        new Handler(Looper.getMainLooper()).post(() -> adapter.notifyDataSetChanged());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }
        if(item.getItemId()==R.id.main_menu_add_user){
            Intent intent= new Intent(MainActivity.this, AddUser.class);
            intent.putExtra("users",users);
            getUser.launch(intent);

        }
        if (item.getItemId() == R.id.main_menu_DB) {
            Executors.newSingleThreadExecutor().execute(() -> {
                listCards.addAll(cardDao.getAllCards());
                MainActivity.this.runOnUiThread(() -> {
                    adapter.notifyDataSetChanged();
                });
            });


        }
        if(item.getItemId()==R.id.main_menu_transaction){
            Intent intent= new Intent(MainActivity.this, AddTransaction.class);
            intent.putExtra("transaction",transactions);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.main_menu_preferences){
            Intent intent=new Intent(MainActivity.this, Preferences_Activity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.main_menu_barchart){
            Intent intent= new Intent(MainActivity.this, Chart.class);
            intent.putExtra("listCh", listCards);
            startActivity(intent);
        }

            return super.onOptionsItemSelected(item);
        }
    }
