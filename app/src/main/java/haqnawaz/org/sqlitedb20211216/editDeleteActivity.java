package haqnawaz.org.sqlitedb20211216;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class editDeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);
        String extraText = getIntent().getExtras().toString();
        TextView textView = findViewById(R.id.textView);
        textView.setText(extraText);
    }
}