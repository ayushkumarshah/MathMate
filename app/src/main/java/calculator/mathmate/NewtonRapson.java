package calculator.mathmate;

import static calculator.mathmate.Concatinator.display;
import static calculator.mathmate.Concatinator.print;
import static calculator.mathmate.Concatinator.println;
import static calculator.mathmate.Concatinator.underline;
import static calculator.mathmate.Derivative.derivative;

public class NewtonRapson {
    static int[] COEFF_DEG=new int[11];
    static String input2 ="",der="",input="";

    public static String NRapson(String eqn,float est){
         // a, b and c have the usual meaning

        input = eqn;
        for(int i=0;input.charAt(i)!='=';i++)
            input2=input2.concat(input.substring(i,i+1));
        der = derivative(input2);
        der=der.concat("=0");


        // The following do-while loop keeps asking the user for a and b until
        // f(a)f(b) does not become negative
        float root = NewtonRaphson(est);
        String zz=(display+"\n"+"Root is"+root).toString();
        display.setLength(0);
        return zz;
    }
    public static void coefficients(String input)
    {
        String[] str=new String[6];
        String[] string=new String[100];
        int i=0,j=1,start=0,count=0;
        for (i=0;i<=10;i++)
        {
            string[i]="";
        }
        for (i = 0 ; i < input.length() ; i ++)  //Converting all character values in its real data type
        {
            if ((input.charAt(i)=='='|| input.charAt(i)=='+' || input.charAt(i)=='-')&&i!=0  )
            {
                str[j]=input.substring(start,i);
                start=i;
                j++;
            }
        }
        for (i = 0; i <=10; i++)
        {
            COEFF_DEG[i] = 0;

        }
        for(count=1;count<=j-1;count++)
        {
            for (i = 0 ; i <str[count].length() ; i ++)
            {
                if(str[count].regionMatches(i, "x^10", 0, 4))
                {
                    string[10]=str[count].substring(0,i);
                    if(string[10].equals("")||string[10].equals("+"))

                        COEFF_DEG[10]=1;
                    else if(string[10].equals("-"))
                        COEFF_DEG[10]=-1;
                    else
                        COEFF_DEG[10]=Integer.valueOf(string[10]);

                    break;

                }
                else if(str[count].regionMatches(i, "x^9", 0, 3))
                {
                    string[9]=str[count].substring(0,i);
                    if(string[9].equals("")||string[9].equals("+"))

                        COEFF_DEG[9]=1;
                    else if(string[9].equals("-"))
                        COEFF_DEG[9]=-1;
                    else
                        COEFF_DEG[9]=Integer.valueOf(string[9]);

                    break;

                }
                else if(str[count].regionMatches(i, "x^8", 0, 3))
                {
                    string[8]=str[count].substring(0,i);
                    if(string[8].equals("")||string[8].equals("+"))

                        COEFF_DEG[8]=1;
                    else if(string[8].equals("-"))
                        COEFF_DEG[8]=-1;
                    else
                        COEFF_DEG[8]=Integer.valueOf(string[8]);

                    break;

                }
                else if(str[count].regionMatches(i, "x^7", 0, 3))
                {
                    string[7]=str[count].substring(0,i);
                    if(string[7].equals("")||string[7].equals("+"))

                        COEFF_DEG[7]=1;
                    else if(string[7].equals("-"))
                        COEFF_DEG[7]=-1;
                    else
                        COEFF_DEG[7]=Integer.valueOf(string[7]);

                    break;

                }
                else if(str[count].regionMatches(i, "x^6", 0, 3))
                {
                    string[6]=str[count].substring(0,i);
                    if(string[6].equals("")||string[6].equals("+"))

                        COEFF_DEG[6]=1;
                    else if(string[6].equals("-"))
                        COEFF_DEG[6]=-1;
                    else
                        COEFF_DEG[6]=Integer.valueOf(string[6]);

                    break;

                }
                else if(str[count].regionMatches(i, "x^5", 0, 3))
                {
                    string[5]=str[count].substring(0,i);
                    if(string[5].equals("")||string[5].equals("+"))

                        COEFF_DEG[5]=1;
                    else if(string[5].equals("-"))
                        COEFF_DEG[5]=-1;
                    else
                        COEFF_DEG[5]=Integer.valueOf(string[5]);

                    break;

                }
                else if(str[count].regionMatches(i, "x^4", 0, 3))
                {
                    string[4]=str[count].substring(0,i);
                    if(string[4].equals("")||string[4].equals("+"))

                        COEFF_DEG[4]=1;
                    else if(string[4].equals("-"))
                        COEFF_DEG[4]=-1;
                    else
                        COEFF_DEG[4]=Integer.valueOf(string[4]);

                    break;

                }
                else if(str[count].regionMatches(i, "x^3", 0, 3))
                {
                    string[3]=str[count].substring(0,i);
                    if(string[3].equals("")||string[3].equals("+"))

                        COEFF_DEG[3]=1;
                    else if(string[3].equals("-"))
                        COEFF_DEG[3]=-1;
                    else
                        COEFF_DEG[3]=Integer.valueOf(string[3]);


                    break;

                }
                else if(str[count].regionMatches(i, "x^2", 0, 3))
                {
                    string[2]=str[count].substring(0,i);
                    if(string[2].equals("")||string[2].equals("+"))

                        COEFF_DEG[2]=1;
                    else if(string[2].equals("-"))
                        COEFF_DEG[2]=-1;
                    else
                        COEFF_DEG[2]=Integer.valueOf(string[2]);


                    break;

                }
                else if(str[count].regionMatches(i, "x^1", 0, 3) || str[count].regionMatches(i, "x", 0, 1))
                {
                    string[1]=str[count].substring(0,i);
                    if(string[1].equals("")||string[1].equals("+"))

                        COEFF_DEG[1]=1;
                    else if(string[1].equals("-"))
                        COEFF_DEG[1]=-1;
                    else
                        COEFF_DEG[1]=Integer.valueOf(string[1]);


                    break;

                }
            }
            if(!str[count].regionMatches(i, "x^10", 0, 4)&&!str[count].regionMatches(i, "x^9", 0, 3)&&!str[count].regionMatches(i, "x^8", 0, 3)&&!str[count].regionMatches(i, "x^7", 0, 3)&&!str[count].regionMatches(i, "x^6", 0, 3)&&!str[count].regionMatches(i, "x^5", 0, 3)&&!str[count].regionMatches(i, "x^4", 0, 3)&&!str[count].regionMatches(i, "x^3", 0, 3)&&!str[count].regionMatches(i, "x^2", 0, 3)&& !str[count].regionMatches(i, "x^1", 0, 3) && !str[count].regionMatches(i, "x", 0, 1))
            {
                string[0]=str[count];
                if(string[0].equals("")||string[0].equals("+"))

                    COEFF_DEG[0]=1;
                else if(string[0].equals("-"))
                    COEFF_DEG[0]=-1;
                else
                    COEFF_DEG[0]=Integer.valueOf(string[0]);

            }
        }
    }
    public static float f(float num) { // Calculates f(x) given x and returns

        coefficients(input);
        float x=0;
        int i;
        for(i=10;i>=0;i--){
            x+=(COEFF_DEG[i] * Math.pow(num, i)) ;
        }
        return x;
    }




    public static float g (float num){

        coefficients(der);
        float x=0;
        int i;
        for(i=10;i>=0;i--){
            x+=(COEFF_DEG[i] * Math.pow(num, i)) ;
        }
        return x;
    }


    public static float NewtonRaphson (float x0)
    {
        final float Error=0.0000005f;
        int count=0;
        float x1=0;
        float f_of_x0=0,g_of_x0=0; // f_of_a, f_of_b store values of f(a) and f(b) respectively

        print("n", 2);
        print("x(n)", 15);
        print("f(x(n))", 15);
        print("f'(x(n))", 15);
        print("x(n+1)", 15);
        print("|x(n+1)-x(n)|", 15);
        underline();
        float x2=x0;
        while (Math.abs(x2 - x1) > Error) {

            f_of_x0 = f(x0);
            g_of_x0=g(x0);
            x1 = x0 - (f(x0)/g(x0));
            print(count, 2);
            print(x0, 15);
            print(f_of_x0, 15);
            print(g_of_x0, 15);
            print( x1, 15);
            print(Math.abs(x0-x1), 15);
            println();
            count++;
            x2=x0;
            x0=x1;
        }
        return x1;
    }



}