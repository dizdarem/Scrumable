package at.htl_villach.scrumable.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import at.htl_villach.scrumable.R;

public class ProductBacklogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_backlog);
        setTitle("Product Backlog");
        initControlls();
    }

    private void initControlls() {
    }
}
