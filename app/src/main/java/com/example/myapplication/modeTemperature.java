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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class modeTemperature extends AppCompatActivity {
    DrawerLayout drawer;
    LinearLayout standard, scientifique, temperature,retour;
    ImageView menu, btnCorrectionTemp;
    TextView txtTemp1, txtTemp2, txtTempk, selectedTxt;
    String selectedUnit1, selectedUnit2;
    Spinner spinTemp1, spinTemp2;
    Button btnCeTemp, btnPlusMoinsTemp, btnVirguleTemp, btn0Temp, btn1Temp,btn2Temp, btn3Temp, btn4Temp, btn5Temp, btn6Temp, btn7Temp, btn8Temp, btn9Temp;
    String nb;
    Boolean selectionChanged = false;
    int nbTempRestant = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_temperature);
        //Récupérer les composants menu
        drawer = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.mainMenu);
        standard = findViewById(R.id.menuStandard);
        scientifique = findViewById(R.id.menuScientifique);
        temperature = findViewById(R.id.menuTemperature);
        retour = findViewById(R.id.menuRetour);

        //Récupérer les composants
        txtTemp1 = findViewById(R.id.txtTemp1);
        txtTemp2 = findViewById(R.id.txtTemp2);
        txtTempk = findViewById(R.id.txtTempKelvin);
        spinTemp1 = findViewById(R.id.spinTemp1);
        spinTemp2 = findViewById(R.id.spinTemp2);
        //Set selected txt to txtTemp1

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
        //Text WATCHERS
        TextWatcher textWatcher1 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtTemp2.setText(String.valueOf(convertirTemperaturePrincipal(selectedUnit1, selectedUnit2)));
                convertirTemperatureSecondaire(selectedUnit1);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };

        TextWatcher textWatcher2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtTemp1.setText(String.valueOf(convertirTemperaturePrincipal(selectedUnit2, selectedUnit1)));
                convertirTemperatureSecondaire(selectedUnit2);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };
        txtTemp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTemp1.setTypeface(null, Typeface.BOLD);
                txtTemp2.setTypeface(null, Typeface.NORMAL);
                txtTemp1.removeTextChangedListener(textWatcher1);
                txtTemp2.removeTextChangedListener(textWatcher2);
                selectedTxt = txtTemp1;
                selectionChanged = true;
                //ON TXTCHANGE EVENTS
                selectedTxt.addTextChangedListener(textWatcher1);
            }
        });

        txtTemp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTemp2.setTypeface(null, Typeface.BOLD);
                txtTemp1.setTypeface(null, Typeface.NORMAL);
                txtTemp1.removeTextChangedListener(textWatcher1);
                txtTemp2.removeTextChangedListener(textWatcher2);
                selectedTxt = txtTemp2;
                selectionChanged = true;
                //ON TXTCHANGE EVENTS
                selectedTxt.addTextChangedListener(textWatcher2);
            }
        });

        btnCorrectionTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typed = selectedTxt.getText().toString();
                if(typed.length() > 1)
                {
                    selectedTxt.setText(typed.substring(0, typed.length() - 1));
                }else {
                    selectedTxt.setText("0");
                }
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
                selectionChanged = false;
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
                    if(selectionChanged || selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1){
                        nb = "0";
                    }else{
                        nb =selectedTxt.getText()+"0";
                        selectionChanged = false;
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
                    if(selectionChanged || selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1){ nb= "1"; selectionChanged = false;} else { nb = selectedTxt.getText()+"1";}
                    selectedTxt.setText(nb);
                    selectionChanged = false;
                }

            }
        });

        btn2Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    if(selectionChanged || selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1){ nb= "2"; selectionChanged = false;} else{ nb= selectedTxt.getText()+"2";}
                    selectedTxt.setText(nb);
                    selectionChanged = false;
                }
            }
        });

        btn3Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    if(selectionChanged || selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1){ nb= "3"; selectionChanged = false;} else{ nb= selectedTxt.getText()+"3";}
                    selectedTxt.setText(nb);
                    selectionChanged = false;
                }
            }
        });

        btn4Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    if(selectionChanged || selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1){ nb =  "4"; selectionChanged = false;} else{ nb = selectedTxt.getText()+"4";}
                    selectedTxt.setText(nb);
                    selectionChanged = false;
                }
            }
        });

        btn5Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    if(selectionChanged || selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1){nb= "5"; selectionChanged = false;} else{ nb=  selectedTxt.getText()+"5";}
                    selectedTxt.setText(nb);
                    selectionChanged = false;
                }
            }
        });

        btn6Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    if(selectionChanged || selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1) { nb ="6"; selectionChanged = false;} else{nb = selectedTxt.getText()+"6";}
                    selectedTxt.setText(nb);
                    selectionChanged = false;
                }
            }
        });

        btn7Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    if(selectionChanged || selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1) {nb ="7"; selectionChanged= false;} else{ nb = selectedTxt.getText()+"7";}
                    selectedTxt.setText(nb);
                    selectionChanged = false;
                }
            }
        });

        btn8Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    if(selectionChanged || selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1) {nb ="8"; selectionChanged = false;} else {nb= selectedTxt.getText() + "8";}
                    selectedTxt.setText(nb);
                    selectionChanged = false;
                }
            }
        });

        btn9Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTxt != null)
                {
                    if(selectionChanged || selectedTxt.getText().toString().equals("0")&& selectedTxt.getText().toString().indexOf(".")==-1)  {nb ="9";selectionChanged = false;} else{ nb= selectedTxt.getText() + "9";}
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
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(modeTemperature.this, afficherClients.class);
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
            result = Double.parseDouble(selectedTxt.getText().toString());
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

    public void convertirTemperatureSecondaire(String un)
    {
        String unites[] = {"Celsius", "Fahrenheit", "Kelvin"};
        String unite1="", unite2 = "";
        if(nbTempRestant == 1)
        {
            for(int i= 0; i < unites.length ; i++)
            {
                if(!unites[i].equals(selectedUnit1) && !unites[i].equals(selectedUnit2))
                {
                    unite1 = unites[i];
                    break;
                }
            }
            txtTempk.setText(String.valueOf(convertirTemperaturePrincipal(un, unite1)) + " " + unite1);
        }
        if(nbTempRestant == 2)
        {
            int ind=0;
            for(int i= 0; i < unites.length ; i++)
            {
                if(!unites[i].equals(selectedUnit1) && !unites[i].equals(selectedUnit2))
                {
                    unite1 = unites[i];
                    ind = i;
                    break;
                }
            }
            for(int i= ind+1; i < unites.length ; i++)
            {
                if(!unites[i].equals(selectedUnit1) && !unites[i].equals(selectedUnit2))
                {
                    unite2 = unites[i];
                    break;
                }
            }
            txtTempk.setText(String.valueOf(convertirTemperaturePrincipal(un, unite1)) + " " + unite1 + "    \t  " + String.valueOf(convertirTemperaturePrincipal(un, unite2)) + " " + unite2);
        }
    }
}