package com.example.dashan.codefundo;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class contacts extends AppCompatActivity {
    private Button back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        TextView textView = (TextView) findViewById(R.id.hi);

        String s= "Having excess food after your functions/parties ? Call us at 9884029950 and we will arrange to collect the food and deliver it directly to orphanage or poor children homes .\n" +
                "Cafe owners in watscooking.com can opt to donate Rs 1 or equivalent for every dish that is ordered which can be used for excess food collection and delivery\n" +
                "Users can donate Rs 50 or equivalent while ordering in watscooking.com which can be used for food collection and delivery to the poor children";

        textView.setText(s);
        back_btn=(Button) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ProfileUser.class);
                startActivity(i);
            }
        });

    }
}
