package calculator.mathmate;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static calculator.mathmate.Bisection.bisectson;
import static calculator.mathmate.Derivative.derivater;
import static calculator.mathmate.Expofunc.expo;
import static calculator.mathmate.Integration.integrater;
import static calculator.mathmate.NewtonRapson.NRapson;
import static calculator.mathmate.Quadratic.quadratic;
import static calculator.mathmate.ThreeVarLinEq.threevareqn;

public class MainActivity extends AppCompatActivity {
    private EditText e1,e2;

    TextView display_mode;
    View view_bisection;
    Dialog dialog_eqn,dialog_xtr,dialog_bisection,dialog_bisectionV;
Window window;

    private int count=0;
    private String expression="";
    private String text="";
    private Double result=0.0;
    private String res="";
    private DBHelper dbHelper;
    String[] equn = new String[3];
    private Button mode,toggle,square,xpowy,log,sin,cos,tan,sqrt,fact,equal,openBracket,closeBracket,blank,btn_xx,btn_yy,btn_zz,btn_xpn,btn_trig;
    Button bt_dialog_bisection_num0,bt_dialog_bisection_num1,bt_dialog_bisection_num2,bt_dialog_bisection_num3,bt_dialog_bisection_num4,bt_dialog_bisection_num5,bt_dialog_bisection_num6,bt_dialog_bisection_num7,bt_dialog_bisection_num8,bt_dialog_bisection_num9,bt_dialog_bisection_numneg,bt_dialog_bisection_numC;
//    private int toggleMode=1;
    Button bisect_a,bisect_b,bt_bisect_done,invi_a,invi_b,invi_eq1,invi_eq2,invi_eq3;
    private int angleMode=1;
    TabHost tab,tab_sci,tab_calc,tab_trig;
    int calc=0;
    int eqcal=0,eqsign=0,conequal,bival;

    float val_a,val_b;
    Intent i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tab=(TabHost) findViewById(R.id.tabHost);
        tab.setup();

        tab_sci=(TabHost) findViewById(R.id.tabHost_sci);
        tab_sci.setup();

        tab_calc=(TabHost) findViewById(R.id.tabHost_calc);
        tab_calc.setup();


        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        display_mode = (TextView) findViewById(R.id.tV_modes);


        mode = (Button) findViewById(R.id.mode);
        toggle = (Button) findViewById(R.id.alpha);
        square = (Button) findViewById(R.id.square);
        xpowy = (Button) findViewById(R.id.factorial);
        log = (Button) findViewById(R.id.log);
        sin = (Button) findViewById(R.id.sin);
        cos = (Button) findViewById(R.id.cos);
        tan = (Button) findViewById(R.id.tan);
        sqrt= (Button) findViewById(R.id.sqrt);
        blank= (Button) findViewById(R.id.blank);
        fact = (Button) findViewById(R.id.factorial);
        equal=(Button) findViewById(R.id.equal);
        openBracket=(Button)findViewById(R.id.openBracket);
        closeBracket=(Button)findViewById(R.id.closeBracket);

        btn_xx=(Button)findViewById(R.id.btn_x2);
        btn_yy=(Button)findViewById(R.id.btn_y2);
        btn_zz=(Button)findViewById(R.id.btn_z2);
        btn_xpn=(Button)findViewById(R.id.xpown2);
//        btn_trig=(Button) findViewById(R.id.btn_trig);

        bisect_a= (Button) findViewById(R.id.bt_dialog_bisection_val_a);
        bisect_b= (Button) findViewById(R.id.bt_dialog_bisection_val_b);

        invi_a= (Button) findViewById(R.id.inviA);
        invi_b= (Button) findViewById(R.id.inviB);



        dbHelper=new DBHelper(this);


        //tags to change the mode from degree to radian and vice versa
        mode.setTag(1);
        //tags to change the names of the buttons performing different operations
        toggle.setTag(1);


        TabHost.TabSpec spec1=tab.newTabSpec("TAB 1");
        spec1.setIndicator("Main");
        spec1.setContent(R.id.tab1);
        tab.addTab(spec1);

        TabHost.TabSpec spec2=tab.newTabSpec("TAB 2");
        spec2.setIndicator("Scientific");
        spec2.setContent(R.id.tab2);
        tab.addTab(spec2);

        TabHost.TabSpec spec3=tab.newTabSpec("TAB 4");
        spec3.setIndicator("Equations");
        spec3.setContent(R.id.tab3);
        tab.addTab(spec3);

//        TabHost.TabSpec spec4=tab.newTabSpec("TAB 3");
//        spec4.setIndicator("Variables");
//        spec4.setContent(R.id.tab4);
//        tab.addTab(spec4);

        TabHost.TabSpec spec5=tab_sci.newTabSpec("TAB 1");
        spec5.setIndicator("Main");
        spec5.setContent(R.id.tab2_1);
        tab_sci.addTab(spec5);

        TabHost.TabSpec spec6=tab_sci.newTabSpec("TAB 1");
        spec6.setIndicator("Trig 1");
        spec6.setContent(R.id.tab2_2_1);
        tab_sci.addTab(spec6);

        TabHost.TabSpec spec10=tab_sci.newTabSpec("TAB 1");
        spec10.setIndicator("Trig 2");
        spec10.setContent(R.id.tab2_2_2);
        tab_sci.addTab(spec10);

        TabHost.TabSpec spec7=tab_calc.newTabSpec("TAB 1");
        spec7.setIndicator("Equation");
        spec7.setContent(R.id.tab3_1);
        tab_calc.addTab(spec7);


        TabHost.TabSpec spec8=tab_calc.newTabSpec("TAB 2");
        spec8.setIndicator("Numerical Method");
        spec8.setContent(R.id.tab3_2);
        tab_calc.addTab(spec8);

