package com.example.aurinitasnim.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Main3Activity extends AppCompatActivity {

    private Spinner spinner1;
    Button button, button2,button3;
    //GPSTracker gps;
    LocationManager locationManager;
    double lattitude,longitude;
    private static final int REQUEST_LOCATION = 1;
    String nameManual;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
        button2 = findViewById(R.id.button2);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        button = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(Main3Activity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                nameManual=String.valueOf(spinner1.getSelectedItem());
                Intent intent=new Intent(Main3Activity.this,Main5Activity.class);
                Bundle bundle=new Bundle();
                bundle.putString("nameManual",nameManual);
                intent.putExtras(bundle);
                startActivity(intent);
            }


        });

        nameManual=String.valueOf(spinner1.getSelectedItem());

    }

    public void Onclick2(View view) {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Main3Activity.this
                    ,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude =(latti);
                longitude = (longi);

            /*    Toast.makeText(this,
                        "TEXT" +
                                "\nlong: " + String.valueOf(longitude)+
                                "\nlat: " + String.valueOf(lattitude),
                        Toast.LENGTH_SHORT).show();
                Log.d("long",String.valueOf(longitude));
                Log.d("lat",String.valueOf(lattitude));
                */


            } else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = (latti);
                longitude = (longi);

              /*  Toast.makeText(this,
                        "TEXT" +
                                "\nlong: " + String.valueOf(longitude)+
                                "\nlat: " + String.valueOf(lattitude),
                        Toast.LENGTH_SHORT).show();
                Log.d("long",String.valueOf(longitude));
                Log.d("lat",String.valueOf(lattitude));
                */



            } else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = (latti);
                longitude = (longi);

               /* Toast.makeText(this,
                        "TEXT" +
                                "\nlong: " + String.valueOf(longitude)+
                                "\nlat: " + String.valueOf(lattitude),
                        Toast.LENGTH_SHORT).show();
                Log.d("long",String.valueOf(longitude));
                Log.d("lat",String.valueOf(lattitude));
*/

            }else{

                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();

            }
        }
        Intent intent=new Intent(Main3Activity.this,Main5Activity.class);
        Bundle bundle=new Bundle();
        bundle.putDouble("lattitude",lattitude);
        bundle.putDouble("logitude",longitude);
        intent.putExtras(bundle);
        startActivity(intent);


    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(Main3Activity.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();

            Log.v("IGA", "Address" + add);
         /*   Toast.makeText(this, "Address=>" + add,
             Toast.LENGTH_SHORT).show();*/

            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
