package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    String selectedUnit1, selectedUnit2;
    Spinner spinTemp1, spinTemp2;
    Button btnCeTemp, btnPlusMoinsTemp, btnVirguleTemp, btn0Temp, btn1Temp,btn2Temp, btn3Temp, btn4Temp, btn5Temp, btn6Temp, btn7Temp, btn8Temp, btn9Temp;
    String nb;
    int nbTempRestant = 0;

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
        //Set selected txt to txtTemp1
        selectedTxt = txtTemp1;

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

        //Initialize selectedUnits values
        selectedUnit1 = selectedUnit2 = spinTemp1.getSelectedItem().toString();

        //Buttons Events
        txtTemp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTemp1.setTypeface(null, Typeface.BOLD);
                txtTemp2.setTypeface(null, Typeface.NORMAL);
                selectedTxt = txtTemp1;
                //ON TXTCHANGE EVENTS
                selectedTxt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        txtTemp2.setText(String.valueOf(convertirTemperaturePrincipal(selectedUnit1, selectedUnit2)));
                    }
                    @Override
                    public void afterTextChanged(Editable s) {}
                });
            }
        });

        txtTemp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTemp2.setTypeface(null, Typeface.BOLD);
                txtTemp1.setTypeface(null, Typeface.NORMAL);
                selectedTxt = txtTemp2;
                //ON TXTCHANGE EVENTS
                selectedTxt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        txtTemp1.setText(String.valueOf(convertirTemperaturePrincipal(selectedUnit2, selectedUnit1)));
                    }
                    @Override
                    public void afterTextChanged(Editable s) {}
                });
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

        btn0Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    if(selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1){
                        nb = "0";
                    }else{
                        nb =selectedTxt.getText()+"0";
                    }
                    selectedTxt.setText(nb);
                }
            }
        });

        btn1Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    nb = (selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1)? "1" : selectedTxt.getText()+"1";
                    selectedTxt.setText(nb);
                }

            }
        });

        btn2Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    nb = (selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1)? "2" : selectedTxt.getText()+"2";
                    selectedTxt.setText(nb);
                }
            }
        });

        btn3Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    nb = (selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1)? "3" : selectedTxt.getText()+"3";
                    selectedTxt.setText(nb);
                }
            }
        });

        btn4Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    nb = (selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1)? "4" : selectedTxt.getText()+"4";
                    selectedTxt.setText(nb);
                }
            }
        });

        btn5Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    nb = (selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1)? "5" : selectedTxt.getText()+"5";
                    selectedTxt.setText(nb);
                }
            }
        });

        btn6Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    nb = (selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1)? "6" : selectedTxt.getText()+"6";
                    selectedTxt.setText(nb);
                }
            }
        });

        btn7Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    nb = (selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1)? "7" : selectedTxt.getText()+"7";
                    selectedTxt.setText(nb);
                }
            }
        });

        btn8Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    nb = (selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1) ? "8" : selectedTxt.getText() + "8";
                    selectedTxt.setText(nb);
                }
            }
        });

        btn9Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    nb = (selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1) ? "9" : selectedTxt.getText() + "9";
                    selectedTxt.setText(nb);
                }
            }
        });


        spinTemp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUnit1 = spinTemp1.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinTemp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUnit2 = spinTemp2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    //Conversion Functions
    public double convertirTemperaturePrincipal(String unit1, String unit2)
    {
        double valToConvert = Double.parseDouble(selectedTxt.getText().toString());
        double result = 0;
        if(unit1.equals(unit2))
        {
            result = Float.parseFloat(selectedTxt.getText().toString());
            nbTempRestant = 2;
        }
        else
        {
            nbTempRestant = 1;
            switch (unit1)
            {
                case "Celsius":
                    if(unit2.equals("Fahrenheit")){result = ((valToConvert * (9.0/5)) + 32);}
                    else if(unit2.equals("Kelvin")){ result = (valToConvert + 273.15);}

                    break;

                case "Fahrenheit":
                    if(unit2.equals("Celsius")) {result = ((valToConvert - 32) * (5.0/9));}
                    else if(unit2.equals("Kelvin")){ result = (((valToConvert -  32) * (5.0/9)) + 273.15);}
                    break;

                case "Kelvin":

                    if(unit2.equals("Celsius")){ result = (valToConvert - 273.15);}
                    else if(unit2.equals("Fahrenheit")){ result = (((valToConvert - 273.15) * (9.0/5)) + 32);}
                    break;
            }
        }
        return Math.round(result * 100.0) / 100.0;
    }
}