package com.jnu.student;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView textView = new TextView(this);
        // 设置TextView的文本内容
        //textView.setText(R.id.text_vciew_hellow_world);
        // 将TextView设置为Activity的内容视图
        // 通过资源名称获取字符串资源的ID

        // 通过资源ID获取字符串值

        // 找到TextView

        // 设置文本内容

        //setContentView(textView);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            String tempText = textView1.getText().toString();
            textView1.setText(textView2.getText());
            textView2.setText(tempText);

            showToast();
            showAlertDialog();
        });
    }
    private void showToast() {
        Toast.makeText(this, "交换成功", Toast.LENGTH_SHORT).show();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("交换成功")
                .setPositiveButton("OK", null)
                .show();
    }
}