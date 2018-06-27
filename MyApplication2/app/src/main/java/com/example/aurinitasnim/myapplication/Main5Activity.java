package com.example.aurinitasnim.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main5Activity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    RadioButton low,high;
    double lattitude,longitude=0;
    String selectedSuperStar;
    Button submit;
   EditText editText;
   String s,nameManual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        submit=findViewById(R.id.button5);
        low = (RadioButton) findViewById(R.id.johnCena);
        high = (RadioButton) findViewById(R.id.randyOrton);
      //  editText=(EditText)findViewById(R.id.editText);
        getvalues();
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener( this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Burger");
        categories.add("Pizza");
        categories.add("Pasta");
        categories.add("Set Menu");
        categories.add("Fried Chicken");
        categories.add("Chicken Steak");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public void getvalues()
    {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null)
        {
            lattitude=bundle.getDouble("lattitude",lattitude);
            longitude=bundle.getDouble("logitude",longitude);
            nameManual=bundle.getString("nameManual",nameManual);
            if(nameManual==null)
            {
                nameManual="Mohakhali";
            }
            Log.e("LOGfffffffffff","Latitde"+lattitude + "longtitude" +longitude);
            /*Toast.makeText(this,
                    "TEXT" +
                            "\nlong: " + String.valueOf(longitude)+
                            "\nlat: " + String.valueOf(lattitude),
                    Toast.LENGTH_SHORT).show();*/
        }
        else {
            Toast.makeText(getApplicationContext(),"Nothing to show",Toast.LENGTH_LONG).show();
        }
    }
    public void Onclick(View view)
    {

        if (low.isChecked()) {
            selectedSuperStar = "Low";
        }
        else if (high.isChecked()) {
            selectedSuperStar = "High";
        }
        Toast.makeText(getApplicationContext(),selectedSuperStar + s ,Toast.LENGTH_LONG).show();

        Intent intent =new Intent(Main5Activity.this,Main6Activity.class);
        Bundle bundle=new Bundle();
        bundle.putDouble("latitude",lattitude);
        bundle.putDouble("logitude",longitude);
        bundle.putString("price",selectedSuperStar);
        bundle.putString("food",s);
        bundle.putString("nameManual",nameManual);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {



    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        s = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
