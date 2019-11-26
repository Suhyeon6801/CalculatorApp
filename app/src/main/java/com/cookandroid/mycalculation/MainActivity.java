package com.cookandroid.mycalculation;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

import android.app.Activity;
import android.icu.util.IslamicCalendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    int num = 0;
    String input="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.button1)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button2)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button3)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button4)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button5)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button6)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button7)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button8)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button9)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.button0)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonPoint)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonAdd)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonSub)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonMul)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonDiv)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonMod)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonSquare)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonSqrt)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonFraction)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonPower)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonFactorial)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonResult)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonCubic)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonPI)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonPar1)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonPar2)).setOnClickListener(on_Click);
        ((Button) findViewById(R.id.buttonChange)).setOnClickListener(on_Click);

        ((Button)findViewById(R.id.buttonClear1)).setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText et = ((EditText) findViewById(R.id.et_input));
                EditText et1 = (EditText)findViewById(R.id.et_output);
                et.setText(" ");
                et1.setText(" ");
            }
        });

        ((Button) findViewById(R.id.buttonClear2)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EditText et = ((EditText) findViewById(R.id.et_input));
                //EditText et1 = (EditText)findViewById(R.id.et_output);
                String s=et.getText().toString();
                s=s.substring(0,s.length()-1);
                et.setText(s);
            }
        });

        ((Button) findViewById(R.id.buttonErase)).setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText et = ((EditText) findViewById(R.id.et_input));
                //EditText et1 = (EditText)findViewById(R.id.et_output);
                String s=et.getText().toString();
                s=s.substring(0,s.length()-1);
                et.setText(s);
            }
        });

        ((Button)findViewById(R.id.buttonChange)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText et = ((EditText) findViewById(R.id.et_input));
                if(et.getText().toString().length()==0)
                {
                    Toast.makeText(MainActivity.this,"수를 입력하세요",Toast.LENGTH_SHORT).show();
                }
                //string을 가져와서 연산기호가 -이면 음수이므로 -1을 곱해서 양수로 바꿔줌
                else
                {
                    Long n=Long.parseLong(et.getText().toString());
                    n*=(-1);
                    et.setText(String.valueOf(n));
                }
                //연산기호가 없거나 +이면 음수로 바꿔준다.

            }
        });

        ((Button) findViewById(R.id.buttonResult)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = ((EditText) findViewById(R.id.et_input));
                EditText et1 = (EditText)findViewById(R.id.et_output);
                input = et.getText().toString();
                if(input.length()==0||input==null)
                {
                    Toast.makeText(MainActivity.this,"식을 입력하세요",Toast.LENGTH_SHORT).show();
                    //break;
                }
                else
                {
                    if(bracketsBalance(input)) {
                        postfix(input);
                        input = calc();
                        et1.setText(input);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"괄호의 짝이 맞지 않습니다.",Toast.LENGTH_SHORT).show();
                        //break;
                    }
                }
            }
        });
    }

    private View.OnClickListener on_Click = new View.OnClickListener() {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String id = null;
            String s;
            EditText et = ((EditText) findViewById(R.id.et_input));
            EditText et1 = (EditText)findViewById(R.id.et_output);
            // if(et.getText().toString().charAt(0) == '0') et.setText("");,

            switch(v.getId()){
                case R.id.button0 :
                    id = "0";
                    break;
                case R.id.button1 :
                    id = "1";
                    break;
                case R.id.button2 :
                    id = "2";
                    break;
                case R.id.button3 :
                    id = "3";
                    break;
                case R.id.button4 :
                    id = "4";
                    break;
                case R.id.button5 :
                    id = "5";
                    break;
                case R.id.button6 :
                    id = "6";
                    break;
                case R.id.button7 :
                    id = "7";
                    break;
                case R.id.button8 :
                    id = "8";
                    break;
                case R.id.button9 :
                    id = "9";
                    break;
                case R.id.buttonPoint:
                    if(et.getText().toString().length()==0) {
                        id="0.";
                    }
                    else {
                        id = ".";
                    }
                    break;
                case R.id.buttonAdd :
                    id = "+";
                    break;
                case R.id.buttonSub :
                    id = "-";
                    break;
                case R.id.buttonMul:
                    id = "*";
                    break;
                case R.id.buttonDiv :
                    id = "/";
                    break;
                case R.id.buttonMod:
                    id="%";
                    break;
                case R.id.buttonPower:
                    id="^";
                    break;
                case R.id.buttonPI:
                    id="π";
                    break;
                case R.id.buttonCubic:
                    id="³";
                    break;
                case R.id.buttonFactorial:
                    s = et.getText().toString();
                    s="("+s+")!";
                    et.setText(s);
                    return;
                case R.id.buttonSqrt:
                    //id="√";
                    s = et.getText().toString();
                    s="√("+s+")";
                    et.setText(s);
                    return;
                case R.id.buttonFraction:
                    s = et.getText().toString();
                    s="1/("+s+")";
                    et.setText(s);
                    return;
                case R.id.buttonSquare:
                    id="²";
                    break;
                case R.id.buttonPar1 :
                    id = "(";
                    break;
                case R.id.buttonPar2 :
                    id = ")";
                    break;
                default :
                    break;
            }

            s = et.getText() + id;
            et.setText(s);
        }
    };


    public Stack stack = new Stack();
    public Stack CalStack = new Stack();
    public ArrayList list = new ArrayList();

    public boolean isOperator(char ch)
    {
        return ( (ch =='+') || (ch =='-') || (ch=='*') || (ch=='/') || (ch=='%') ||(ch=='²') ||(ch=='√')||(ch=='^')||(ch=='!')
        ||(ch=='³')||(ch=='π'));
    }

    public int precedence(String op)
    {
        //연산자 우선순위 체크
        if(op.charAt(0) == '(') return 0;
        if(op.charAt(0)=='²'||op.charAt(0)=='√'||op.charAt(0)=='^'||op.charAt(0)=='!'||op.charAt(0)=='³'||op.charAt(0)=='π')   return 3;
        if((op.charAt(0) =='+') || (op.charAt(0) =='-')) return 1;
        if((op.charAt(0) =='*') || (op.charAt(0) =='/')||(op.charAt(0)=='%')) return 2;
        else return 4;
    }
    public int precedence(char op)
    {
        //연산자 우선순위 체크
        if(op == '(') return 0;
        if(op=='²'||op=='√'||op=='^'||op=='!'||op=='π'||op=='³') return 3;
        if((op =='+') || (op =='-')) return 1;
        if((op =='*') || (op =='/')||(op=='%')) return 2;
        else return 4;
    }

    public void postfix(String exp)
    {
        //변환 하기 전에 스택과 리스트를 비워 준다 .
        CalStack.clear();
        list.clear();
        //중위 표기법 후위 표기법으로 변환
        for(int i = 0; i<exp.length();i++)
        {
            char ch = exp.charAt(i);

            if(ch=='(')
            {
                // ( 라면 스택에 푸쉬
                String str = new String();
                str += ch;
                CalStack.push(str);
            }
            else if(ch == ')')
            {
                // ) 를 만나면 (가 나올때 까지 팝하고 (는 버림
                while(true)
                {
                    String dst = (String)CalStack.pop();
                    if(dst.charAt(0) != '(')
                    {
                        list.add(dst);
                    }
                    else break;
                }
            }
            else if(isOperator(ch))
            {//연산자 라면 우선순위를 체크해서 자신보다 높은 연산자 우선순위의 값은 팝
                while((!CalStack.empty()) && (precedence((String)CalStack.peek()) >= precedence(ch)))
                {
                    list.add(CalStack.pop());
                }
                String str = new String();
                str += ch; //그리고 자기 자신을 푸쉬 하여 준다.
                CalStack.push(str);
            }
            else if((ch >= '0' && ch <= '9') || ch=='.')
            {//피연산자라면 ....
                String str = new String();
                do
                {//숫자가 연속해서 있을 수도 있기 때문에....
                    str += ch;
                    i++;
                    if(i < exp.length())
                    {
                        ch = exp.charAt(i);
                    }
                    else break;
                }while((ch >= '0' && ch <= '9') || ch=='.');

                list.add(str);
                i--;
            }
        }
        while(!CalStack.empty())
        {
            String str = new String();
            str = (String)CalStack.pop();
            list.add(str);
        }
    }

    public String calc()
    {
        CalStack.clear();
        double l;
        String Error="0으로 나눌 수 없습니다.";
        while(!list.isEmpty())
        {
            String str = (String)list.get(0);
            list.remove(0);

            if((str.charAt(0) >= '0') && (str.charAt(0) <= '9'))
            {//피연산자라면 푸쉬
                CalStack.push(str);
            }
            else if(str.charAt(0)=='²')
            {
                //String dst = (String)CalStack.pop();
                String src = (String)CalStack.pop();
                l = Double.parseDouble(src) * Double.parseDouble(src);
                String buf = String.valueOf(l);
                CalStack.push(buf);
            }
            else if(str.charAt(0)=='!')
            {
                String src = (String)CalStack.pop();
                int Fact=Integer.parseInt(src);
                int getResult=1;
                for(int i=Fact; i>=1; i--)
                {
                    getResult*=i;
                }
                String buf=String.valueOf(getResult);
                CalStack.push(buf);
            }
            else if(str.charAt(0) == '³')
            {
                //String dst = (String)CalStack.pop();
                String src = (String)CalStack.pop();
                l = Double.parseDouble(src)*Double.parseDouble(src)*Double.parseDouble(src);
                String buf = String.valueOf(l);
                CalStack.push(buf);

            }
            else if(str.charAt(0) == 'π')
            {
                //String dst = (String)CalStack.pop();
                String src = (String)CalStack.pop();
                l = Double.parseDouble(src)*3.14;
                String buf = String.valueOf(l);
                CalStack.push(buf);
            }
            else if(str.charAt(0) == '√')
            {
                //String dst = (String)CalStack.pop();
                String src = (String)CalStack.pop();
                l = Math.sqrt(Double.parseDouble(src));
                String buf = String.valueOf(l);
                CalStack.push(buf);
            }
            else if(str.charAt(0)=='^')
            {
                String dst = (String)CalStack.pop();
                String src = (String)CalStack.pop();
                l = Math.pow(Double.parseDouble(src) , Double.parseDouble(dst));
                String buf = String.valueOf(l);
                CalStack.push(buf);
            }
            else if(str.charAt(0) == '*')
            {//연산자 이면 팝을 두번 해서 그결과를 푸쉬
                String dst = (String)CalStack.pop();
                String src = (String)CalStack.pop();
                l = Double.parseDouble(dst) * Double.parseDouble(src);
                String buf = String.valueOf(l);
                CalStack.push(buf);
            }
            else if(str.charAt(0) == '+')
            {
                String dst = (String)CalStack.pop();
                String src = (String)CalStack.pop();
                l = Double.parseDouble(dst) + Double.parseDouble(src);
                String buf = String.valueOf(l);
                CalStack.push(buf);
            }
            else if(str.charAt(0) == '/')
            {// / 나 - 일 경우에는 자리가 변경 되면 안되므로 자리에 맞춰서 계산 하여 준다
                String dst = (String)CalStack.pop();
                String src = (String)CalStack.pop();

                if(dst.equals("0")||dst.equals("0.0"))
                {
                    Toast.makeText(MainActivity.this,"0으로 나눌 수 없습니다.",Toast.LENGTH_SHORT).show();
                    return Error;
                }
                else {
                    l = Double.parseDouble(src) / Double.parseDouble(dst);
                    String buf = String.valueOf(l);
                    CalStack.push(buf);
                }
            }
            else if(str.charAt(0) == '-')
            {
                String dst = (String)CalStack.pop();
                String src = (String)CalStack.pop();
                l = Double.parseDouble(src) - Double.parseDouble(dst);
                String buf = String.valueOf(l);
                CalStack.push(buf);
            }
            else if(str.charAt(0)=='%')
            {
                String dst = (String)CalStack.pop();
                String src = (String)CalStack.pop();
                l = Double.parseDouble(dst) % Double.parseDouble(src);
                String buf = String.valueOf(l);
                CalStack.push(buf);
            }
        }
        return (String)CalStack.pop();
    }

    public boolean bracketsBalance(String exp)
    {//괄호의 수식이 맞는지 체크
        //스택을 비원줌
        stack.clear();
        for(int i = 0; i<exp.length();i++)
        {
            char ch= exp.charAt(i);
            //시작 괄호 이면 스택에 넣음
            if(ch=='[' || ch == '(')
            {
                stack.push(new Character(ch));
            }
            else if(ch == ']' || ch == ')')
            {
                //파싱중 우측 괄호를 봤는데 스택이 비어 있다면 오류
                if(stack.isEmpty()) return false;
                //우선 스택에서 하나를 꺼낸다.
                char charFromStack = ((Character)stack.pop()).charValue();
                //파싱한 것이 '('인데 스택에서 꺼낸것이 ')'아니거나,
                //파싱한 것이 '['인데 스택에서 꺼낸것이 ']'아닌경우
                if(ch==']' &&charFromStack !='['||(ch==')'&&charFromStack!='('))return false;

            }//end if
        }//end for loop
        return stack.isEmpty(); //empty means matched, else unmatched
    }

    public boolean Lexical()
    {//수식이 맞는지 안 맞는지 판단...
        //기존의 후위 연산 변경 리스트에서 가져와서 수식이 맞는지 체크 한다
        int f = 0;
        for(int i = 0; i < list.size();i++)
        {
            String exp = (String) list.get(i);
            char ch= exp.charAt(0);
            while(ch == ')' || ch == '(')
            {//괄호는 무시
                i++;
                exp = (String) list.get(i);
                ch = exp.charAt(0);
            }
            if(isOperator(ch)) f--;
            else
            {
                f++;
                ch= exp.charAt(0);
            }
            if(f<1) break; //f가 1보다 작으면 언더 플로
        }
        return f==1;
    }
}


