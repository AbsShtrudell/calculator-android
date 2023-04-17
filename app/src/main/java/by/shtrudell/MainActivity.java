package by.shtrudell;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import by.shtrudell.expression.*;
import by.shtrudell.expression.Function;

public class MainActivity extends AppCompatActivity {

    private TextView expressionField;
    private TextView answerField;

    private CalcExpression expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expression = new CalcExpression();

        expressionField = findViewById(R.id.expressionText);
        answerField = findViewById(R.id.resultText);

        expressionField.setMovementMethod(new ScrollingMovementMethod());

        expressionField.setText("");
        answerField.setText("");

        initPrimitiveButton(R.id.button0, new Digit('0'));
        initPrimitiveButton(R.id.button1, new Digit('1'));
        initPrimitiveButton(R.id.button2, new Digit('2'));
        initPrimitiveButton(R.id.button3, new Digit('3'));
        initPrimitiveButton(R.id.button4, new Digit('4'));
        initPrimitiveButton(R.id.button5, new Digit('5'));
        initPrimitiveButton(R.id.button6, new Digit('6'));
        initPrimitiveButton(R.id.button7, new Digit('7'));
        initPrimitiveButton(R.id.button8, new Digit('8'));
        initPrimitiveButton(R.id.button9, new Digit('9'));
        initPrimitiveButton(R.id.button_brackets, new Bracket());
        initPrimitiveButton(R.id.button_dot, new Dot());
        initPrimitiveButton(R.id.button_div, Operator.DIVISION);
        initPrimitiveButton(R.id.button_mul, Operator.MULTIPLICATION);
        initPrimitiveButton(R.id.button_min, Operator.SUBSTRACTION);
        initPrimitiveButton(R.id.button_plus, Operator.ADDITION);
        initPrimitiveButton(R.id.button_pr, Operator.MODULE);
        initPrimitiveButton(R.id.button_sin, new Function("sin"));
        initPrimitiveButton(R.id.button_cos, new Function("cos"));

        initDeleteButton(R.id.button_del);
        initClearExpressionButton(R.id.button_ce);

    }

    private void expressionFieldChange(String text)
    {
        expressionField.setText(text);
        final int scrollAmount = expressionField.getLayout().getLineTop(expressionField.getLineCount()) - expressionField.getHeight();

        expressionField.scrollTo(0, Math.max(scrollAmount, 0));
    }

    protected void addDigit(Digit digit) {

    }

    protected void calculate(String expression) {
        double answer = new Expression(expression).calculate();
        answerField.setText(Double.isNaN(answer)? "": new DecimalFormat("#.##########").format(answer));
        //Toast.makeText(this, expression.toString(), Toast.LENGTH_SHORT).show();
    }

    private void initPrimitiveButton(int id, Primitive primitive) {
        Button button = findViewById(id);
        button.setOnClickListener(v -> {
            primitive.clone().format(expression.getFormater());
            expressionFieldChange(expression.viewStyle());
            calculate(expression.parseStyle());
        });
    }

    private void initDeleteButton(int id) {
        Button button = findViewById(id);
        button.setOnClickListener(v -> {
            expression.deleteLast();
            expressionFieldChange(expression.viewStyle());
            calculate(expression.parseStyle());
        });
    }

    private void initClearExpressionButton(int id) {
        Button button = findViewById(id);
        button.setOnClickListener(v -> {
            expression.clear();
            expressionFieldChange(expression.viewStyle());
            calculate(expression.parseStyle());
        });
    }

    private void initCalculateButton(int id) {
        Button button = findViewById(id);
        button.setOnClickListener(v -> {
            expression.clear();
            expressionFieldChange(expression.viewStyle());
            calculate(expression.parseStyle());
        });
    }
}