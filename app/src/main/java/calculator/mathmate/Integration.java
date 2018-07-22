package calculator.mathmate;

public class Integration {
    static int input1 = 0,input2=0 ,input3=1,log=0;
    static char variable = ' ' ;
    static int multi = 1,index = 0 , i=0, ang=1,sign=1,exp=1 ;
    static int power = 0,flag=0 ;
    static String displ="";
    static int begin=0, end=0;
    static String pow="",trigo="",inttrigo="",angle="",expvalue="",intexp="",intpow="",inp3="";;
    public static void calcint (String input)
    {

        for (index = 0 ; index < input.length() ; index++)  //Converting all character values in its real data type
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
                case '/':
                    for(int i=index+1;input.charAt(i)!='x';i++)
                    {
                        if(input.charAt(i)=='('||input.charAt(i)==')')
                            continue;
                        inp3=inp3.concat(input.substring(i,i+1));
                    }
                    if(!inp3.equals(""))
                        input3=Integer.valueOf(inp3);
                    log=1;
                    index=input.length()-1;
                    break;
                case '^' :
                    for(i=index+1;i!=input.length();i++)
                    {
                        if(input.charAt(i)=='('||input.charAt(i)==')')
                            continue;
                        if(input.charAt(i)=='x')
                        {
                            flag=1;
                            break;
                        }
                        pow=pow.concat(input.substring(i,i+1));


                    }
                    if (flag==1)
                    {
                        input2=input1;
                        input1=0;
                        intpow=input2+"^("+pow+"x)";
                        index=input.length()-2;
                        if(power==0)
                            power=1;
                    }
                    if(flag!=1)
                    {
                        power=Integer.valueOf(pow);

                        index=input.length()-1;
                    }
                    break;
                case 'e':
                    for(int i=index+2;input.charAt(i)!='x';i++)
                    {
                        if(input.charAt(i)=='('||input.charAt(i)==')')
                            continue;
                        expvalue=expvalue.concat(input.substring(i,i+1));
                    }
                    if(!expvalue.equals(""))
                        exp=Integer.valueOf(expvalue);
                    intexp="e^("+exp+"x)";
                    if(input1==0)
                        input1=1;
                    index=input.length()-2;
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
                        inttrigo="cos"+angle+"x";
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
                        if(input.contains("^"))
                            inttrigo="tan"+angle+"x";
                        else if(input.contains("tan"))
                        {
                            inttrigo="sec"+angle+"x";
                            index=input.length()-1;
                        }
                        else
                            inttrigo="ln|sec"+angle+"x+tan"+angle+"x|";
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
                        if(input.contains("^"))
                        {
                            sign=-1;
                            inttrigo="cot"+angle+"x";
                        }
                        else if(input.contains("cot"))
                        {
                            inttrigo="cosec"+angle+"x";
                            index=input.length()-1;
                            sign=-1;
                        }
                        else
                            inttrigo="ln|cosec"+angle+"x-cot"+angle+"x|";
                        index=input.indexOf('x',index);
                        if(!angle.equals(""))
                            ang=Integer.valueOf(angle);


                    }
                    if(trigo.equals("cos"))
                    {
                        for(int i=index+3;input.charAt(i)!='x';i++)
                        {
                            if(input.charAt(i)=='(')
                                continue;
                            angle=angle.concat(input.substring(i,i+1));
                        }
                        inttrigo="sin"+angle+"x";
                        index=input.indexOf('x',index);
                        if(!angle.equals(""))
                            ang=Integer.valueOf(angle);

                    }
                    else if(trigo.equals("cot"))
                    {
                        for(int i=index+3;input.charAt(i)!='x';i++)
                        {
                            if(input.charAt(i)=='(')
                                continue;
                            angle=angle.concat(input.substring(i,i+1));
                        }
                        if(input.contains("cosec"))
                        {
                            inttrigo="cosec"+angle+"x";
                            index=input.length()-1;
                            sign=-1;
                        }
                        else
                            inttrigo="(ln|sin"+angle+"x|)";
                        index=input.indexOf('x',index);
                        if(!angle.equals(""))
                            ang=Integer.valueOf(angle);

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
                        if(input.contains("sec"))
                        {
                            inttrigo="sec"+angle+"x";
                            index=input.length()-1;
                        }
                        else
                            inttrigo="(ln|cos"+angle+"x|)";

                        index=input.indexOf('x',index);
                        if(!angle.equals(""))
                            ang=Integer.valueOf(angle);
                        sign=-1;
                    }
                    break;
                default :
                    break ;
            }
            if (index==0)
            {
                multi*=10;
            }

        }
        if (input.charAt(0)=='-')
            input1=-input1;

        if(inttrigo.equals("")&&intexp.equals("")&&flag!=1&&log!=1)
        {

            if (variable == ' ')	//i
                displ=displ.concat(Integer.toString(input1)+"x") ;
            else if (input1 != 0  && variable != ' ' && power == 0)		//ii
                displ=displ.concat("("+Integer.toString(input1)+"/2)"+(variable)+"^2");

            else if (input1 == 0 && variable != ' ' && power == 0 && input.charAt(0)=='-')		//iii
                displ=displ.concat("(-1/2)"+(variable)+"^2") ;
            else if (input1 == 0 && variable != ' ' && power == 0)		//iv
                displ=displ.concat("(1/2)"+(variable)+"^2") ;
            else if (variable !=' ' && power == -1 && input.charAt(0)=='-')		//v
            {
                if (input1 == 0)
                    displ=displ.concat("-logx");
                else
                    displ=displ.concat(Integer.toString(input1)+"logx");
            }
            else if (variable !=' ' && power == -1 )		//vi
            {
                if (input1 == 0)
                    displ=displ.concat("logx");
                else
                    displ=displ.concat(Integer.toString(input1)+"logx");
            }
            else if (input1 == 0 && variable != ' ' && power < -1 && input.charAt(0)=='-')		//vii
            {
                power= -(power+1);
                displ=displ.concat("(-1/"+Integer.toString(power)+")"+(variable)+"^"+Integer.toString(power)) ;
            }
            else if (input1 == 0 && variable != ' ' && power > 0 && input.charAt(0)=='-')		//viii
                displ=displ.concat("(-1/"+Integer.toString(power+1)+")"+(variable)+"^"+Integer.toString(power+1)) ;
            else if (input1 == 0 && variable != ' ' && power != 0 )			//ix
                displ=displ.concat("(1/"+Integer.toString(power+1)+")"+(variable)+"^"+Integer.toString(power+1)) ;

            else if (input1 != 0 && variable != ' ' && power != 0)
            {
                if (input1<0 && power<0)		//x
                    displ=displ.concat("("+Integer.toString(input1)+"/"+Integer.toString((power+1))+")"+(variable)+"^"+Integer.toString(power+1)) ;
                else if (input1>0 && power>0)		//xi
                    displ=displ.concat("("+Integer.toString(input1)+"/"+Integer.toString(power+1)+")"+(variable)+"^"+Integer.toString(power+1)) ;
                else if (input1<0 &&power>0)		//xii
                    displ=displ.concat("("+Integer.toString(input1)+"/"+Integer.toString(power+1)+")"+(variable)+"^"+Integer.toString(power+1)) ;
                else if (input1>0 && power<0)		//xiii
                    displ=displ.concat("(-"+Integer.toString(input1)+"/"+Integer.toString(-(power+1))+")"+(variable)+"^"+Integer.toString(power+1)) ;

            }
        }
        else if(!inttrigo.equals(""))
        {

            if (input1 != 0  )		//xiv
            {
                displ=displ.concat("("+sign*input1+"/"+ang+")"+inttrigo);
                //displ=displ.concat((sign*((float)(input1)/ang))+inttrigo);
            }
            else if (input1 == 0   && input.charAt(0)=='-')
            {
                displ=displ.concat("("+-sign+"/"+ang+")"+inttrigo) ;
                //displ=displ.concat(-sign/(float)ang+inttrigo) ;
            }
            else if (input1 == 0 )
            {
                displ=displ.concat("("+sign+"/"+ang+")"+inttrigo) ;
                //displ=displ.concat(sign/(float)ang+inttrigo) ;
            }


        }
        else if(!intexp.equals(""))
            displ=displ.concat("("+input1+"/"+exp+")"+intexp);
        else if(flag==1)
            displ=displ.concat("(1/("+power+"log"+input2+"))."+"("+intpow+")");
        else if(log==1)
            displ=displ.concat("("+input1+"/"+input3+")logx");
        input1=0;
        power=0;
        pow="";
        ang=1;
        input2=0;
        multi=1;
        variable=' ';
        inttrigo="";
        angle="";
        sign=1;
        expvalue="";
        exp=1;
        intexp="";
        intpow="";
        log=0;
        inp3="";
        input3=1;
        flag=0;
    }
    public static void main(String[] args) {
        integrater("4x");
    }



    public static String integrater(String func){

        @SuppressWarnings("resource")

        String input0=func;         // Get input from user
        String input01="";
        for (int i = 0 ; i < input0.length() ; i ++)  //Converting all character values in its real data type

        {
            if (input0.charAt(i)=='+')
            {

                end=input0.indexOf('+',end+1);
                input01=input0.substring(begin,end);
                calcint(input01);
                displ=displ.concat("+");
                begin=end+1;

            }
            else if(input0.charAt(i)=='-'&& i!=0 &&input0.charAt(i-1)!='^'&& input0.charAt(i-1)!='(')
            {

                end=input0.indexOf('-',end+1);
                input01=input0.substring(begin,end);
                calcint(input01);
                displ=displ.concat("-");
                begin=end+1;


            }

        }
        input01=input0.substring(begin,input0.length());
        calcint(input01);
        begin=0;
        end=0;
        displ=displ.concat("+c");
        String zz=displ.toString();
        displ="";
        return zz;



    }

}