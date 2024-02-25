package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtOperation, txtTyping;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPlus, btnMoins, btnMultiplication, btnDivision, btnPourcentage, btnCe, btnC, btnUnSurX, btnCarre, btnRacineCarre, btnMc, btnMr, btnMplus, btnMmoins, btnMs, btnVirgule, btnPlusMoins, btnEgale;
    ImageButton btnEffacer;
    Boolean symbol = false, equal=false;
    String nb;
    float val=0;
    String operation="";
    //Menu Declarations
    DrawerLayout drawer;
    LinearLayout standard, scientifique;
    ImageView menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupérer les composants menu
        drawer = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        standard = findViewById(R.id.menuStandard);
        scientifique = findViewById(R.id.menuScientifique);

        //Creation of views
        txtOperation = findViewById(R.id.txtOperation);
        txtTyping = findViewById(R.id.txtTyping);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnPlus = findViewById(R.id.btnPlus);
        btnMoins = findViewById(R.id.btnMoins);
        btnMultiplication = findViewById(R.id.btnMultiplication);
        btnDivision = findViewById(R.id.btnDivision);
        btnPourcentage = findViewById(R.id.btnPourcent);
        btnC = findViewById(R.id.btnC);
        btnCe = findViewById(R.id.btnCe);
        btnEffacer = findViewById(R.id.btnEffacer);
        btnEgale = findViewById(R.id.btnEgale);
        btnVirgule = findViewById(R.id.btnVirgule);
        btnPlusMoins = findViewById(R.id.btnPlusMoins);
        btnUnSurX = findViewById(R.id.btnUnSurX);
        btnEffacer = findViewById(R.id.btnEffacer);
        btnMc = findViewById(R.id.btnMc);
        btnMr = findViewById(R.id.btnMr);
        btnMplus = findViewById(R.id.btnMplus);
        btnMmoins = findViewById(R.id.btnMmoins);
        btnMs = findViewById(R.id.btnMs);
        btnCarre = findViewById(R.id.btnCarre);
        btnRacineCarre = findViewById(R.id.btnRacineCarre);

        //Adding Events Listener
            //Numbers Buttons
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtTyping.getText().equals("0")&& txtTyping.getText().toString().indexOf(".")==-1  || symbol==true){
                    nb = "0";
                }else{
                    nb =txtTyping.getText()+"0";
                }
                txtTyping.setText(nb);
                symbol = false;
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nb = (txtTyping.getText().equals("0")&& txtTyping.getText().toString().indexOf(".")==-1   || symbol==true)? "1" : txtTyping.getText()+"1";
                txtTyping.setText(nb);
                symbol = false;
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nb = (txtTyping.getText().equals("0")&& txtTyping.getText().toString().indexOf(".")==-1   || symbol==true)? "2" : txtTyping.getText()+"2";
                txtTyping.setText(nb);
                symbol = false;
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nb = (txtTyping.getText().equals("0")&& txtTyping.getText().toString().indexOf(".")==-1   || symbol==true)? "3" : txtTyping.getText()+"3";
                txtTyping.setText(nb);
                symbol = false;
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nb = (txtTyping.getText().equals("0")&& txtTyping.getText().toString().indexOf(".")==-1   || symbol==true)? "4" : txtTyping.getText()+"4";
                txtTyping.setText(nb);
                symbol = false;
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nb = (txtTyping.getText().equals("0")&& txtTyping.getText().toString().indexOf(".")==-1   || symbol==true)? "5" : txtTyping.getText()+"5";
                txtTyping.setText(nb);
                symbol = false;
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nb = (txtTyping.getText().equals("0")&& txtTyping.getText().toString().indexOf(".")==-1   || symbol==true)? "6" : txtTyping.getText()+"6";
                txtTyping.setText(nb);
                symbol = false;
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nb = (txtTyping.getText().equals("0")&& txtTyping.getText().toString().indexOf(".")==-1   || symbol==true)? "7" : txtTyping.getText()+"7";
                txtTyping.setText(nb);
                symbol = false;
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nb = (txtTyping.getText().equals("0")&& txtTyping.getText().toString().indexOf(".")==-1   || symbol) ? "8" : txtTyping.getText() + "8";
                txtTyping.setText(nb);
                symbol = false;
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nb = (txtTyping.getText().equals("0")&& txtTyping.getText().toString().indexOf(".")==-1   || symbol) ? "9" : txtTyping.getText() + "9";
                txtTyping.setText(nb);
                symbol = false;
            }
        });

            //Clear Buttons
        btnCe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTyping.setText("0");
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTyping.setText("0");
                txtOperation.setText("");
                val = 0;
                symbol = false;
                equal = false;
            }
        });

            //Operations Buttons
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if(operation.equals("")){operation = "+";}
                if(equal == false) {
                    switch(operation)
                    {
                        case "+": val += Float.parseFloat(txtTyping.getText().toString()); break;
                        case "-": val -= Float.parseFloat(txtTyping.getText().toString()); break;
                        case "*": val *= Float.parseFloat(txtTyping.getText().toString()); break;
                        case "/": val /= Float.parseFloat(txtTyping.getText().toString()); break;
                    }
                }
                if((int)val == val) {
                    txtOperation.setText((int)val + " + ");
                    txtTyping.setText((int)val+"");
                } else {
                    txtOperation.setText(val + " + ");
                    txtTyping.setText(val+"");
                }
                symbol = true;
                equal = false;
                operation = "+";
            }
        });

        btnMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operation.equals("")){operation = "-";}
                if(equal == false) {
                    switch(operation)
                    {
                        case "+": val += Float.parseFloat(txtTyping.getText().toString()); break;
                        case "-": val -= Float.parseFloat(txtTyping.getText().toString()); break;
                        case "*": val *= Float.parseFloat(txtTyping.getText().toString()); break;
                        case "/": val /= Float.parseFloat(txtTyping.getText().toString()); break;
                    }
                }
                if((int)val == val) {
                    txtOperation.setText((int)val + " - ");
                    txtTyping.setText((int)val+"");
                } else {
                    txtOperation.setText(val + " - ");
                    txtTyping.setText(val+"");
                }
                symbol = true;
                equal = false;
                operation = "-";
            }
        });

        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(val==0){val = 1;}
                if(operation.equals("")){operation = "*";}
                if(equal == false) {
                    Log.d("MULTI VAL",String.valueOf(val));
                    switch(operation)
                    {
                        case "+": val += Float.parseFloat(txtTyping.getText().toString()); break;
                        case "-": val -= Float.parseFloat(txtTyping.getText().toString()); break;
                        case "*": val *= Float.parseFloat(txtTyping.getText().toString()); break;
                        case "/": val /= Float.parseFloat(txtTyping.getText().toString()); break;
                    }
                }
                if((int)val == val) {
                    txtOperation.setText((int)val + " x ");
                    txtTyping.setText((int)val+"");
                } else {
                    txtOperation.setText(val + " x ");
                    txtTyping.setText(val+"");
                }
                symbol = true;
                equal = false;
                operation = "*";
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operation.equals("")){operation = "/";}
                if(equal == false) {
                    switch(operation)
                    {
                        case "+": val += Float.parseFloat(txtTyping.getText().toString()); break;
                        case "-": val -= Float.parseFloat(txtTyping.getText().toString()); break;
                        case "*": val *= Float.parseFloat(txtTyping.getText().toString()); break;
                        case "/": val /= Float.parseFloat(txtTyping.getText().toString()); break;
                    }
                }
                if((int)val == val) {
                    txtOperation.setText((int)val + " ÷ ");
                    txtTyping.setText((int)val+"");
                } else {
                    txtOperation.setText(val + " ÷ ");
                    txtTyping.setText(val+"");
                }
                symbol = true;
                equal = false;
                operation = "/";
            }
        });


        btnVirgule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String res=  (txtTyping.getText().toString().indexOf(".") == -1)? (txtTyping.getText() + ".") : (txtTyping.getText() + "");
                txtTyping.setText(res);
            }
        });

        btnEgale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float val2 = Float.parseFloat(txtTyping.getText().toString()), resultat=0;
               switch (operation)
               {
                   case "+": resultat=val + val2; break;
                   case "-": resultat=val - val2; break;
                   case "*": resultat=val * val2; break;
                   case "/": resultat=val / val2; break;
               }

                if((int)resultat == resultat) {
                    txtOperation.setText((int)val + " " + operation + " " + (int)val2 + " = " + (int)resultat);
                    txtTyping.setText((int)resultat+"");
                    val = (int) resultat;
                } else {
                    txtOperation.setText(val + " " + operation + " " + val2 + " = " + resultat);
                    txtTyping.setText(resultat+"");
                    val = resultat;
                }
                symbol = true;
                equal = true;
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
                recreate();
            }
        });
        scientifique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainActivity.this, modeScientifique.class);
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