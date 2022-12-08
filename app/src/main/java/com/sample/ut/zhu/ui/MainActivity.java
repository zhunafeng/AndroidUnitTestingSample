package com.sample.ut.zhu.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.sample.ut.R;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkbox;
    private String lifecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lifecycle = "onCreate";

        checkbox = (CheckBox) this.findViewById(R.id.checkbox);
    }

    public void jump(View view){

        startActivity(new Intent(this, LoginActivity.class));

//        startActivity(new Intent(this, LoginMVPActivity.class));

//        startActivity(new Intent(this, LoginDaggerActivity.class));
    }

    public void showToast(View view){
        Toast.makeText(this,"Hello UT!",Toast.LENGTH_LONG).show();
    }

    public void showDialog(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("Hello UT！")
                .setTitle("Tip")
                .create();
        alertDialog.show();
    }

    public void inverse(View view){
        checkbox.setChecked(!checkbox.isChecked());
    }

    public String getLifecycleState(){
        return lifecycle;
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycle = "onStart";
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycle = "onResume";
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycle = "onPause";
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifecycle = "onStop";
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        lifecycle = "onRestart";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycle = "onDestroy";
    }
}
