package com.jnu.student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BookItemDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_item_details);

        Button buttonOk= findViewById(R.id.button_item_details_ok);

        buttonOk.setOnClickListener(v -> {
            Intent intent = new Intent();
            EditText editTextItemName= findViewById(R.id.edittext_item_name);

            intent.putExtra( "name" , editTextItemName.getText().toString());
            setResult(Activity.RESULT_OK, intent) ;

            BookItemDetailsActivity.this.finish();
        });
    }
}