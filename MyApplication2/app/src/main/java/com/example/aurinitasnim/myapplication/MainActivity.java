package com.example.aurinitasnim.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void OnClick(View v)
    {
        button1=(Button)findViewById(R.id.button_owner);
        Intent intent= new Intent(this,Main2Activity.class );
        startActivity(intent);
    }
    public void Onclick2(View view)
    {
        button2=(Button)findViewById(R.id.button_user);
        Intent intent=new Intent(this,Main3Activity.class);
        startActivity(intent);
    }
}
