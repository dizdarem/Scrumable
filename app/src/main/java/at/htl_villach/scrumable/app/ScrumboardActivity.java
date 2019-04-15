package at.htl_villach.scrumable.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import at.htl_villach.scrumable.R;

public class ScrumboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrumboard);
        setTitle("Scrumboard");
        initControlls();
    }

    private void initControlls() {
    }
}
