package calculator.mathmate;

import static calculator.mathmate.Concatinator.display;
import static calculator.mathmate.Concatinator.print;
import static calculator.mathmate.Concatinator.println;
import static calculator.mathmate.Concatinator.underline;


public class Bisection {


    static int[] COEFF_DEG=new int[20];
    static int highest_degree;

    public static String bisectson(String degree,float A,float B){
        float a, b, c; // a, b and c have the usual meaning
        float f_of_a, f_of_b; // f_of_a, f_of_b store values of f(a) and f(b)
        // respectively
        String[] str=new String[6];
        String[] string=new String[100];
        int i=0,j=1,start=0,count=0;
        String input = degree;
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
            if(!str[count].regionMatches(i, "x^5", 0, 3)&&!str[count].regionMatches(i, "x^4", 0, 3)&&!str[count].regionMatches(i, "x^3", 0, 3)&&!str[count].regionMatches(i, "x^2", 0, 3)&& !str[count].regionMatches(i, "x^1", 0, 3) && !str[count].regionMatches(i, "x", 0, 1))
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



        // The following do-while loop keeps asking the user for a and b until
        // f(a)f(b) does not become negative
        do {
            a = A;
            b = B;
            if (f(a) * f(b) >= 0) {
                String exc="X";
                return exc;
            }
        } while (f(a) * f(b) >= 0);
        f_of_a = f(a);
        f_of_b = f(b);
        float root = bisectionMethod(f_of_a, f_of_b, a, b);
        display.append("Root is : " + root);
        String zz=display.toString();
        display.setLength(0);
        return zz;

    }

    public static float f(float num) { // Calculates f(x) given x and returns
        float x=0;
        int i;// f(x)
        for(i=5;i>=0;i--){
            x+=(COEFF_DEG[i] * Math.pow(num, i)) ;
        }
        return x;
    }

    public static float bisectionMethod(float f_of_a, float f_of_b, float a,
                                        float b) { // Does the actual work of evaluating
        float c=0; // the root using the method of bisection.
        float f_of_c;
        int count=1;
        final float TOLERANCE = 0.00001f;
        Concatinator con=new Concatinator();
        print("n", 2);
        print("a", 15);
        print("b", 15);
        print("c", 15);
        print("f(a)", 15);
        print("f(b)", 15);
        print("f(c)", 15);
        print("(b-a)", 15);
        underline();
        while (Math.abs(a - b) > TOLERANCE) {
            c = (a + b) / 2;
            f_of_c = f(c);
            f_of_a = f(a);
            f_of_b = f(b);
            print(count, 2);
            print(a, 15);
            print(b, 15);
            print(c, 15);
            print( f_of_a, 15);
            print(f_of_b, 15);
            print( f_of_c, 15);
            print((b-a), 15);
            println();
            count++;

            if (f_of_c * f(a) == 0 || f_of_c * f(b) == 0) {
                return c;
            } else if (f_of_c * f(a) > 0) {
                a = c;
            } else {
                b = c;
            }
        }
        return c;
    }



}
