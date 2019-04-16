package at.htl_villach.scrumable.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import at.htl_villach.scrumable.R;

public class StartActivity extends AppCompatActivity {

    private Button btnScrumActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initControlls();
    }

    private void initControlls() {
        //toDo: Build Register/Login
        btnScrumActivity = (Button) findViewById(R.id.btnScrumActivity);

        btnScrumActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, ScrumActivity.class));
            }
        });
    }
}
