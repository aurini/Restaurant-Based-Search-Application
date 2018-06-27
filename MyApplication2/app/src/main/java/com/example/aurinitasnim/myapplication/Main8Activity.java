package com.example.aurinitasnim.myapplication;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main8Activity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    Boolean b=false;

    private DatabaseReference mData;
    String plcae, resname,location,menu;
    EditText editText1,editText2,editText3;
    Button button;
    String res1,loc1;
    String men1,t;
    String [] array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);


        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        button=(Button)findViewById(R.id.button_add);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select place");
        categories.add("Banani");
        categories.add("Mohakhali");
        categories.add("Baily Road");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        mData= FirebaseDatabase.getInstance().getReference();


    }

    public void OnClick (View v)
    {

        res1=";"+editText1.getText().toString();
        loc1=";"+editText2.getText().toString();
        men1=editText3.getText().toString();
        array=men1.split(",");



        Boolean c= readItems();
        if(c= true)
        {
            Toast.makeText(Main8Activity.this,"Updated",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Main8Activity.this,"Not Updated",Toast.LENGTH_SHORT).show();
        }

       /* if(true) {

            String array[] = menu.split(",");
            Log.e("name", resname.toString());
            Log.e("location", location.toString());
            Log.e("menu", array[0] + "fsad" + array[1]);
            DatabaseReference hopperRef = mData.child(plcae).child("BurgerHigh");
           /* Map<String, Object> userUpdates = new HashMap<>();
            userUpdates.put("BurgerHigh" + "/res", ";" + resname);
            userUpdates.put("BurgerHigh" + "/location", ";" + location);

            hopperRef.updateChildren(userUpdates);*/

        }


    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {



    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        plcae = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private boolean readItems() {
        Log.e("asdda",array[0]+array[1]);
         t=array[0]+array[1];

        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {

                    mData.child(plcae).child(t).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue()!=null) {
                                Log.e("NAME", "burger RES" + dataSnapshot.getValue(BurgerLow.class));
                                BurgerLow burgerHigh = dataSnapshot.getValue(BurgerLow.class);

                                resname = burgerHigh.res;
                                location = burgerHigh.location;
                                menu = burgerHigh.menu;
                                Log.e("NAME", "burger RESaa" + dataSnapshot.getValue());
                                Log.e("NAME", "burger RESweweawe  " + burgerHigh.res);
                                Log.e("NAME", "burger RESweweawe asdas " + resname);
                            }
                            else
                            {
                                DatabaseReference hopperRef = mData.child(plcae);
                                Map<String, Object> userUpdates = new HashMap<>();
                                if(resname ==null) {

                                   String res2=editText1.getText().toString();
                                    String loc2=editText2.getText().toString();
                                    userUpdates.put(array[0] + array[1] + "/res", res2);
                                    userUpdates.put(array[0] + array[1] + "/location", loc2);
                                    userUpdates.put(array[0] + array[1] + "/menu", array[2]);
                                    hopperRef.updateChildren(userUpdates);
                                }
                              //  Toast.makeText(Main8Activity.this,"nothing to matched",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                            Log.e("eerrroor","error cannot accesss");
                        }
                    });
                    Thread.sleep(4000);

                    Log.e("name", resname.toString());
                    Log.e("location", location.toString());
                    String [] array=men1.split(",");
                   Log.e("menu", array[0] + "fsad" + array[1]+" asfsd "+array[2]);
                    DatabaseReference hopperRef = mData.child(plcae);
                    Map<String, Object> userUpdates = new HashMap<>();
                    userUpdates.put(array[0]+array[1] + "/res",  resname+res1);
                    userUpdates.put(array[0]+array[1] + "/location",   location+loc1);
                    userUpdates.put(array[0]+array[1] + "/menu", menu+";"+array[2]);
                    hopperRef.updateChildren(userUpdates);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                Log.e("dasdnbjashfbjhdsbfkjhs","asdnkjsadkaaaaaaaaaa");
                b=true;
            }
        }).start();


        return b;
    }

}
