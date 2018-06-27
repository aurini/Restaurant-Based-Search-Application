package com.example.aurinitasnim.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main7Activity extends AppCompatActivity {

    TextView textView;
    Button b;
    ListView listView;
    String resName, reslocation, resmenu;
    String[] loca;
    String[] mena;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        getvalues();
        b = findViewById(R.id.butto55);
        listView = findViewById(R.id.list44);
        textView = findViewById(R.id.textView);
        textView.setText(resName + "\n" + loca[0]);
        adapter  =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mena );
        listView.setAdapter(adapter);
    }

    public void onClick(View view) throws Exception {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        try {
            if (loca[1] != null) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + loca[1])));
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,"No call Information found",Toast.LENGTH_SHORT).show();
        }


    }

    public void getvalues()
    {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null)
        {
            resName=bundle.getString("resName",resName);
            reslocation=bundle.getString("reslocation",reslocation);
            resmenu=bundle.getString("resmenu",resmenu);
            System.out.println("LOGffffffffffffasfasfasdf"+"Latitde"+reslocation + "longtitude" +resName);
            System.out.println ("NNNNAAAMMMMMMMEEEE22222222222" +
                    ""+"nameManual"+resmenu);
            loca=reslocation.split(":");
            mena=resmenu.split(">");
            Log.e("MENA   ", resmenu);
            Log.e("MENAasdsa   ", String.valueOf(mena.length));
            Log.e("MENA 1111  ",  String.valueOf(mena[0]));


        }
        else
        {
            Toast.makeText(getApplicationContext(),"Nothing to show",Toast.LENGTH_LONG).show();
        }

        String[] items2 = { "Milk", "Butter", "Yogurt", "Toothpaste", "Ice Cream" };
        if(mena==null)
            Log.e("fdkjbsdjafhjasd","asdfgsaudyfguy22222222222222222222");
     //   adapter  =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items2 );
       // listView.setAdapter(adapter);


    }


}
