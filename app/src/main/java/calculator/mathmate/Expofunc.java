package calculator.mathmate;

/**
 * Created by deepesh on 2/5/17.
 */

public class Expofunc {
    static StringBuilder display = new StringBuilder(400);

    public static String expo(String que){

        int i,j=1,start=0;
        String func="["+que+"]";
        double val1,val2,val3,val=0,valsign=0,ans=0,x;
        String str1="",str2="",str3="",dummy,strwc="",question;
        String [] str= new String[20];

        question=func.substring(func.indexOf("[") + 1, func.indexOf("]"));
        str1= func.substring(func.indexOf("[") + 1, func.indexOf("^"));
        str2 = func.substring(func.indexOf("(") + 1, func.indexOf(")"));
        str3 = func.substring(func.indexOf("=") + 1, func.indexOf("]"));
        dummy= func.substring(func.indexOf("[") + 1, func.indexOf("="));

        val1=Math.log(Integer.parseInt(str1));
        val2=Math.log(Integer.parseInt(str3));
        val3=(val2/val1);

        String strx=str2+".";

        for (i = 0 ; i<strx.length() ; i ++)  //Converting all character values in its real data type
        {
            if ((strx.charAt(i)=='.'|| (strx.charAt(i)=='+'|| strx.charAt(i)=='-'))&&i!=0  )
            {
                str[j]=strx.substring(start,i);
                start=i;
                j++;
            }
        }

        if(str[1].indexOf("x")!=-1){
            strwc=str[1];
            ans=getCoeff(strwc);
        }
        else{
            valsign=Double.parseDouble(str[1]);
            val=val3-valsign;

        }
        if(str[2].indexOf("x")!=-1){
            strwc=str[2];
            ans=getCoeff(strwc);
        }
        else
        {
            valsign=Double.parseDouble(str[2]);
            val=val3-valsign;
        }

        x=val/ans;

        display.append(question + "\n");
        display.append("Taking log on both sides" + "\n");
        display.append("log("+dummy+")=log("+str3+")" + "\n");
        display.append("("+str2+")xlog("+str1+")=log("+str3+")" + "\n");
        display.append(str2+"=log("+str3+")/log("+str1+")" + "\n");
        display.append(str2+"=log("+str3+"/"+str1+")" + "\n");
        display.append(str2+"="+val3 + "\n");
        display.append(strwc+"="+val3+"-("+valsign+")" + "\n");
        display.append(strwc+"="+val + "\n");
        display.append("The value of x is " + x);

        String z=display.toString();
        display.setLength(0);
        return z;


    }


    public static double getCoeff(String str){
        String value="";

        for (int i = 0 ; i<str.length() ; i ++) {
            if ((str.charAt(i)=='x')&&i!=0  )
            {
                value=str.substring(0,i);
            }

        }
        String plus="+";
        String minus="-";
        if(value.equals(plus)){
            value="+1";
        }
        else if(value.equals(minus)){
            value="-1";
        }
        return Double.parseDouble(value);
    }
}

