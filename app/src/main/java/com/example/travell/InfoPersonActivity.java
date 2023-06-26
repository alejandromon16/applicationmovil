package com.example.travell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InfoPersonActivity extends AppCompatActivity {

    private Button sendEmailButton;
    private EditText name;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_person);

        int totalPrice = getIntent().getIntExtra("price",0);

        name = findViewById(R.id.textView_name);
        email = findViewById(R.id.textView_email);

        sendEmailButton = findViewById(R.id.button_send_email);
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipientEmail = email.getText().toString();
                String subject = "Compra de Boletos";
                String messageText = "Su compra ha sido exitosa "+name.getText().toString()+ " por el valor de " + String.valueOf(totalPrice) + "Bs";

                EmailSender.sendEmail(recipientEmail, subject, messageText);

                Intent intent = new Intent(InfoPersonActivity.this , SuccesfulBookingActivity.class);
                startActivity(intent);

            }
        });
    }
}