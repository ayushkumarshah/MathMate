package calculator.mathmate;

public class Derivative {
    static int input1 = 0 ,input2=0;         // This variable stores the integer value of a Q. like 4x^2
    static char variable = ' ' ;   // This variable stores the variable of a Q. as a character. like 4x^2
    static int power = 0 ;        // This variable stores the power of a Q. as a integer. like 4x^2
    static int index = 0,i=0 ;	     // variable use under in switch statement .
    static int multi = 1 ,ang=1,sign=1;
    static String trigo="",dertrigo="",display="",angle="",pow="",pow_backup="",derpow="",log="",logvalue="",derlog="",logvalue_backup="",derexp="",expvalue="",expvalue_backup="";
    public static String derivative(String input)
    {

        int pmposbegin=0; //plus or minus starting position
        int pmposend=0;//plus or minus ending position
        for (int i = 0 ; i < input.length() ; i ++)  //Converting all character values in its real data type
        {
            if (input.charAt(i)=='(')
                i=input.indexOf(')');

            else if (input.charAt(i)=='+')
            {
                pmposend=input.indexOf('+',pmposend+1);
                calcder(input,pmposbegin,pmposend);
                display=display.concat("+");
                pmposbegin=pmposend+1;
            }
            else if(input.charAt(i)=='-'&& i!=0 &&input.charAt(i-1)!='^'&& input.charAt(i-1)!='(')
            {
                pmposend=input.indexOf('-',pmposend+1);
                calcder(input,pmposbegin,pmposend);
                display=display.concat("-");
                pmposbegin=pmposend+1;

            }


        }

        calcder(input,pmposbegin,input.length());
        pmposbegin=0;
        pmposend=0;
        String deriv=display;
        display="";
        return deriv;

    }

