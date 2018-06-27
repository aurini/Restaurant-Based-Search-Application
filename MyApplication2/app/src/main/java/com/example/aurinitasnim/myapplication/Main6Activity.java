package com.example.aurinitasnim.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main6Activity extends AppCompatActivity {
    double lattitude,longitude=0;
    String price,food,nameManual;
    ListView listView;
   String [] items;
   String [] location;
   String [] menu;
    ArrayAdapter<String> itemsAdapter;
    ArrayAdapter<String> adapter;
    //Button b1;
    ProgressDialog progressDialog;

    String resname,reslocation,resmenu;
    private DatabaseReference mData;
    boolean b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
      //  FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mData= FirebaseDatabase.getInstance().getReference();

        getvalues();

        listView=findViewById(R.id.list);


        try {
            b=readItems();
        } catch (Exception e) {
            Toast.makeText(Main6Activity.this, "Not found",Toast.LENGTH_SHORT).show();
        }

        Log.e("dfjhsjdhfsjhdf","o am iiww");


            //b1=findViewById(R.id.button61);
        setupListViewListener();
        }

        //setupListViewListener();

    private void setupListViewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long rowId) {
                AlertDialog alertDialog = new AlertDialog.Builder(Main6Activity.this).create();
                alertDialog.setTitle("Details");
                alertDialog.setMessage("Are You Sure?");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Okay",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent =new Intent(Main6Activity.this,Main7Activity.class);
                                Bundle bundle=new Bundle();
                                String name=items[position];
                                bundle.putString("resName",name);
                                 bundle.putString("reslocation",location[position]);
                                bundle.putString("resmenu",menu[position]);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

                //return true;
            }
        });
    }


    private boolean readItems() throws Exception  {

        progressDialog = new ProgressDialog(Main6Activity.this);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("ProgressDialog"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    Log.e("name",nameManual+" sdfdsfsdf "+food+price);

                    mData.child(nameManual).child(food+price).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot)  {
                            if(dataSnapshot.getValue()!=null) {
                                Log.e("NAME", "burger RES" + dataSnapshot.getValue(BurgerLow.class));
                                BurgerLow burgerHigh = dataSnapshot.getValue(BurgerLow.class);
                                    resname = burgerHigh.res;
                                    reslocation = burgerHigh.location;
                                    resmenu = burgerHigh.menu;
                                    Log.e("NAME", "burger RESaa" + dataSnapshot.getValue());
                                    Log.e("NAME", "burger RESweweawe  " + burgerHigh.res);
                                    Log.e("NAME", "burger RESweweawe asdas " + resname);
                                    items = resname.split(";");
                                    for (int i = 0; i < items.length; i++) {

                                        Log.e("STRING print", items[i]);
                                    }
                                    adapter = new ArrayAdapter<String>(Main6Activity.this, android.R.layout.simple_list_item_1, items);
                                    listView.setAdapter(adapter);

                                    location = reslocation.split(";");
                                    menu = resmenu.split(";");
                                }
                            else
                                {
                                    Toast.makeText(Main6Activity.this, "nothing to matched", Toast.LENGTH_SHORT).show();
                                }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                            Log.e("eerrroor","error cannot accesss");
                        }
                    });
                    Thread.sleep(4000);
                }
                catch (Exception e)
                {

                }
                progressDialog.dismiss();
                Log.e("dasdnbjashfbjhdsbfkjhs","asdnkjsadkaaaaaaaaaa");
                b=false;
            }
        }).start();


        return b;

    }

    public void getvalues()
    {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null)
        {
            lattitude=bundle.getDouble("lattitude",lattitude);
            longitude=bundle.getDouble("logitude",longitude);
            price=bundle.getString("price",price);
            food=bundle.getString("food",food);
            nameManual=bundle.getString("nameManual",nameManual);
            System.out.println("LOGffffffffffffasfasfasdf"+"Latitde"+lattitude + "longtitude" +longitude);
            System.out.println ("NNNNAAAMMMMMMMEEEE22222222222" +
                    ""+"nameManual"+nameManual);
           /* Toast.makeText(getApplicationContext(),price + " "+food +" "+String.valueOf(lattitude) +"" +
                    " "+String.valueOf(longitude)   ,Toast.LENGTH_LONG).show();*/
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Nothing to show",Toast.LENGTH_LONG).show();
        }


    }
    @Override
    public void onStart() {
        super.onStart();




     /*   mData.child("banani").orderByChild("burgerHigh").equalTo("burgerHigh").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("NAME","burger RES "+dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


    }
}
