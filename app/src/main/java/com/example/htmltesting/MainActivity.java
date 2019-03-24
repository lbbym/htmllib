package com.example.htmltesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.showCode.html.CodeView;
import com.showCode.html.CodeViewTheme;

public class MainActivity extends AppCompatActivity {

    CodeView codeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codeView = findViewById(R.id.codeView);
        codeView.setTheme(CodeViewTheme.DARKULA);
        codeView.displayCodeCss(Constant.HTML, ".code");
    }
}