        TabHost.TabSpec spec9=tab_calc.newTabSpec("TAB 9");
        spec9.setIndicator("Calculus");
        spec9.setContent(R.id.tab3_3);
        tab_calc.addTab(spec9);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public void onClick(View v)
    {
//        toggleMode=(int)toggle.getTag();
        angleMode=((int)mode.getTag());
        switch (v.getId()) {

            case R.id.alpha:
                //change the button text if switch button is clicked
                if(eqsign==1){
                    conequal=1;


                }
//                else{
//                if(toggleMode==1)
//                {
//                    toggle.setTag(2);
//                    square.setText(R.string.cube);
//                    xpowy.setText(R.string.tenpow);
//                    log.setText(R.string.naturalLog);
//                    sin.setText(R.string.sininv);
//                    cos.setText(R.string.cosinv);
//                    tan.setText(R.string.taninv);
//                    sqrt.setText(R.string.cuberoot);
//                    fact.setText(R.string.Mod);
//                }
//                else if(toggleMode==2)
//                {
//                    toggle.setTag(3);
//                    square.setText(R.string.square);
//                    xpowy.setText(R.string.epown);
//                    log.setText(R.string.log);
//                    sin.setText(R.string.hyperbolicSine);
//                    cos.setText(R.string.hyperbolicCosine);
//                    tan.setText(R.string.hyperbolicTan);
//                    sqrt.setText(R.string.inverse);
//                    fact.setText(R.string.factorial);
//                }
//                else if(toggleMode==3)
//                {
//                    toggle.setTag(1);
//                    sin.setText(R.string.sin);
//                    cos.setText(R.string.cos);
//                    tan.setText(R.string.tan);
//                    sqrt.setText(R.string.sqrt);
//                    xpowy.setText(R.string.xpown);
//                }}
                break;

            case R.id.mode:

                //change the angle property for trignometric operations if mode button is clicked
                if(angleMode==1)
                {
                    mode.setTag(2);
                    mode.setText(R.string.mode2);
                }
                else
                {
                    mode.setTag(1);
                    mode.setText(R.string.mode1);
                }
                break;

            case R.id.num0:
                e2.setText(e2.getText() + "0");
                break;

            case R.id.num1:
                e2.setText(e2.getText() + "1");
                break;

            case R.id.num2:
                e2.setText(e2.getText() + "2");
                break;

            case R.id.num3:
                e2.setText(e2.getText() + "3");
                break;


            case R.id.num4:
                e2.setText(e2.getText() + "4");
                break;

            case R.id.num5:
                e2.setText(e2.getText() + "5");
                break;

            case R.id.num6:
                e2.setText(e2.getText() + "6");
                break;

            case R.id.num7:
                e2.setText(e2.getText() + "7");
                break;

            case R.id.num8:
                e2.setText(e2.getText() + "8");
                break;

            case R.id.num9:
                e2.setText(e2.getText() + "9");
                break;

            case R.id.btn_x:
                e2.setText(e2.getText() + "x");
                break;

            case R.id.btn_y:
                e2.setText(e2.getText() + "y");
                break;

            case R.id.btn_z:
                e2.setText(e2.getText() + "z");
                break;
            case R.id.btn_x2:
                e2.setText(e2.getText() + "x");
                break;

            case R.id.btn_y2:
                e2.setText(e2.getText() + "y");
                break;

            case R.id.btn_z2:
                e2.setText(e2.getText() + "z");
                break;

            case R.id.btn_dr:
                e2.setText("");
                expression = "";
                calc=1;
                blank.setVisibility(View.VISIBLE);
                blank.setText("SOLVE");
                e1.setText("Derivative of ");
                tab.setCurrentTab(0);
                btn_xx.setVisibility(View.VISIBLE);
                btn_yy.setVisibility(View.INVISIBLE);
                btn_zz.setVisibility(View.INVISIBLE);
                btn_xpn.setVisibility(View.VISIBLE);
//                btn_trig.setVisibility(View.VISIBLE);

                break;

            case R.id.btn_int:
                e2.setText("");
                expression = "";
                calc=7;
                blank.setVisibility(View.VISIBLE);
                blank.setText("SOLVE");
                e1.setText("Integration of ");
                tab.setCurrentTab(0);
                btn_xx.setVisibility(View.VISIBLE);
                btn_yy.setVisibility(View.INVISIBLE);
                btn_zz.setVisibility(View.INVISIBLE);
                btn_xpn.setVisibility(View.VISIBLE);
//                btn_trig.setVisibility(View.VISIBLE);

                break;



//            case R.id.btn_eqn:
//
//                dialog_eqn =new Dialog(MainActivity.this);
//                dialog_eqn.setTitle("Equations");
//                dialog_eqn.setContentView(R.layout.dialog_eqn);
//                dialog_eqn.show();

//                e2.setText("");
//                expression = "";
//                calc=767;
//                openBracket.setText("QUAD");
//                closeBracket.setText("3VAR");
//                break;


            case R.id.bt_dialog_quad:
//                btn_trig.setVisibility(View.INVISIBLE);
                e2.setText("");
                expression = "";
                toggle.setVisibility(View.VISIBLE);
                btn_xx.setVisibility(View.VISIBLE);
                btn_yy.setVisibility(View.INVISIBLE);
                btn_zz.setVisibility(View.INVISIBLE);
                btn_xpn.setVisibility(View.VISIBLE);
//                btn_trig.setVisibility(View.INVISIBLE);
                toggle.setText("ALPHA");
                eqsign = 1;
                calc=2;
                eqcal=1;
                e1.setText("Quadratic Equation"+"\n"+"Enter a quadratic equation and press solve");
                tab.setCurrentTab(0);

//                dialog_eqn.cancel();
                break;

            case R.id.bt_dialog_3var:
//                btn_trig.setVisibility(View.INVISIBLE);
                e2.setText("");
                expression = "";
                toggle.setVisibility(View.VISIBLE);
                btn_xx.setVisibility(View.VISIBLE);
                btn_yy.setVisibility(View.VISIBLE);
                btn_zz.setVisibility(View.VISIBLE);
                btn_xpn.setVisibility(View.VISIBLE);
//                btn_trig.setVisibility(View.INVISIBLE);
                toggle.setText("ALPHA");
                eqsign = 1;
                calc=3;
                eqcal=2;
                e1.setText("3 Variable Linear Equation"+"\n"+"To get started, press EQ1 to enter the first equation");
                blank.setVisibility(View.VISIBLE);
                blank.setText("EQ1");
                tab.setCurrentTab(0);

//                dialog_eqn.cancel();
                break;

            case R.id.btn_exp:
//                btn_trig.setVisibility(View.INVISIBLE);
                e2.setText("");
                expression = "";
                toggle.setVisibility(View.VISIBLE);
                btn_xx.setVisibility(View.VISIBLE);
                btn_yy.setVisibility(View.VISIBLE);
                btn_zz.setVisibility(View.VISIBLE);
                btn_xpn.setVisibility(View.VISIBLE);
//                btn_trig.setVisibility(View.INVISIBLE);
                toggle.setText("ALPHA");
                eqsign = 1;
                e1.setText("Insert an exponential function of form"+"\n"+"a^(1-bx)=c");
                calc=4;
                tab.setCurrentTab(0);

//                dialog_eqn.cancel();
                break;


//            case R.id.btn_other:
//                dialog_xtr =new Dialog(MainActivity.this);
//                dialog_xtr.setContentView(R.layout.dialog_xtr);
//                dialog_xtr.show();
//                break;

            case R.id.bt_dialog_bisection:
                e2.setText("");
                expression = "";
                toggle.setVisibility(View.VISIBLE);
                btn_xx.setVisibility(View.VISIBLE);
                btn_yy.setVisibility(View.VISIBLE);
                btn_zz.setVisibility(View.VISIBLE);
                btn_xpn.setVisibility(View.VISIBLE);
//                btn_trig.setVisibility(View.INVISIBLE);
                toggle.setText("ALPHA");
                eqsign = 1;
                eqcal=5;
                calc=5;
                e1.setText("Give the polynomial eqn a0x^n+a1x^n-1+...+an=0");
                calc=5;
                tab.setCurrentTab(0);
//                btn_trig.setVisibility(View.INVISIBLE);
//                dialog_xtr.cancel();
                break;

//               case R.id.bt_dialog_bisection_val_a:
//                view_bisection=(LayoutInflater.from(MainActivity.this)).inflate(R.layout.dialog_bisection_val,null);
//                    AlertDialog.Builder alert_bisection;
//                    final EditText eTB = (EditText) view_bisection.findViewById(R.id.ed_bisection);
//                    bt_dialog_bisection_num0=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_0));
//                    bt_dialog_bisection_num1=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_1));
//                    bt_dialog_bisection_num2=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_2));
//                    bt_dialog_bisection_num3=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_3));
//                    bt_dialog_bisection_num4=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_4));
//                    bt_dialog_bisection_num5=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_5));
//                    bt_dialog_bisection_num6=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_6));
//                    bt_dialog_bisection_num7=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_7));
//                    bt_dialog_bisection_num8=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_8));
//                    bt_dialog_bisection_num9=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_9));
//                    bt_dialog_bisection_numneg=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_N));
//                   bt_dialog_bisection_numC=(Button)view_bisection.findViewById(R.id.bt_dialog_bisection_val_C);
//                    bt_bisect_done=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_done));
//                    alert_bisection =new AlertDialog.Builder(MainActivity.this);
//                    alert_bisection.setView(view_bisection);
//                    bt_dialog_bisection_num0.setOnClickListener(new View.OnClickListener() {
//                        public void onClick(View v) {
//                            eTB.setText(eTB.getText()+"0");
//                            }
//                    });
//                   bt_dialog_bisection_num1.setOnClickListener(new View.OnClickListener() {
//                       public void onClick(View v) {
//                           eTB.setText(eTB.getText()+"1");
//                       }
//                   });
//                   bt_dialog_bisection_num2.setOnClickListener(new View.OnClickListener() {
//                       public void onClick(View v) {
//                           eTB.setText(eTB.getText()+"2");
//                       }
//                   });
//                   bt_dialog_bisection_num3.setOnClickListener(new View.OnClickListener() {
//                       public void onClick(View v) {
//                           eTB.setText(eTB.getText()+"3");
//                       }
//                   });
//                   bt_dialog_bisection_num4.setOnClickListener(new View.OnClickListener() {
//                       public void onClick(View v) {
//                           eTB.setText(eTB.getText()+"4");
//                       }
//                   });
//                   bt_dialog_bisection_num5.setOnClickListener(new View.OnClickListener() {
//                       public void onClick(View v) {
//                           eTB.setText(eTB.getText()+"5");
//                       }
//                   });
//                   bt_dialog_bisection_num6.setOnClickListener(new View.OnClickListener() {
//                       public void onClick(View v) {
//                           eTB.setText(eTB.getText()+"6");
//                       }
//                   });
//                   bt_dialog_bisection_num7.setOnClickListener(new View.OnClickListener() {
//                       public void onClick(View v) {
//                           eTB.setText(eTB.getText()+"7");
//                       }
//                   });
//                   bt_dialog_bisection_num8.setOnClickListener(new View.OnClickListener() {
//                       public void onClick(View v) {
//                           eTB.setText(eTB.getText()+"8");
//                       }
//                   });
//                   bt_dialog_bisection_num9.setOnClickListener(new View.OnClickListener() {
//                       public void onClick(View v) {
//                           eTB.setText(eTB.getText()+"9");
//                       }
//                   });
//                   bt_dialog_bisection_numneg.setOnClickListener(new View.OnClickListener() {
//                       public void onClick(View v) {
//                           if(eTB.length()==0)
//                           eTB.setText("-");
//                       }
//                   });
//                   bt_dialog_bisection_numC.setOnClickListener(new View.OnClickListener() {
//                       public void onClick(View v) {
//
//                       }
//                   });
//
//                   bt_bisect_done.setOnClickListener(new View.OnClickListener() {
//                       public void onClick(View v) {
//                            val_a=Integer.parseInt(eTB.getText().toString());
//
//                           dialog_bisectionV.cancel();
//                       }
//                   });
//
//                dialog_bisectionV=alert_bisection.create();
//                    dialog_bisectionV.show();
//
//                break;

//            case R.id.bt_dialog_bisection_val_b:
//                view_bisection=(LayoutInflater.from(MainActivity.this)).inflate(R.layout.dialog_bisection_val,null);
//                final EditText eTB2 = (EditText) view_bisection.findViewById(R.id.ed_bisection);
//                bt_dialog_bisection_num0=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_0));
//                bt_dialog_bisection_num1=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_1));
//                bt_dialog_bisection_num2=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_2));
//                bt_dialog_bisection_num3=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_3));
//                bt_dialog_bisection_num4=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_4));
//                bt_dialog_bisection_num5=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_5));
//                bt_dialog_bisection_num6=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_6));
//                bt_dialog_bisection_num7=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_7));
//                bt_dialog_bisection_num8=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_8));
//                bt_dialog_bisection_num9=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_9));
//                bt_dialog_bisection_numneg=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_N));
//                bt_dialog_bisection_numC=(Button)view_bisection.findViewById(R.id.bt_dialog_bisection_val_C);
//                bt_bisect_done=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_val_done));
//                alert_bisection =new AlertDialog.Builder(MainActivity.this);
//                alert_bisection.setView(view_bisection);
//                bt_dialog_bisection_num0.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        eTB2.setText(eTB2.getText()+"0");
//                    }
//                });
//                bt_dialog_bisection_num1.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        eTB2.setText(eTB2.getText()+"1");
//                    }
//                });
//                bt_dialog_bisection_num2.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        eTB2.setText(eTB2.getText()+"2");
//                    }
//                });
//                bt_dialog_bisection_num3.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        eTB2.setText(eTB2.getText()+"3");
//                    }
//                });
//                bt_dialog_bisection_num4.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        eTB2.setText(eTB2.getText()+"4");
//                    }
//                });
//                bt_dialog_bisection_num5.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        eTB2.setText(eTB2.getText()+"5");
//                    }
//                });
//                bt_dialog_bisection_num6.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        eTB2.setText(eTB2.getText()+"6");
//                    }
//                });
//                bt_dialog_bisection_num7.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        eTB2.setText(eTB2.getText()+"7");
//                    }
//                });
//                bt_dialog_bisection_num8.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        eTB2.setText(eTB2.getText()+"8");
//                    }
//                });
//                bt_dialog_bisection_num9.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        eTB2.setText(eTB2.getText()+"9");
//                    }
//                });
//                bt_dialog_bisection_numneg.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        if(eTB2.length()==0)
//                            eTB2.setText("-");
//                    }
//                });
//                bt_dialog_bisection_numC.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//
//                    }
//                });
//
//                bt_bisect_done.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        val_b=Integer.parseInt(eTB2.getText().toString());
//                        dialog_bisectionV.cancel();
//                    }
//                });
//
//                dialog_bisectionV=alert_bisection.create();
//                dialog_bisectionV.show();
//
//                break;
//
//            case R.id.btn_trig:
//                tab.setCurrentTab(1);
//
//                break;

            case R.id.inviA:
                if(e2.length()!=0){
                String invAS=e2.getText().toString();
                val_a=Float.valueOf(invAS);
                invi_a.setText(invAS);
                e2.setText("");
                    blank.setVisibility(View.VISIBLE);
                blank.setText("SOLVE");}
                break;

            case R.id.inviB:
                if(e2.length()!=0){
                String invBS=e2.getText().toString();
                val_b=Float.valueOf(invBS);
                invi_b.setText(invBS);
                    blank.setVisibility(View.VISIBLE);
                blank.setText("SOLVE");
                e2.setText("");}
                break;

//            case R.id.bt_dialog_bisection_done:
//                res=bisectson(expression,val_a,val_b);
//                if(res=="X"){
//                                Toast.makeText(getApplicationContext(), "The Values are not cool", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(getApplicationContext(), "TEnter other values", Toast.LENGTH_SHORT).show();
//                            }
//
//                else{
//
//                            blank.setText("SOLVE");
//                dialog_bisection.cancel();
//
//                }
//                break;

            case R.id.bt_dialog_rapson:
                e2.setText("");
                expression = "";
                toggle.setVisibility(View.VISIBLE);
                btn_xx.setVisibility(View.VISIBLE);
                btn_yy.setVisibility(View.VISIBLE);
                btn_zz.setVisibility(View.VISIBLE);
                btn_xpn.setVisibility(View.VISIBLE);
                toggle.setText("ALPHA");
                eqsign = 1;
                eqcal=6;
                calc=6;
                e1.setText("Give the polynomial eqn a0x^n+a1x^n-1+...+an=0");
                tab.setCurrentTab(0);
//                dialog_xtr.cancel();
                break;

            case R.id.epown:
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    e2.setText("e^(" + text + ")");

                }
                break;

            case R.id.dot:
                if (count == 0 && e2.length() != 0) {
                    e2.setText(e2.getText() + ".");
                    count++;
                }
                break;

            case R.id.clear:
                e1.setText("");
                e2.setText("");
                display_mode.setText("");
                toggle.setText("");
                toggle.setVisibility(View.INVISIBLE);
                blank.setText("");
                blank.setVisibility(View.INVISIBLE);
                btn_xx.setVisibility(View.INVISIBLE);
                btn_yy.setVisibility(View.INVISIBLE);
                btn_zz.setVisibility(View.INVISIBLE);
                btn_xpn.setVisibility(View.INVISIBLE);
//                btn_trig.setVisibility(View.INVISIBLE);
                openBracket.setText("(");
                closeBracket.setText(")");
                equal.setText("=");
                expression = text = "";
                count=eqcal=calc=0;
                break;

            case R.id.backspace:
                text=e2.getText().toString();
                if(text.length()>0)
                {
                    if(text.endsWith("."))
                    {
                        count=0;
                    }
                    String newText=text.substring(0,text.length()-1);
                    //to delete the data contained in the brackets at once
                    if(text.endsWith(")"))
                    {
                        char []a=text.toCharArray();
                        int pos=a.length-2;
                        int counter=1;
                        //to find the opening bracket position
                        for(int i=a.length-2;i>=0;i--)
                        {
                            if(a[i]==')')
                            {
                                counter++;
                            }
                            else if(a[i]=='(')
                            {
                                counter--;
                            }
                            //if decimal is deleted b/w brackets then count should be zero
                            else if(a[i]=='.')
                            {
                                count=0;
                            }
                            //if opening bracket pair for the last bracket is found
                            if(counter==0)
                            {
                                pos=i;
                                break;
                            }
                        }
                        newText=text.substring(0,pos);
                    }
                    //if e2 edit text contains only - sign or sqrt or any other text functions
                    // at last then clear the edit text e2
                    if(newText.equals("-")||newText.endsWith("sqrt")||newText.endsWith("log")||newText.endsWith("ln")
                            ||newText.endsWith("sin")||newText.endsWith("asin")||newText.endsWith("asind")||newText.endsWith("sinh")
                            ||newText.endsWith("cos")||newText.endsWith("acos")||newText.endsWith("acosd")||newText.endsWith("cosh")
                            ||newText.endsWith("tan")||newText.endsWith("atan")||newText.endsWith("atand")||newText.endsWith("tanh")
                            ||newText.endsWith("cbrt"))
                    {
                        newText="";
                    }
                    //if pow sign is left at the last or divide sign
                    else if(newText.endsWith("^")||newText.endsWith("/"))
                        newText=newText.substring(0,newText.length()-1);
                    else if(newText.endsWith("pi")||newText.endsWith("e^"))
                        newText=newText.substring(0,newText.length()-2);
                    e2.setText(newText);
                }
                break;

            case R.id.plus:
                if(calc==0){
                    operationClicked("+");}
                else{
                    e2.setText(e2.getText() + "+");
                }
                break;

            case R.id.minus:
                if(calc==0){
                    operationClicked("-");}
                else{
                    e2.setText(e2.getText() + "-");
                }
                break;

            case R.id.divide:
                operationClicked("/");
                break;

            case R.id.multiply:
                operationClicked("*");
                break;

            case R.id.sqrt:
                if (e2.length() != 0) {
                    text = e2.getText().toString();

                        e2.setText("sqrt(" + text + ")");

                }
                break;

            case R.id.cbrt:
                if (e2.length() != 0&&calc==0) {
                    text = e2.getText().toString();
                e2.setText("cbrt(" + text + ")");}
                else
                    Toast.makeText(getApplicationContext(), "OPTIONS UNAVAILABLE", Toast.LENGTH_SHORT).show();

                break;


            case R.id.inverse:
                if(e2.length()!=0&&calc==0){
                text = e2.getText().toString();
                e2.setText("1/(" + text + ")");}
                else
                    Toast.makeText(getApplicationContext(), "OPTIONS UNAVAILABLE", Toast.LENGTH_SHORT).show();
                break;



            case R.id.square:
                if (e2.length() != 0&&calc==0) {
                    text = e2.getText().toString();
                        e2.setText("(" + text + ")^2");
                }
                else
                    e2.setText(e2.getText()+"^2");
                break;

//            case R.id.xpowy:
//                if (e2.length() != 0) {
//                    text = e2.getText().toString();
//                    if(toggleMode==1){
//                        if(calc==0){
//                            e2.setText("(" + text + ")^");}
//                        else{
//                            e2.setText(e2.getText()+"^");
//                        }}
//                    else if(toggleMode==2)
//                        e2.setText("10^(" + text + ")");
//                    else
//                        e2.setText("e^(" + text + ")");
//                }
//                break;

            case R.id.xpown:
                text = e2.getText().toString();
                  if (e2.length() != 0&&calc==0) {
                    e2.setText("(" + text + ")^");}

                else
                    e2.setText(e2.getText()+"^");
                break;

            case R.id.xpown2:
                text = e2.getText().toString();
                if (e2.length() != 0&&calc==0) {
                    e2.setText("(" + text + ")^");}

                else
                    e2.setText(e2.getText()+"^");
                break;



            case R.id.log:
        text = e2.getText().toString();
                if (e2.length() != 0&&calc==0) {
                    e2.setText("log(" + text + ")");
                }
                else
                    e2.setText(e2.getText()+"log");
                break;

            case R.id.ln:
                if(e2.length()!=0&&calc==0){
                text = e2.getText().toString();
        e2.setText("ln(" + text + ")");}
                else
                Toast.makeText(getApplicationContext(), "OPTIONS UNAVAILABLE", Toast.LENGTH_SHORT).show();
            break;


            case R.id.factorial:
                if (e2.length() != 0&&calc==0) {
                    text = e2.getText().toString();

                        String res="";
                        try
                        {
                            CalculateFactorial cf=new CalculateFactorial();
                            int []arr=cf.factorial((int)Double.parseDouble(String.valueOf(new ExtendedDoubleEvaluator().evaluate(text))));
                            int res_size=cf.getRes();
                            if(res_size>20)
                            {
                                for (int i=res_size-1; i>=res_size-20; i--)
                                {
                                    if(i==res_size-2)
                                        res+=".";
                                    res+=arr[i];
                                }
                                res+="E"+(res_size-1);
                            }
                            else
                            {
                                for (int i=res_size-1; i>=0; i--)
                                {
                                    res+=arr[i];
                                }
                            }
                            e2.setText(res);
                        }
                        catch (Exception e)
                        {
                            if(e.toString().contains("ArrayIndexOutOfBoundsException"))
                            {
                                e2.setText("Result too big!");
                            }
                            else
                                e2.setText("Invalid!!");
                            e.printStackTrace();
                        }

                }
                else
                    Toast.makeText(getApplicationContext(), "OPTIONS UNAVAILABLE", Toast.LENGTH_SHORT).show();
                break;

            case R.id.Mod:
                if (e2.length() != 0&&calc==0) {
                    text = e2.getText().toString();
                    e1.setText("(" + text + ")%");
                    e2.setText("");
                }
                else
                    Toast.makeText(getApplicationContext(), "OPTIONS UNAVAILABLE", Toast.LENGTH_SHORT).show();

            case R.id.sin:
                if(calc==1||calc==7){
                    e2.setText(e2.getText()+"sin");
                }
                else{
                    if (e2.length() != 0) {
                        text = e2.getText().toString();
                        if(angleMode==1)
                        {
                            double angle=Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));
                                e2.setText("sin(" + angle + ")");

                        }
                        else
                        {

                                e2.setText("sin(" + text + ")");

                        }
                    }}
                break;

            case R.id.sininv:
                if(calc==1||calc==7){
                    e2.setText(e2.getText()+"asin");
                }
                else{
                    if (e2.length() != 0&&calc==0) {
                        text = e2.getText().toString();
                        if(angleMode==1)
                        {
                            double angle=Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));

                                e2.setText("asind(" + angle + ")");

                        }
                        else
                        {

                                e2.setText("asin(" + text + ")");

                        }
                    }}

                break;

            case R.id.hypsin:
                if(calc==1||calc==7){
                    e2.setText(e2.getText()+"sinh");
                }
                else{
                    if (e2.length() != 0&&calc==0) {
                        text = e2.getText().toString();
                        if(angleMode==1)
                        {
                            double angle=Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));

                                e2.setText("sinh(" + text + ")");
                        }
                        else
                        {

                                e2.setText("sinh(" + text + ")");
                        }
                    }}
                break;

            case R.id.cos:
                if(calc==1||calc==7){
                    e2.setText(e2.getText()+"cos");
                }
                if (e2.length() != 0&&calc==0) {
                    text = e2.getText().toString();
                    if(angleMode==1)
                    {
                        double angle=Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));

                            e2.setText("cos(" + angle + ")");

                    }
                    else
                    {
                            e2.setText("cos(" + text + ")");

                    }
                }
                break;

            case R.id.cosinv:
                if(calc==1||calc==7){
                    e2.setText(e2.getText()+"acos");
                }

                if (e2.length() != 0&&calc==0) {
                    text = e2.getText().toString();
                    if(angleMode==1)
                    {

                            e2.setText("acosd(" + text + ")");

                    }
                    else
                    {

                            e2.setText("acos(" + text + ")");

                    }
                }
                break;

            case R.id.hypcos:
                if(calc==1||calc==7){
                    e2.setText(e2.getText()+"cosh");
                }
                if (e2.length() != 0&&calc==0) {
                    text = e2.getText().toString();
                    if(angleMode==1)
                    {
                        double angle=Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));

                            e2.setText("cosh(" + text + ")");
                    }
                    else
                    {

                            e2.setText("cosh(" + text + ")");
                    }
                }
                break;

            case R.id.tan:
                if(calc==1||calc==7){
                    e2.setText(e2.getText()+"tan");
                }
                if (e2.length() != 0&&calc==0) {
                    text = e2.getText().toString();
                    if(angleMode==1)
                    {
                        double angle=Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));

                            e2.setText("tan(" + angle + ")");

                    }
                    else
                    {

                            e2.setText("tan(" + text + ")");

                    }
                }
                break;

            case R.id.taninv:
                if(calc==1||calc==7){
                    e2.setText(e2.getText()+"atan");
                }
                if (e2.length() != 0&&calc==0) {
                    text = e2.getText().toString();
                    if(angleMode==1)
                    {
                        double angle=Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));

                            e2.setText("tanh(" + angle + ")");
                    }
                    else
                    {

                            e2.setText("tanh(" + text + ")");
                    }
                }
                break;

            case R.id.hyptan:
                if(calc==1||calc==7){
                    e2.setText(e2.getText()+"tanh");
                }
                if (e2.length() != 0&&calc==0) {
                    text = e2.getText().toString();
                    if(angleMode==1)
                    {
                        double angle=Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));

                            e2.setText("tanh(" + angle + ")");
                    }
                    else
                    {

                            e2.setText("tanh(" + text + ")");
                    }
                }
                break;


            case R.id.cosec:
                if (calc==1||calc==7) {
                    e2.setText(e2.getText()+"cot");
                }
                break;

            case R.id.cosecinv:
                if (calc==1||calc==7) {
                    e2.setText(e2.getText()+"acosec");
                }
                break;

            case R.id.hypcosec:
                if (calc==1||calc==7) {
                    e2.setText(e2.getText()+"cosech");
                }
                break;



            case R.id.sec:
                if (calc==1||calc==7) {
                    e2.setText(e2.getText()+"sec");
                }
                break;

            case R.id.secinv:
                if (calc==1||calc==7) {
                    e2.setText(e2.getText()+"asec");
                }
                break;

            case R.id.hypsec:
                if (calc==1||calc==7) {
                    e2.setText(e2.getText()+"sech");
                }
                break;

            case R.id.cot:
                if (calc==1||calc==7) {
                    e2.setText(e2.getText()+"cot");
                }
                break;


            case R.id.cotinv:
                if (calc==1||calc==7) {
                    e2.setText(e2.getText()+"acot");
                }
                break;

            case R.id.hypcot:
                if (calc==1||calc==7) {
                    e2.setText(e2.getText()+"coth");
                }
                break;




            case R.id.posneg:
                if (e2.length() != 0) {
                    String s = e2.getText().toString();
                    char arr[] = s.toCharArray();
                    if (arr[0] == '-')
                        e2.setText(s.substring(1, s.length()));
                    else
                        e2.setText("-" + s);
                }
                break;

            case R.id.blank:


                if(calc==1 && e2.length() != 0){
                    expression = e2.getText().toString();

                    if (expression.length() == 0)
                        expression = "0.0";

                    res = derivater(expression);
                    e1.setText("Derivative of"+"\n"+expression+" is");
                    e2.setText(res + "");;
                    if (!res.equals("0.0")){
                        dbHelper.insert("SCIENTIFIC", res);}



                    calc=0;
                    text="";
                    expression="";
                    blank.setText("");
                    blank.setVisibility(View.INVISIBLE);
//                    btn_trig.setVisibility(View.INVISIBLE);
                }

                else if(calc==7 && e2.length() != 0){
                    expression = e2.getText().toString();

                    if (expression.length() == 0)
                        expression = "0.0";

                    res = integrater(expression);
                    e1.setText("Integration of"+"\n"+expression+" is");
                    e2.setText(res + "");;
                    if (!res.equals("0.0")){
                        dbHelper.insert("SCIENTIFIC", res);}



                    calc=0;
                    text="";
                    expression="";
                    blank.setText("");
                    blank.setVisibility(View.INVISIBLE);
//                    btn_trig.setVisibility(View.INVISIBLE);
                }


                else if(eqcal==1&&calc==2&&e2.length()!=0&&conequal==0){
                    expression = e2.getText().toString();
                    e2.setText("");

                    if (expression.length() == 0)
                        expression = "0.0";

                    res = quadratic(expression);



                    if (!res.equals("0.0")){
                        dbHelper.insert("Quadratic", res);
                    }

                    i = new Intent(this, eHistory.class);
                    i.putExtra("calcName", "Quadratic");
                    startActivity(i);

                    e1.setText("");e2.setText("");toggle.setText("");toggle.setVisibility(View.INVISIBLE); btn_xx.setVisibility(View.INVISIBLE);
                    btn_yy.setVisibility(View.INVISIBLE);
                    btn_zz.setVisibility(View.INVISIBLE);
                    btn_xpn.setVisibility(View.INVISIBLE);
                    calc=eqcal=eqsign=0;
                    text="";
                    expression="";
                    blank.setText("");
                    blank.setVisibility(View.INVISIBLE);
                }

                else if(calc==3 && eqcal==2 && e2.length()==0 ){
                    e1.setText("3 Variable Linear Equation"+"\n"+"Enter Equation 1");
                    blank.setVisibility(View.INVISIBLE);
                    blank.setText("");

                    eqcal=211;
                }

                else if(calc==3 && eqcal==9 && e2.length()!=0){
                    equn[0]=e2.getText().toString();
                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"Press EQ2 to enter second equation");
                    e2.setText("");
                    blank.setVisibility(View.VISIBLE);
                    blank.setText("EQ2");
                    eqcal=212;
                }

                else if(calc==3 && eqcal==212 && e2.length()==0){
                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"Enter Equation 2");
                    blank.setVisibility(View.INVISIBLE);
                    blank.setText("");
                    eqcal=221;
                }


                else if(calc==3 && eqcal==99 && e2.length()!=0){
                    equn[1]=e2.getText().toString();
                    e2.setText("");
                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"EQ2:"+equn[1]+"\n"+"Press EQ3 to enter the third equation");

                    blank.setVisibility(View.VISIBLE);
                    blank.setText("EQ3");
                    eqcal=231;
                }

                else if(calc==3 && eqcal==231 && e2.length()==0){
                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"EQ2:"+equn[1]+"\n"+"Enter Equation 3");

                    blank.setVisibility(View.INVISIBLE);
                    blank.setText("");
                    eqcal=232;
                }

                else if(calc==3 && eqcal==999 && e2.length()!=0){
                    equn[2]=e2.getText().toString();
                    e2.setText("");
                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"EQ2:"+equn[1]+"\n"+"EQ3:"+equn[2]+"\n"+"Press SOL to get the solution");
                    blank.setVisibility(View.VISIBLE);blank.setText("SOL");
                    eqcal=333;
                }
                else if(calc==3 && eqcal==333 && e2.length()==0){

                    res=threevareqn(equn[0],equn[1],equn[2]);

                    if (!res.equals("0.0")){
                        dbHelper.insert("3 Variable", res);
                    }

                    i = new Intent(this, eHistory.class);
                    i.putExtra("calcName", "3 Variable");
                    startActivity(i);


                    e1.setText("");e2.setText("");
                    calc=eqcal=eqsign=0;
                    text="";
                    expression="";
                    blank.setVisibility(View.INVISIBLE);
                }

                else if(calc==41 && e2.length()!=0 && eqcal==0){

                    expression=e2.getText().toString();

                    res=expo(expression);
                    if (!res.equals("0.0")){
                        dbHelper.insert("Exponential", res);
                    }

                    i = new Intent(this, eHistory.class);
                    i.putExtra("calcName", "Exponential");
                    startActivity(i);
                    e1.setText("");e2.setText("");
                    calc=eqcal=eqsign=0;
                    text="";
                    expression="";
                    blank.setVisibility(View.INVISIBLE);

                }

                else if(calc==5 && e2.length() != 0){
                    expression = e2.getText().toString();
                    e2.setText("");
                    btn_xx.setVisibility(View.INVISIBLE);
                    btn_yy.setVisibility(View.INVISIBLE);
                    btn_zz.setVisibility(View.INVISIBLE);
                    btn_xpn.setVisibility(View.INVISIBLE);
                    invi_a.setVisibility(View.VISIBLE);
                    invi_b.setVisibility(View.VISIBLE);
//                    dialog_bisection =new Dialog(MainActivity.this);
//                    window = dialog_bisection.getWindow();
//                    window.setGravity(Gravity.TOP);
//                    dialog_bisection.setContentView(R.layout.dialog_bisection);
//                    dialog_bisection.show();
                    eqcal=555;
                    blank.setVisibility(View.VISIBLE);
                    blank.setText("SOLVE");

                }

                else if(calc==5 && e2.length()== 0 && eqcal==555) {

                    res=bisectson(expression,val_a,val_b);
                    if(res=="X"){
                        Toast.makeText(getApplicationContext(), "The Values are not cool", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Enter other values", Toast.LENGTH_SHORT).show();
                    }

                    else{

                        invi_a.setVisibility(View.INVISIBLE);
                        invi_b.setVisibility(View.INVISIBLE);
                        if (!res.equals("0.0")) {
                            dbHelper.insert("Bisection", res);
                        }
                        i = new Intent(this, eHistory.class);
                        i.putExtra("calcName", "Bisection");
                        startActivity(i);
                        e1.setText("");
                        e2.setText("");
                        calc = eqcal = 0;
                        text = "";
                        expression = "";
                        equal.setText("=");
                        blank.setVisibility(View.INVISIBLE);
                    }
                }

                else if(calc==6&& e2.length() != 0&&eqcal==0){
                    expression = e2.getText().toString();
                    e2.setText("");
                    e1.setText(expression+"\nEnter an initial guess value");
                    eqcal=66;
                    blank.setVisibility(View.VISIBLE);
                    blank.setText("Done");

                }

                else if(calc==6 && e2.length()!= 0 && eqcal==66) {
                    val_a=Float.valueOf(String.valueOf(e2.getText()));
                    e1.setText(expression+"\n"+"guess value"+val_a+"Now press solve");
                    eqcal=666;
                    blank.setVisibility(View.VISIBLE);
                    blank.setText("SOLVE");
                    e2.setText("");
                }



                else if(calc==6 && e2.length()== 0 && eqcal==666)  {

                    res=NRapson(expression,val_a);



                        if (!res.equals("0.0")) {
                            dbHelper.insert("NewtonRhapson", res);
                        }
                        i = new Intent(this, eHistory.class);
                        i.putExtra("calcName", "NewtonRhapson");
                        startActivity(i);
                        e1.setText("");
                        e2.setText("");
                        calc = eqcal = 0;
                        text = "";
                        expression = "";
                        equal.setText("=");
                    blank.setVisibility(View.INVISIBLE);
                        blank.setText("");

                }

                break;



            case R.id.equal:

//Quad
//                if(calc==2 && e2.length()!=0 && eqcal==1){
//                    e2.setText(e2.getText()+"=");
//                    eqcal=0;
//                    equal.setText("SOLVE");
//                }
//
//                else if(calc==2 && e2.length() != 0){
//
//                    expression = e2.getText().toString();
//                    e2.setText("");
//
//                    if (expression.length() == 0)
//                        expression = "0.0";
//
//                    res = quadratic(expression);
//
//
//
//                    if (!res.equals("0.0")){
//                        dbHelper.insert("eqn", res);
//                    }
//
//                    i = new Intent(this, eHistory.class);
//                    i.putExtra("calcName", "eqn");
//                    startActivity(i);
//
//
//                    e1.setText("");e2.setText("");
//                    calc=eqcal=0;
//                    text="";
//                    expression="";
//                    equal.setText("=");
//
//                }
//                else if(calc==3 && eqcal==2 && e2.length()==0 ){
//                    e1.setText("3 Variable Linear Equation"+"\n"+"Enter Equation 1");
//                    equal.setText("=");
//                    eqcal=211;
//                }
//
//                else if(calc==3 && eqcal==211 && e2.length()!=0){
//                    e2.setText(e2.getText()+"=");
//                    eqcal=9;
//                    equal.setText("done");
//                    e1.setText("3 Variable Linear Equation"+"\n"+"Now press DONE");
//                }
//
//                else if(calc==3 && eqcal==9 && e2.length()!=0){
//                    equn[0]=e2.getText().toString();
//                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"Press EQ2 to enter second equation");
//
//                    e2.setText("");
//                    equal.setText("EQ2");
//                    eqcal=212;
//                }
//                else if(calc==3 && eqcal==212 && e2.length()==0){
//                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"Enter Equation 2");
//                    equal.setText("=");
//                    eqcal=221;
//                }
//
//                else if(calc==3 && eqcal==221 && e2.length()!=0){
//                    e2.setText(e2.getText()+"=");
//                    eqcal=99;
//                    equal.setText("done");
//                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"Now press DONE");
//                }
//                else if(calc==3 && eqcal==99 && e2.length()!=0){
//                    equn[1]=e2.getText().toString();
//                    e2.setText("");
//                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"EQ2:"+equn[1]+"\n"+"Press EQ3 to enter the third equation");
//                    equal.setText("EQ3");
//                    eqcal=231;
//                }
//                else if(calc==3 && eqcal==231 && e2.length()==0){
//                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"EQ2:"+equn[1]+"\n"+"Enter Equation 3");
//
//                    equal.setText("=");
//                    eqcal=232;
//                }
//                else if(calc==3 && eqcal==232 && e2.length()!=0){
//                    e2.setText(e2.getText()+"=");
//                    eqcal=999;
//                    equal.setText("done");
//                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"EQ2:"+equn[1]+"\n"+"Now press DONE");
//                }
//                else if(calc==3 && eqcal==999 && e2.length()!=0){
//                    equn[2]=e2.getText().toString();
//                    e2.setText("");
//                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"EQ2:"+equn[1]+"\n"+"EQ2:"+equn[2]+"\n"+"Press SOL to get the solution");
//                    equal.setText("SOL");
//                    eqcal=333;
//                }
//                else if(calc==3 && eqcal==333 && e2.length()==0){
//
//                    res=threevareqn(equn[0],equn[1],equn[2]);
//
//                    if (!res.equals("0.0")){
//                        dbHelper.insert("threeeqn", res);
//                    }
//
//                    i = new Intent(this, eHistory.class);
//                    i.putExtra("calcName", "threeeqn");
//                    startActivity(i);
//                    e1.setText("");e2.setText("");
//                    calc=eqcal=0;
//                    text="";
//                    expression="";
//                    equal.setText("=");
//                }


//Exponential
//
//
//                else if(calc==4 && e2.length()!=0 && eqcal==0){
//                    e2.setText(e2.getText()+"=");
//                    equal.setText("SOLVE");
//                    calc=41;
//                }
//
//
//                else if(calc==41 && e2.length()!=0 && eqcal==0){
//
//                    expression=e2.getText().toString();
//
//                    res=expo(expression);
//                    if (!res.equals("0.0")){
//                        dbHelper.insert("expofunc", res);
//                    }
//
//                    i = new Intent(this, eHistory.class);
//                    i.putExtra("calcName", "expofunc");
//                    startActivity(i);
//
//                    calc=0;
//
//                }

//Bisection
//                else if(calc==5 && e2.length()!=0 && eqcal==5){
//                    e2.setText(e2.getText()+"=");
//                    eqcal=0;
//                    equal.setText("Value");
//                }
//
//
//
//                else if(calc==5 && e2.length() != 0){
//                    expression = e2.getText().toString();
//                    e2.setText("");
//
//                    dialog_bisection =new Dialog(MainActivity.this);
//                    dialog_bisection.setContentView(R.layout.dialog_bisection);
//                    dialog_bisection.show();
//                    eqcal=555;
//
//                    View view_bisection=(LayoutInflater.from(MainActivity.this)).inflate(R.layout.dialog_bisection,null);
//                    AlertDialog.Builder alert_bisection;
//                    final EditText eTBa = (EditText) view_bisection.findViewById(R.id.eT_dialog_bisection_a);
//                    final EditText eTBb = (EditText) view_bisection.findViewById(R.id.eT_dialog_bisection_b);
//                    bt_dialog_bisection_num0=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_num0));
//                    bt_dialog_bisection_num1=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_num1));
//                    bt_dialog_bisection_num2=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_num2));
//                    bt_dialog_bisection_num3=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_num3));
//                    bt_dialog_bisection_num4=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_num4));
//                    bt_dialog_bisection_num5=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_num5));
//                    bt_dialog_bisection_num6=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_num6));
//                    bt_dialog_bisection_num7=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_num7));
//                    bt_dialog_bisection_num8=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_num8));
//                    bt_dialog_bisection_num9=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_num9));
//                    bt_dialog_bisection_numneg=(Button)view_bisection.findViewById((R.id.bt_dialog_bisection_numneg));
//                    alert_bisection =new AlertDialog.Builder(MainActivity.this);
//                    alert_bisection.setView(view_bisection);
//                    bt_dialog_bisection_num0.setOnClickListener(new View.OnClickListener() {
//                        public void onClick(View v) {
//                            val_a=Integer.parseInt(eTBa.getText().toString());
//                            val_b=Integer.parseInt(eTBb.getText().toString());
//                            res=bisectson(expression,val_a,val_b);
//                            if(res=="X"){
//                                Toast.makeText(getApplicationContext(), "The Values are not cool", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(getApplicationContext(), "TEnter other values", Toast.LENGTH_SHORT).show();
//                            }
//                            else{
//                            }
//                            equal.setText("SOLVE");
//                        }
//                    });
//                    dialog_bisection=alert_bisection.create();
//                    dialog_bisection.show();
//                }
//                else if(calc==5 && e2.length()== 0 && eqcal==555) {
//
//                    if (!res.equals("0.0")) {
//                        dbHelper.insert("bisection", res);
//                    }
//
//                    i = new Intent(this, eHistory.class);
//                    i.putExtra("calcName", "bisection");
//                    startActivity(i);
//
//
//                    e1.setText("");
//                    e2.setText("");
//                    calc = eqcal = 0;
//                    text = "";
//                    expression = "";
//                    equal.setText("=");
//
//                }

                display_mode.setText("");

                if(conequal==1&& e2.length()!=0&&calc==2&&eqcal==1){
                    e2.setText(e2.getText()+"=");
                    conequal=0;
                    blank.setVisibility(View.VISIBLE);
                    blank.setText("Solve");


                }


                else if(conequal==1&&calc==3 && eqcal==211 && e2.length()!=0){
                    e2.setText(e2.getText()+"=");
                    eqcal=9;
                    blank.setVisibility(View.VISIBLE);
                    blank.setText("done");
                    e1.setText("3 Variable Linear Equation"+"\n"+"Now press DONE");

                }

                else if(conequal==1&&calc==3 && eqcal==221 && e2.length()!=0){
                    e2.setText(e2.getText()+"=");
                    eqcal=99;
                    blank.setVisibility(View.VISIBLE);
                    blank.setText("done");
                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"Now press DONE");

                }

                else if(conequal==1&&calc==3 && eqcal==232 && e2.length()!=0){
                    e2.setText(e2.getText()+"=");
                    eqcal=999;
                    blank.setVisibility(View.VISIBLE);
                    blank.setText("done");
                    e1.setText("3 Variable Linear Equation\tEQ1:"+equn[0]+"\n"+"EQ2:"+equn[1]+"\n"+"Now press DONE");

                }

                else if(conequal==1&&calc==4 && e2.length()!=0 && eqcal==0){
                    e2.setText(e2.getText()+"=");
                    blank.setVisibility(View.VISIBLE);
                    blank.setText("SOLVE");
                    calc=41;
                }

                else if(conequal==1&&calc==5 && e2.length()!=0 && eqcal==5){
                    e2.setText(e2.getText()+"=");
                    eqcal=0;
                    blank.setVisibility(View.VISIBLE);
                    blank.setText("Value");
                }

                else if(conequal==1&&calc==6 && e2.length()!=0 && eqcal==6){
                    e2.setText(e2.getText()+"=");
                    eqcal=0;
                    blank.setVisibility(View.VISIBLE);
                    blank.setText("Value");
                }





                else{
                    if (e2.length() != 0) {
                        text = e2.getText().toString();
                        expression = e1.getText().toString() + text;
                    }
                    e1.setText("");
                    if (expression.length() == 0)
                        expression = "0.0";
                    try {
                        //evaluate the expression
                        result = new ExtendedDoubleEvaluator().evaluate(expression);
                        //insert expression and result in sqlite database if expression is valid and not 0.0
                        if (String.valueOf(result).equals("6.123233995736766E-17"))
                        {
                            result=0.0;
                            e2.setText(result + "");
                        }
                        else if(String.valueOf(result).equals("1.633123935319537E16"))
                            e2.setText("infinity");
                        else
                            e2.setText(result + "");
                        if (!expression.equals("0.0"))
                            dbHelper.insert("SCIENTIFIC", expression + " = " + result);
                    } catch (Exception e) {
                        e2.setText("Invalid Expression");
                        e1.setText("");
                        expression = "";
                        e.printStackTrace();
                    }}

                /*for more knowledge on DoubleEvaluator and its tutorial go to the below link
                http://javaluator.sourceforge.net/en/home/*/


                break;

            case R.id.openBracket:
                e1.setText(e1.getText() + "(");
                break;

            case R.id.closeBracket:
                if(e2.length()!=0)
                    e1.setText(e1.getText() +e2.getText().toString()+ ")");
                else
                    e1.setText(e1.getText() + ")");
                break;


        }
    }



    private void operationClicked(String op) {
        if (e2.length() != 0) {
            String text = e2.getText().toString();
            e1.setText(e1.getText() + text + op);
            e2.setText("");
            count = 0;
        } else {
            String text = e1.getText().toString();
            if (text.length() > 0) {
                String newText = text.substring(0, text.length() - 1) + op;
                e1.setText(newText);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

      if (id == R.id.ab_history) {
            i = new Intent(this, History.class);
            i.putExtra("calcName", "SCIENTIFIC");
            startActivity(i);
            return true;
        }


        else if (id == R.id.ab_uc) {
            i=new Intent(this,UnitConverter.class);
            startActivity(i);
            return true;
        }



        else if (id == R.id.ab_about) {
            i = new Intent(this, credits.class);
            startActivity(i);
            return true;
        }

        else if (id == R.id.ab_exit) {
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





}
