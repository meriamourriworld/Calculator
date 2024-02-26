package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class modeTemperature extends AppCompatActivity {
    DrawerLayout drawer;
    LinearLayout standard, scientifique, temperature;
    ImageView menu, btnCorrectionTemp;
    TextView txtTemp1, txtTemp2, txtTempk, selectedTxt;
    String selectedUnit1, getSelectedUnit2;
    Spinner spinTemp1, spinTemp2;
    Button btnCeTemp, btnPlusMoinsTemp, btnVirguleTemp, btn0Temp, btn1Temp,btn2Temp, btn3Temp, btn4Temp, btn5Temp, btn6Temp, btn7Temp, btn8Temp, btn9Temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_temperature);
        //Récupérer les composants menu
        drawer = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menuDevise);
        standard = findViewById(R.id.menuStandard);
        scientifique = findViewById(R.id.menuScientifique);
        temperature = findViewById(R.id.menuTemperature);

        //Récupérer les composants
        txtTemp1 = findViewById(R.id.txtTemp1);
        txtTemp2 = findViewById(R.id.txtTemp2);
        txtTempk = findViewById(R.id.txtTempKelvin);
        spinTemp1 = findViewById(R.id.spinTemp1);
        spinTemp2 = findViewById(R.id.spinTemp2);
        btnCorrectionTemp = findViewById(R.id.btnEffacerTemp);
        btnCeTemp = findViewById(R.id.btnCTemp);
        btnVirguleTemp = findViewById(R.id.btnVirguleTemp);
        btnPlusMoinsTemp = findViewById(R.id.btnPlusMoinsTemp);
        btn0Temp = findViewById(R.id.btn0Temp);
        btn1Temp = findViewById(R.id.btn1Temp);
        btn2Temp = findViewById(R.id.btn2Temp);
        btn3Temp = findViewById(R.id.btn3Temp);
        btn4Temp = findViewById(R.id.btn4Temp);
        btn5Temp = findViewById(R.id.btn5Temp);
        btn6Temp = findViewById(R.id.btn6Temp);
        btn7Temp = findViewById(R.id.btn7Temp);
        btn8Temp = findViewById(R.id.btn8Temp);
        btn9Temp = findViewById(R.id.btn9Temp);

        //Buttons Events
        txtTemp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTemp1.setTypeface(null, Typeface.BOLD);
                txtTemp2.setTypeface(null, Typeface.NORMAL);
                selectedTxt = txtTemp1;
            }
        });

        txtTemp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTemp2.setTypeface(null, Typeface.BOLD);
                txtTemp1.setTypeface(null, Typeface.NORMAL);
                selectedTxt = txtTemp2;
            }
        });

        btnCorrectionTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typed = selectedTxt.getText().toString();
                selectedTxt.setText(typed.substring(0, typed.length() - 1));
                if(selectedTxt.getText().toString().equals("")) selectedTxt.setText("0");
            }
        });

        btnCeTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTxt.setText("0");
            }
        });

        btnVirguleTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res=  (selectedTxt.getText().toString().indexOf(".") == -1)? (selectedTxt.getText() + ".") : (selectedTxt.getText() + "");
                selectedTxt.setText(res);
            }
        });

        btnPlusMoinsTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selectedTxt.getText().toString().equals("0"))
                {
                    String typedNumber = selectedTxt.getText().toString();
                    typedNumber = (typedNumber.indexOf("-")==-1)? "-"+ selectedTxt.getText().toString() : selectedTxt.getText().toString().substring(1);
                    selectedTxt.setText(typedNumber);
                }
            }
        });

        //Menu events
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawer);
            }
        });
        standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(modeTemperature.this, MainActivity.class);
            }
        });
        scientifique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(modeTemperature.this, modeScientifique.class);
            }
        });

        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
    }


    //Fonctions drawer menu
    public static void openDrawer(DrawerLayout dl){dl.openDrawer(GravityCompat.START);}
    public static void closeDrawer(DrawerLayout dl){dl.closeDrawer(GravityCompat.START);}

    public static void redirectActivity(Activity activity, Class activity1)
    {
        Intent intent = new Intent(activity, activity1);
        activity.startActivity(intent);
    }
    protected void onPause() {
        super.onPause();
        closeDrawer(drawer);
    }
}