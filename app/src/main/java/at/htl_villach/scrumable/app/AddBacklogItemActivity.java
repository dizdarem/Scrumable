package at.htl_villach.scrumable.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import at.htl_villach.scrumable.R;

public class AddBacklogItemActivity extends AppCompatActivity {
    private ImageView imageBtn;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_backlog_item);
        setTitle("");

        initControls();
    }

    private void initControls() {

    }
}
