package com.example.myapplication;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

public class CalculateHelper {
    public static double num1;
    public static double num2;
    public static double resultNumber;

    static double factorial(double n) {
        if (n == 1) {
            return 1;
        }
        return (n * factorial(n - 1));

    }

    private ArrayList splitTokens(String equation){
        String[] constant = equation.split(" ");
        ArrayList constantList = new ArrayList();
        double number=0;
        boolean flag= false;
        boolean save=false;
        String savedata=null;
        for(String data:constant){
            if (Objects.equals(data, " ")){
                continue;
            }
            if (save){
                if(checkNumber(data)){
                    number = number*10+Double.parseDouble(data);
                    flag=true;
                }
                if (savedata.equals("log")){
                    constantList.add(Math.log10(number));
                    number=0;
                    flag=false;
                } else if (savedata.equals("ln")){
                    constantList.add(Math.log(number));
                    number=0;
                    flag=false;
                }else if (savedata.equals("sin")){
                    constantList.add(Math.sin(Math.toRadians(number)));
                    number=0;
                    flag=false;
                }else if (savedata.equals("cos")){
                    constantList.add(Math.cos(Math.toRadians(number)));
                    number=0;
                    flag=false;
                }else if (savedata.equals("tan")){
                    constantList.add(Math.tan(Math.toRadians(number)));
                    number=0;
                    flag=false;
                }
                save=false;
            }
            else {
                if (checkNumber(data)) {
                    number = number * 10 + Double.parseDouble(data);
                    System.out.println(number);
                    flag = true;
                } else if (data.equals("π")) {
                    number = Math.PI;
                    flag = true;
                } else if (data.equals("e")) {
                    number = Math.E;
                    flag = true;
                } else if (data.equals("^2")) {
                    constantList.add(Math.pow(number, 2));
                    number = 0;
                    flag = false;
                } else if (data.equals("^3")) {
                    constantList.add(Math.pow(number, 3));
                    number = 0;
                    flag = false;
                } else if (data.equals("!")) {
                    constantList.add(factorial(number));
                    number = 0;
                    flag = false;
                }else if (data.equals("log")) {
                    savedata = data;
                    number = 0;
                    save = true;
                    flag = false;
                }else if (data.equals("ln")) {
                    savedata = data;
                    number = 0;
                    save = true;
                    flag = false;
                }else if (data.equals("sin")) {
                    savedata = data;
                    number = 0;
                    save = true;
                    flag = false;
                }else if (data.equals("cos")) {
                    savedata = data;
                    number = 0;
                    save = true;
                    flag = false;
                }
                else if (data.equals("tan")) {
                    savedata = data;
                    number = 0;
                    save = true;
                    flag = false;
                }
                else {
                    if (flag) {
                        constantList.add(number);
                        number = 0;
                    }
                    flag = false;
                    constantList.add(data);
                }
            }
        }
        if(flag){
            constantList.add(number);
        }

        return constantList;
    }

    private ArrayList infixToPostfix(ArrayList constant){
        ArrayList result= new ArrayList();
        HashMap level = new HashMap();
        Stack stack = new Stack();

        level.put("*",3);
        level.put("/",3);
        level.put("+",2);
        level.put("-",2);
        level.put("(",1);
        level.put("^",3);
        level.put("√",3);

        for(Object object : constant){
            if(object.equals("(")){
                stack.push(object);
            }else if(object.equals(")")){
                while(!stack.peek().equals("(")){
                    Object val = stack.pop();
                    if(!val.equals("(")){
                        result.add(val);
                    }
                }
                stack.pop();
            }else if(level.containsKey(object)){
                if(stack.isEmpty()){
                    stack.push(object);
                }else{
                    if(Double.parseDouble(level.get(stack.peek()).toString()) >= Double.parseDouble(level.get(object).toString())){
                        result.add(stack.pop());
                        stack.push(object);
                    }else{
                        stack.push(object);
                    }
                }
            }else{
                result.add(object);
            }
        }
        while(!stack.isEmpty()){
            result.add(stack.pop());
        }
        return result;
    }

    private Double postFixEval(ArrayList expr){
        Stack numberStack = new Stack();
        for(Object o : expr){
            if(o instanceof Double){
                numberStack.push(o);
            }else if(o.equals("+")){
                num1=(Double)numberStack.pop();
                num2=(Double)numberStack.pop();
                numberStack.push(num2+num1);
            }else if(o.equals("-")){
                num1=(Double)numberStack.pop();
                num2=(Double)numberStack.pop();
                numberStack.push(num2-num1);
            }else if(o.equals("*")){
                num1=(Double)numberStack.pop();
                num2=(Double)numberStack.pop();
                numberStack.push(num2*num1);
            }else if(o.equals("/")){
                num1=(Double)numberStack.pop();
                num2=(Double)numberStack.pop();
                numberStack.push(num2/num1);
            }else if(o.equals("^")){
                num1=(Double)numberStack.pop();
                num2=(Double)numberStack.pop();
                numberStack.push(Math.pow(num2,num1));
            }else if(o.equals("√")){
                num1=(Double)numberStack.pop();
                num2=(Double)numberStack.pop();
                numberStack.push(Math.pow(num1,1/num2));
            }
        }
        resultNumber = (Double)numberStack.pop();

        return resultNumber;
    }

    public Double process(String equation){
        ArrayList postfix = infixToPostfix(splitTokens(equation));
        Double result = postFixEval(postfix);
        return result;
    }

    public boolean checkNumber(String str){
        char check;

        if(str.equals("")){
            return false;
        }

        for(int i=0;i<str.length();i++){
            check=str.charAt(i);
            if(check<48||check>58){
                if(check!='.')
                    return false;
            }
        }

        return true;
    }

}