    public static void calcder(String input,int pmposbegin,int pmposend)
    {
        int flag=0;
        for (index = pmposbegin ; index < pmposend ; index++)  //Converting all character values in its real data type
        {
            switch (input.charAt(index))
            {


                case '1' :
                    input1 = input1 * multi ;
                    input1 = input1 + 1 ;
                    break ;
                case '2' :
                    input1 = input1 * multi ;
                    input1 = input1 + 2 ;
                    break ;
                case '3' :
                    input1 = input1 * multi ;
                    input1 = input1 + 3 ;
                    break ;
                case '4' :
                    input1 = input1 * multi ;
                    input1 = input1 + 4 ;
                    break ;
                case '5' :
                    input1 = input1 * multi ;
                    input1 = input1 + 5 ;
                    break ;
                case '6' :
                    input1 = input1 * multi ;
                    input1 = input1 + 6 ;
                    break ;
                case '7' :
                    input1 = input1 * multi ;
                    input1 = input1 + 7 ;
                    break ;
                case '8' :
                    input1 = input1 * multi ;
                    input1 = input1 + 8 ;
                    break ;
                case '9' :
                    input1 = input1 * multi ;
                    input1 = input1 + 9 ;
                    break ;
                case '0' :
                    input1 = input1 * multi ;
                    input1 = input1 + 0 ;
                    break ;
                case 'x' :
                    variable = 'x' ;
                    break ;
                case 'e':
                    for(int i=index+2;i!=pmposend;i++)
                    {
                        if(input.charAt(i)=='('||input.charAt(i)==')')
                            continue;
                        expvalue=expvalue.concat(input.substring(i,i+1));
                    }
                    expvalue_backup=expvalue;
                    derexp=derivative(expvalue);

                    index=pmposend-1;
                    break;
                case 'l':
                    log=input.substring(index,index+3);
                    if(log.equals("log"))
                    {
                        for(int i=index+3;i!=pmposend;i++)
                        {
                            if(input.charAt(i)=='('||input.charAt(i)==')')
                                continue;
                            logvalue=logvalue.concat(input.substring(i,i+1));
                        }
                        logvalue_backup=logvalue;

                        derlog=derivative(logvalue);

                        index=pmposend-1;
                    }
                    break;

                case 's':
                    trigo=input.substring(index,index+3);

                    if(trigo.equals("sin"))
                    {


                        for(int i=index+3;input.charAt(i)!='x';i++)
                        {
                            if(input.charAt(i)=='(')
                                continue;
                            angle=angle.concat(input.substring(i,i+1));
                        }
                        dertrigo="cos"+angle+"x";
                        index=input.indexOf('x',index);
                        if(!angle.equals(""))
                            ang=Integer.valueOf(angle);


                    }
                    else if(trigo.equals("sec"))
                    {
                        for(int i=index+3;input.charAt(i)!='x';i++)
                        {
                            if(input.charAt(i)=='(')
                                continue;
                            angle=angle.concat(input.substring(i,i+1));
                        }
                        dertrigo="sec"+angle+"x.tan"+angle+"x";
                        index=input.indexOf('x',index);
                        if(!angle.equals(""))
                            ang=Integer.valueOf(angle);
                    }
                    break;
                case 'c':
                    if(input.charAt(index+3)=='e')
                        trigo=input.substring(index,index+5);
                    else
                        trigo=input.substring(index,index+3);
                    if(trigo.equals("cosec"))
                    {
                        for(int i=index+5;input.charAt(i)!='x';i++)
                        {
                            if(input.charAt(i)=='(')
                                continue;
                            angle=angle.concat(input.substring(i,i+1));
                        }
                        dertrigo="cosec"+angle+"x.cot"+angle+"x";
                        index=input.indexOf('x',index);
                        if(!angle.equals(""))
                            ang=Integer.valueOf(angle);
                        sign=-1;
                    }
                    if(trigo.equals("cos"))
                    {
                        for(int i=index+3;input.charAt(i)!='x';i++)
                        {
                            if(input.charAt(i)=='(')
                                continue;
                            angle=angle.concat(input.substring(i,i+1));
                        }
                        dertrigo="sin"+angle+"x";
                        index=input.indexOf('x',index);
                        if(!angle.equals(""))
                            ang=Integer.valueOf(angle);
                        sign=-1;
                    }
                    else if(trigo.equals("cot"))
                    {
                        for(int i=index+3;input.charAt(i)!='x';i++)
                        {
                            if(input.charAt(i)=='(')
                                continue;
                            angle=angle.concat(input.substring(i,i+1));
                        }
                        dertrigo="(cosec"+angle+"x)^2";
                        index=input.indexOf('x',index);
                        if(!angle.equals(""))
                            ang=Integer.valueOf(angle);
                        sign=-1;
                    }

                    break;
                case 't':
                    trigo=input.substring(index,index+3);
                    if(trigo.equals("tan"))
                    {
                        for(i=index+3;input.charAt(i)!='x';i++)
                        {
                            if(input.charAt(i)=='(')
                                continue;
                            angle=angle.concat(input.substring(i,i+1));
                        }
                        dertrigo="(sec"+angle+"x)^2";
                        index=input.indexOf('x',index);
                        if(!angle.equals(""))
                            ang=Integer.valueOf(angle);

                    }
                    break;
                case '^' :
                    for(i=index+1;(i)!=pmposend;i++)
                    {
                        if(input.charAt(i)=='('||input.charAt(i)==')')
                            continue;
                        pow=pow.concat(input.substring(i,i+1));
                        if(input.charAt(i)=='x')
                            flag=1;
                    }
                    if (flag==1)
                    {
                        input2=input1;
                        input1=0;
                        pow_backup=pow;
                        System.out.println(pow);
                        derpow=derivative(pow);
                        System.out.println(derpow);
                        index=pmposend-1;
                    }

                    if(flag!=1)
                    {
                        power=Integer.valueOf(pow);
                        index=pmposend;
                    }
                    break;
                default :
                    break ;
            }
            if (index==pmposbegin)
            {
                multi*=10;
            }

        }		if (input.charAt(pmposbegin)=='-')
        input1=-input1;

        if(dertrigo.equals("")&&derlog.equals("")&&derexp.equals("")&&flag!=1)
        {

            if (variable == ' ')
                display=display.concat("0") ;
            else if (input1 != 0  && variable != ' ' && power == 0)
                display=display.concat(Integer.toString(input1));

            else if (input1 == 0 && variable != ' ' && power == 0 && input.charAt(pmposbegin)=='-')
                display=display.concat("-1") ;
            else if (input1 == 0 && variable != ' ' && power == 0)
                display=display.concat("1") ;
            else if (input1 == 0 && variable != ' ' && power != 0 && input.charAt(pmposbegin)=='-')
                display=display.concat(Integer.toString(-power)+(variable)+"^"+Integer.toString(power-1)) ;
            else if (input1 == 0 && variable != ' ' && power != 0 )
                display=display.concat(Integer.toString(power)+(variable)+"^"+Integer.toString(power-1)) ;

            else if (input1 != 0 && variable != ' ' && power != 0)
                display=display.concat(Integer.toString(power*input1)+(variable)+"^"+Integer.toString(power-1)) ;

        }
        else if(!dertrigo.equals(""))
        {

            if (input1 != 0  && power == 0)
                display=display.concat(sign*ang*input1+dertrigo);

            else if (input1 == 0  && power == 0 && input.charAt(pmposbegin)=='-')
                display=display.concat(-sign*ang+dertrigo) ;
            else if (input1 == 0 && power == 0)
                display=display.concat(sign*ang+dertrigo) ;
            else if (input1 == 0 &&  power != 0 && input.charAt(pmposbegin)=='-')
                display=display.concat(Integer.toString(-sign*ang*power)+(trigo)+angle+"x^"+Integer.toString(power-1)+"."+dertrigo) ;
            else if (input1 == 0 && power != 0 )
                display=display.concat(Integer.toString(sign*ang*power)+trigo+angle+"x^"+Integer.toString(power-1)+"."+dertrigo) ;

            else if (input1 != 0 &&  power != 0)
                display=display.concat(Integer.toString(sign*ang*power*input1)+(trigo)+angle+"x^"+Integer.toString(power-1)+"."+dertrigo) ;

        }

        else if(!derlog.equals(""))
            display=display.concat("("+derlog+")/("+logvalue_backup+")");
        else if(!derexp.equals(""))
            display=display.concat("("+derexp+").("+"e^("+expvalue_backup+"))");
        else if(flag==1)
            display=display.concat("("+derpow+")."+input2+"^("+pow_backup+").log("+input2+")");

        ang=1;
        input1=0;
        multi=1;
        variable=' ';
        power=0;
        dertrigo="";
        angle="";
        sign=1;
        pow="";
        log="";
        derlog="";
        logvalue="";
        derexp="";
        expvalue="";


    }


    public static String derivater(String eqn){
        String input1="",input2="",der="";
        // Loop for calling the program continuously.
        // Input from user as a character variable.

        String input=eqn; // Get input from user
        for (int i = 0 ; i < input.length() ; i ++)  //Converting all character values in its real data type
        {
            if (input.charAt(i)=='.')
            {
                input1=input.substring(0,i);
                input2=input.substring(i+1,input.length());
            }
        }
        if(input1==""&&input2=="")
            der=derivative(input);
        else
            der="("+input1+").("+derivative(input2)+")+("+input2+").("+derivative(input1)+")";

        String zz=der;
        der="";
        input1="";
        input2="";
        return zz;

    }
}