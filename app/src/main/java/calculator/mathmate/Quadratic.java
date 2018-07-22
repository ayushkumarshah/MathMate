package calculator.mathmate;

public class Quadratic {
    static String display="";
    static String answer="";

    public static String quadratic(String xxx){

        int b1=0,b2=0,d,i,j=1,n=0,m=0,com1,com2,start=0,count=0,a=0,b=0,c=0;
        float x1=0,x2=0;
        String stra="",strb="",strc="";
        int[] fact=new int[100];
        String[] str=new String[100];
        String input = xxx;



        for (i = 0 ; i < input.length() ; i ++)  //Converting all character values in its real data type
        {
            if ((input.charAt(i)=='='|| input.charAt(i)=='+' || input.charAt(i)=='-')&&i!=0  )
            {
                str[j]=input.substring(start,i);
                start=i;
                j++;
            }
        }

        for(count=1;count<=j-1;count++)
        {
            for (i = 0 ; i <str[count].length() ; i ++)
            {
                if(str[count].regionMatches(i, "x^2", 0, 3))
                {
                    stra=str[count].substring(0,i);
                    if(stra.equals("")||stra.equals("+"))

                        a=1;
                    else if(stra.equals("-"))
                        a=-1;
                    break;

                }
                else if(str[count].regionMatches(i, "x^1", 0, 3) || str[count].regionMatches(i, "x", 0, 1))
                {
                    strb=str[count].substring(0,i);
                    if(strb.equals("")||strb.equals("+"))

                        b=1;
                    else if(strb.equals("-"))
                        b=-1;
                    break;

                }
            }
            if(!str[count].regionMatches(i, "x^2", 0, 3)&& !str[count].regionMatches(i, "x^1", 0, 3) && !str[count].regionMatches(i, "x", 0, 1))
            {
                strc=str[count];
                if(strc.equals("")||strc.equals("+"))

                    c=1;
                else if(strb.equals("-"))
                    c=-1;
            }
        }
        if(a!=1&&a!=-1)
        {
            if(stra.equals(""))
                a=0;
            else
                a=Integer.valueOf(stra);
        }
        if(b!=1&&b!=-1)
        {
            if(strb.equals(""))
                b=0;
            else
                b=Integer.valueOf(strb);
        }
        if(c!=1&&c!=-1)
        {
            if(strc.equals(""))
                c=0;
            else
                c=Integer.valueOf(strc);
        }

        int dis=b*b-4*a*c;
        double sqrt = Math.sqrt(dis);
        int x = (int) sqrt;
        if (c==0)
        {
            display=display.concat(a + "x^2+" + b + "x=0"+ "\n");
            display=display.concat(GCD(a,b) + "x("+ a/GCD(a,b) + "x+"+ b/GCD(a,b)+")=0"+ "\n");
            display=display.concat("x=0	OR	 "+ a/GCD(a,b) + "x+"+ b/GCD(a,b)+"=0"+ "\n");
            display=display.concat("x=0	OR	 "+"x="+ -b/GCD(a,b)+"/"+ a/GCD(a,b) + "\n");
            x1=(float)(-b/GCD(a,b)) /(a/GCD(a,b));
            System.out.println("x=0	OR	 "+"x="+ x1 );

        }
        else if(Math.pow(sqrt,2) == Math.pow(x,2))
        {
            d=(a*c);
            //finding factors of d
            int absd=Math.abs(d);
            for (j=1,n=0;j<=absd;j++)
            {
                if(absd%j==0)
                {
                    n++;
                    fact[n]=j;

                }
            }
            if(n%2==0)
                m = n/2;
            else
                m=n/2+1;
            for(i=1,j=n;i<=m;i++,j--)
            {
                if((fact[i]+fact[j]==b && d>0)||(Math.abs((fact[i]-fact[j]))==Math.abs(b)&&d<0))
                {
                    b1=fact[j];
                    b2=fact[i];
                    break;
                }
            }
            com1=GCD(a,b1);
            com2=GCD(b2,c);
            x1=(float)(-com2)/com1;
            x2=(float)(-b1)/a;

            if(d>0)
            {
                if(a>0)
                {
                    display=display.concat(a + "x^2+" + b + "x+"+ c+"=0" + "\n");
                    display=display.concat(a + "x^2+" + b1 + "x+" +b2 + "x+"+ c+"=0" + "\n");
                    display=display.concat(com1 + "x(" + a/com1 + "x+"+ b1/com1 + ")" + "+ " +com2 + "(" + b2/com2 + "x+" + c/com2 + ")" + "=" + "0" + "\n");
                    display=display.concat("("+com1 + "x+" + com2 + ")("+ a/com1 + "x+" + b1/com1 + ")=0" + "\n");
                    display=display.concat(com1 + "x+" + com2 + "=0  OR " + a/com1 + "x+" + b1/com1 + "=0" + "\n");

                    display=display.concat(  "x=" + (-com2)+"/"+com1 + "  OR  " + "x=" + (-b1)+"/"+a + "\n");
                    answer=display=display.concat(  "x=" + x1 + "  OR  " +"x=" + x2);

                }
                if(a<0)
                {
                    display=display.concat(a + "x^2+" + b + "x"+ c+"=0" + "\n");
                    display=display.concat(a + "x^2+" + b1 + "x+" +b2 + "x" + c+"=0" + "\n");
                    display=display.concat(com1 + "x(" + a/com1 + "x"+ b1/com1 + ")+ " +com2 + "(" + b2/com2 + "x" + c/com2 + ")" + "=" + "0" + "\n");
                    display=display.concat("("+com1 + "x+" + com2 + ")("+ a/com1 + "x" + b1/com1 + ")=0" + "\n");
                    display=display.concat(com1 + "x+" + com2 + "=0   OR " + "\n");
                    display=display.concat( a/com1 + "x" + b1/com1 + "=0" + "\n");
                    display=display.concat(  "x=" + (com2)+"/"+(-com1) + "  OR  " + "\n");
                    display=display.concat(  "x=" + b1+"/"+(-a) + "\n");
                    answer=display=display.concat(  "x=" + x1 + "  OR  " + "x=" + x2);
                }

            }

            else
            {
                if(a>0)
                {
                    if(b>0)
                    {
                        display=display.concat(a + "x^2+" + b + "x"+ c+"=0" + "\n");
                        display=display.concat(a + "x^2" + "+" + b1 + "x" + "-" +b2 + "x" +"+("+ c+")=0" + "\n");
                        display=display.concat(com1 + "x(" + a/com1 + "x+"+ b1/com1 + ")" + "-" +com2 + "(" + b2/com2 + "x+" + b1/com1 + ")" + "=" + "0" + "\n");
                        display=display.concat("("+com1 + "x-" + com2 + ")("+ a/com1 + "x+" + b1/com1 + ")=0" + "\n");
                        display=display.concat(com1 + "x-" + com2 + "=0  OR " + "\n");
                        display=display.concat( a/com1 + "x+" + b1/com1 + "=0" + "\n");
                        display=display.concat(  "x=" + (com2)+"/"+com1 + "  OR  " + "\n");
                        display=display.concat(  "x=" + (-b1)+"/"+a + "\n");
                        answer=display=display.concat(  "x=" + (-x1) + "  OR  " + "x=" + x2);
                    }
                    else
                    {
                        display=display.concat(a + "x^2" + b + "x"+ c+"=0" + "\n");
                        display=display.concat(a + "x^2" + "-" + b1 + "x" + "+" +b2 + "x" +"+("+ c+")=0" + "\n");
                        display=display.concat(com1 + "x(" + a/com1 + "x-"+ b1/com1 + ")" + "+" +com2 + "(" + b2/com2 + "x" + c/com2 + ")" + "=" + "0" + "\n");
                        display=display.concat("("+com1 + "x+" + com2 + ")("+ a/com1 + "x-" + b1/com1 + ")=0" + "\n");
                        display=display.concat(com1 + "x+" + com2 + "=0  OR " + "\n");
                        display=display.concat( a/com1 + "x-" + b1/com1 + "=0" + "\n");
                        display=display.concat(  "x=" + (-com2)+"/"+com1 + "  OR  " + "\n");
                        display=display.concat(  "x=" + (b1)+"/"+a + "\n");
                        answer=display=display.concat(  "x=" + x1 + "  OR  " + "x=" + (-x2));
                    }
                }
                else
                {
                    if(b>0)
                    {
                        display=display.concat(a + "x^2+" + b + "x+"+ c+"=0" + "\n");
                        display=display.concat(a + "x^2" + "+" + b1 + "x" + "-" +b2 + "x" +"+"+ c+"=0" + "\n");
                        display=display.concat(com1 + "x(" + a/com1 + "x"+ b1/com1 + ")" + "-" +com2 + "(" + b2/com2 + "x-" + (c)/com2 + ")" + "=" + "0" + "\n");
                        display=display.concat("("+com1 + "x-" + com2 + ")("+ a/com1 + "x" + b1/com1 + ")=0" + "\n");
                        display=display.concat(com1 + "x-" + com2 + "=0  OR " + "\n");
                        display=display.concat( a/com1 + "x" + b1/com1 + "=0" + "\n");
                        display=display.concat(  "x=" + (com2)+"/"+com1 + "  OR  " + "\n");
                        display=display.concat(  "x=" + (b1)+"/"+(-a) + "\n");
                        answer=display=display.concat(  "x=" + (-x1) + "  OR  " + "x=" + x2);
                    }
                    else
                    {
                        display=display.concat(a + "x^2" + b + "x+"+ c+"=0" + "\n");
                        display=display.concat(a + "x^2" + "-" + b1 + "x" + "+" +b2 + "x" +"+"+ c+"=0" + "\n");
                        display=display.concat(com1 + "x(" + a/com1 + "x+"+ c/com2 + ")" + "+" +com2 + "(" + b2/com2 + "x+" + c/com2 + ")" + "=" + "0" + "\n");
                        display=display.concat("("+com1 + "x+" + com2 + ")("+ a/com1 + "x+" + c/com2 + ")=0" + "\n");
                        display=display.concat(com1 + "x+" + com2 + "=0  OR " + "\n");
                        display=display.concat( a/com1 + "x+" + c/com2 + "=0" + "\n");
                        display=display.concat(  "x=" + (-com2)+"/"+com1 + "  OR  " + "\n");
                        display=display.concat(  "x=" + (-b1)+"/"+(-a) + "\n");
                        answer=display=display.concat(  "x=" + x1 + "  OR  " + "x=" + (-x2));
                    }
                }


            }
        }
        else
        {
            if(dis>=0)

            {
                display=display.concat(a + "x^2+" + b + "x+"+ c+"=0" + "\n");
                display=display.concat("x=(-b+sqrt(b^2-4ac))/2a			OR			x=(-b-sqrt(b^2-4ac))/2a" + "\n");
                display=display.concat("x=("+(-b)+"+sqrt("+(b*b)+"-4*"+a+"*"+c+"))/"+"2*"+a+"		OR			x=("+(-b)+"-sqrt("+(b*b)+"-4*"+a+"*"+c+"))/"+"2*"+a + "\n");
                display=display.concat("x=("+(-b)+"+sqrt("+((b*b)-(4*a*c))+"))/"+2*a+"      			OR			x=("+(-b)+"-sqrt("+((b*b)-(4*a*c))+"))/"+2*a  + "\n");
                display=display.concat("x=("+(-b)+"+"+(float)Math.sqrt((b*b)-(4*a*c))+")/"+2*a+"		OR			x=("+(-b)+"-"+(float)Math.sqrt((b*b)-(4*a*c))+")/"+2*a + "\n");
                answer=display=display.concat("x="+(((-b)+(float)Math.sqrt((b*b)-(4*a*c)))/2*a)+"			OR			x="+(((-b)-(float)Math.sqrt((b*b)-(4*a*c)))/2*a));
            }
            else
            {
                display=display.concat(a + "x^2+" + b + "x+"+ c+"=0" + "\n");
                display=display.concat("x=(-b+sqrt(b^2-4ac))/2a			OR			x=(-b-sqrt(b^2-4ac))/2a" + "\n");
                display=display.concat("x=("+(-b)+"+sqrt("+(b*b)+"-4*"+a+"*"+c+"))/"+"2*"+a+"		OR			x=("+(-b)+"-sqrt("+(b*b)+"-4*"+a+"*"+c+"))/"+"2*"+a + "\n");
                display=display.concat("x=("+(-b)+"+sqrt("+((b*b)-(4*a*c))+"))/"+2*a+"      			OR			x=("+(-b)+"-sqrt("+((b*b)-(4*a*c))+"))/"+2*a  + "\n");
                display=display.concat("x=("+(-b)+"+"+(float)Math.sqrt(-((b*b)-(4*a*c)))+"i)/"+2*a+"		OR			x=("+(-b)+"-"+(float)Math.sqrt(-((b*b)-(4*a*c)))+"i)/"+2*a + "\n");
                answer=display=display.concat("x=("+(float)(-b)/(2*a)+")+("+((float)Math.sqrt(-((b*b)-(4*a*c))))/(2*a)+")i			OR			x=("+(float)(-b)/(2*a)+")-("+((float)Math.sqrt(-((b*b)-(4*a*c))))/(2*a)+")i");
            }
        }


        String z=display.toString();
        display="";

        return z;


    }


    public static String eqnanswer(){
        String ans=answer.toString();
        answer="";
        return ans;
    }


    private static int GCD(int number1, int number2)
    { //base case
        if(number2 == 0)
        {
            return number1;
        }
        return GCD(number2, number1%number2);
    }
}

