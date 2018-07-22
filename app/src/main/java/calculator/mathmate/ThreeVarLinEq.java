package calculator.mathmate;

public class ThreeVarLinEq {
    static StringBuilder display = new StringBuilder(200);
    public static String threevareqn(String eq1,String eq2,String eq3){

        float b1n,b2n,c1n,c2n,d1n,d2n,b3n,c3n,d3n,b2nn,c2nn,d2nn;

        float p1=0,q1=0,r1=0,p2=0,q2=0,r2=0;
        String[] eqn = new String[3];
        String[] str = new String[20];
        String[] strvar = new String[20];
        float[] a = new float[4];
        float[] b = new float[4];
        float[] c = new float[4];
        float[] d = new float[4];
        eqn[0]=eq1;
        eqn[1]=eq2;
        eqn[2]=eq3;


        int i=0,j=1,start=0,count=0,k=1,ca=1,cb=1,cc=1,cd=1;
        for (i = 0; i < 3; i++)
        {
            a[i]=0;
            b[i]=0;
            c[i]=0;
            d[i]=0;

        }
        for(int cnt=0;cnt<3;cnt++)
        {
            for (i = 0 ; i < eqn[cnt].length() ; i ++)  //Converting all character values in its real data type
            {
                if ((eqn[cnt].charAt(i)=='='|| eqn[cnt].charAt(i)=='+' || eqn[cnt].charAt(i)=='-' )&&i!=0  )
                {

                    str[j]=eqn[cnt].substring(start,i);
                    start=i;
                    j++;
                    if(eqn[cnt].charAt(i)=='=')
                    {
                        str[j]=eqn[cnt].substring(start+1,eqn[cnt].length());
                        j++;
                        break;
                    }

                }
            }
            start=0;
        }

        for(count=1;count<j;count++)
        {

            for (i = 0 ; i <str[count].length() ; i ++)
            {
                if(str[count].regionMatches(i, "x", 0, 1))
                {
                    strvar[count]=str[count].substring(0,i);
                    if(strvar[count].equals("")||strvar[count].equals("+"))
                    {
                        a[ca]=1;

                    }
                    else if(strvar[count].equals("-"))
                    {
                        a[ca]=-1;

                    }
                    else
                    {
                        a[ca]=Integer.valueOf(strvar[count]);
                    }
                    ca++;
                    break;
                }
                else if(str[count].regionMatches(i, "y", 0, 1) )
                {
                    strvar[count]=str[count].substring(0,i);
                    if(strvar[count].equals("")||strvar[count].equals("+"))
                    {
                        b[cb]=1;

                    }
                    else if(strvar[count].equals("-"))
                    {
                        b[cb]=-1;

                    }
                    else
                    {
                        b[cb]=Integer.valueOf(strvar[count]);
                    }
                    cb++;
                    break;
                }
                else if(str[count].regionMatches(i, "z", 0, 1) )
                {
                    strvar[count]=str[count].substring(0,i);
                    if(strvar[count].equals("")||strvar[count].equals("+"))
                    {
                        c[cc]=1;

                    }
                    else if(strvar[count].equals("-"))
                    {
                        c[cc]=-1;

                    }
                    else
                    {
                        c[cc]=Integer.valueOf(strvar[count]);
                    }
                    cc++;
                    break;
                }
            }
            if(!str[count].regionMatches(i, "x", 0, 1)&& !str[count].regionMatches(i, "y", 0, 1) && !str[count].regionMatches(i, "z", 0, 1))
            {
                strvar[count]=str[count];
                if(strvar[count].equals("")||strvar[count].equals("+"))
                {
                    d[cd]=1;

                }
                else if(strvar[count].equals("-"))
                {
                    d[cd]=-1;

                }
                else
                {
                    d[cd]=Integer.valueOf(strvar[count]);
                }
                cd++;

            }
        }

        display.append("The entered equations are:" +"\n");
        display.append(a[1]+"x + "+b[1]+"y +"+c[1]+"z "+"="+d[1]+"......eqn 1" +"\n");
        display.append(a[2]+"x + "+b[2]+"y +"+c[2]+"z "+"="+d[2]+"......eqn 2" +"\n");
        display.append(a[3]+"x + "+b[3]+"y +"+c[3]+"z "+"="+d[3]+"......eqn 3" +"\n");
        display.append("\n");
        display.append("\n"+"Eliminating x from equation 1 and 2" +"\n");
        display.append("\n");
        display.append("\n"+"Multiplying eqn 1 by " + LCM(a[1],a[2])/a[1] + " and eqn 2 by " + LCM(a[1],a[2])/a[2] + ","+"\n"+ "we get" +"\n");
        b1n= LCM(a[1],a[2])/a[1]*b[1];
        c1n=LCM(a[1],a[2])/a[1]*c[1];
        d1n=LCM(a[1],a[2])/a[1]*d[1];
        b2n= LCM(a[1],a[2])/a[2]* b[2];
        c2n=LCM(a[1],a[2])/a[2] * c[2];
        d2n= LCM(a[1],a[2])/a[2] *d[2];

        display.append("\n"+LCM(a[1],a[2])+"x +"+b1n+"y +"+c1n+"z "+"="+d1n+"\n");
        display.append("\n"+LCM(a[1],a[2])+"x +"+b2n+"y +"+c2n+"z "+"="+d2n+"\n");
        display.append("\n"+"_________________________" +"\n");

        {
            if (LCM(a[1],a[2])>=0)
                display.append("-    ");
            if (LCM(a[1],a[2])<0)
                display.append("+    ");
            if (b2n >= 0)
            {
                display.append("    -");
            }
            if (b2n < 0)
                display.append("    +");
            if(c2n>=0)
                display.append("    -");
            if(c2n<0)
                display.append("    +");
            if (d2n>=0)
                display.append("    -");
            if (d2n<0)
                display.append("    +");
        }
        display.append("\n"+"_________________________" +"\n");

        if(b2n>=0)
        {
            p1=b1n-b2n;
            display.append("0 +"+ (b1n-b2n) + "y +" );

        }
        if(b2n<0)
        {
            p1=b1n+Math.abs(b2n);
            display.append("0 +"+ p1 + "y +" );

        }
        if (c2n>=0)
        {
            q1=c1n-c2n;
            display.append( (c1n-c2n) + "z" );

        }
        if (c2n<0)
        {
            q1=c1n+Math.abs(c2n);
            display.append( q1 + "z" );

        }

        if (d2n>=0)
        {   r1=d1n-d2n;
            display.append("="+ (d1n-d2n));
        }




        if (d2n<0)
        {
            r1=d1n+Math.abs(d2n);
            display.append("="+ r1);
        }

        display.append("\n");
        display.append("\n"+"eliminating x from equation 2 and 3"+"\n");
        display.append("\n");
        display.append("\n"+"Multiplying eqn 2 by" + LCM(a[2],a[3])/a[2] + "and eqn 3 by " + LCM(a[2],a[3])/a[3] + ","+"\n"+ "we get"+"\n");
        b2nn= LCM(a[2],a[3])/a[2]*b[2];
        c2nn=LCM(a[2],a[3])/a[2]*c[2];
        d2nn=LCM(a[2],a[3])/a[2]*d[2];
        b3n= LCM(a[2],a[3])/a[3]* b[3];
        c3n=LCM(a[2],a[3])/a[3] * c[3];
        d3n= LCM(a[2],a[3])/a[3] *d[3];

        display.append(LCM(a[2],a[3]) + "x +" + b2nn + "y +"+ c2nn +"z " +"=" + d2nn +"\n");
        display.append(LCM(a[2],a[3]) + "x +" + b3n  + "y +"+ c3n + "z " +"="+ d3n +"\n");

        display.append("\n"+"_________________________" +"\n");
        {
            if (LCM(a[2],a[3])>=0)
                display.append("-    ");
            if (LCM(a[2],a[3])<0)
                display.append("+    ");
            if (b3n >= 0)
            {
                display.append("    -");
            }
            if (b3n < 0)
                display.append("     +");
            if(c3n>=0)
                display.append("     -");
            if(c3n<0)
                display.append("     +");
            if (d3n>=0)
                display.append("     -");
            if (d3n<0)
                display.append("     +");
        }
        display.append("\n"+"_________________________" +"\n");

        if(b3n>=0)
        {
            p2=b2nn-b3n;
            display.append("0 +"+ (b2nn-b3n) + "y +" );

        }
        if(b3n<0)
        {
            p2=b2nn+Math.abs(b3n);
            display.append("0 +"+ p2 + "y +" );

        }
        if (c3n>=0)
        {
            q2=c2nn-c3n;
            display.append( (c2nn-c3n) + "z" );

        }
        if (c3n<0)
        {
            q2=c2nn+Math.abs(c3n);
            display.append( q2 + "z" );

        }
        if (d3n>=0)
        {
            r2=d2nn-d3n;
            display.append("="+ (d2nn-d3n));
        }




        if (d3n<0)
        {
            r2=d2nn+Math.abs(d3n);
            display.append("="+ r2+"\n");
        }
        display.append("\n");
        display.append("\n"+"the equations after eliminating x are" +"\n");

        display.append(p1 + "y +"+ q1 + "z"+"="+ r1 +"\n");
        display.append(p2 + "y +"+ q2 + "z"+"="+ r2 +"\n");

        float q1n,q2n,r1n,r2n;
        q1n=LCM(p1,p2)/p1*q1;
        r1n=LCM(p1,p2)/p1*r1;
        q2n=LCM(p1,p2)/p2*q2;
        r2n=LCM(p1,p2)/p2*r2;

        display.append("Multiplying eqn 4 by" + LCM(p1,p2)/p1 + "and eqn 5 by " + LCM(p1,p2)/p2 + ","+"\n"+ "we get" +"\n");
        display.append(LCM(p1,p2) + "y +"+ q1n + "z"+"="+ r1n +"\n");
        display.append(LCM(p1,p2) + "y +"+ q2n + "z"+"="+ r2n +"\n");
        display.append("\n"+"_________________________" +"\n");

        if (LCM(p1,p2)>=0)
            display.append("-    ");
        if (LCM(p1,p2)<0)
            display.append("+    ");
        if (q2n >= 0)
        {
            display.append("     -");
        }
        if (q2n < 0)
            display.append("     +");
        if(r2n>=0)
            display.append("     -");

        if(r2n<0)
            display.append("     +");
        display.append("\n"+"_________________________" +"\n");
        float q2nn=0,r2nn=0;
        if (q2n>=0)
        {
            q2nn=q1n-q2n;
            display.append("  0 +  "+ (q1n-q2n)+"z =" +"\n" );
        }
        if (q2n<0)
        {
            q2nn=q1n+Math.abs(q2n);
            display.append("  0 +  " + q2nn+"z =");
        }
        if(r2n>=0)
        {
            r2nn=r1n-r2n;
            display.append(r2nn+"\n");
        }
        if(r2n<0)
        {
            r2nn=r1n+Math.abs(r2n);
            display.append( r2nn+"\n");
        }
        float z= (r2nn/q2nn);
        display.append("z="+ (r2nn/q2nn) +"\n");
        display.append("now, putting the value of z in equation .." +"\n");
        display.append(p1 + "y +"+ q1 + "z"+"="+ r1 +"\n");
        display.append(p1 + "y +"+ q1 + "*"+z+"="+ r1 +"\n");
        display.append(p1 + "y +"+ q1*z+"="+ r1 +"\n");
        display.append(p1 + "y="+ r1+"+"+(-q1*z) +"\n");
        display.append(p1 + "y="+ (r1-q1*z) +"\n");
        display.append("y="+ (r1-q1*z)/p1 +"\n");
        display.append("\n");
        float y=(r1-q1*z)/p1;
        display.append("Putting the values of y and z in eqn ..." +"\n");
        display.append( a[1] + "x +" + b[1] + "y +"+  c[1] + "z " + "=" +d[1] +"\n" );
        display.append( a[1] + "x +" + b[1] +"*"+ y +"+"+  c[1]+"*" + z  + "=" +d[1]  +"\n");
        display.append( a[1] + "x +" + (b[1]*y)+"+"+  (c[1]*z)  + "=" +d[1]  +"\n");
        display.append( a[1] + "x +" + ((b[1]*y)+(c[1]*z) ) + "=" +d[1]  +"\n");
        display.append( a[1] + "x=" +(d[1]-((b[1]*y)+(c[1]*z) ))  +"\n");
        display.append( "x=" +((d[1]-((b[1]*y)+(c[1]*z) ))/a[1]) +"\n" );
        float x= ((d[1]-((b[1]*y)+(c[1]*z) ))/a[1]);
        display.append("\n");
        display.append("Hence, ");
        display.append("x="+ x +"\t");
        display.append("y="+ y +"\t");
        display.append("z="+ z +"\t");

        String ans=display.toString();
        display.setLength(0);
        return ans;

    }


    private static float GCD(float number1, float number2)
    { //base case
        if(number2 == 0)
        {
            return number1;
        }
        return GCD(number2, number1%number2);
    }

    private static float LCM(float number1, float number2)
    {
        return (number1*number2/GCD(number1,number2));
    }

}
