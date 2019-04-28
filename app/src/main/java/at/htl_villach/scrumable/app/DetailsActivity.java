package at.htl_villach.scrumable.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.bll.BacklogItem;

public class DetailsActivity extends AppCompatActivity {
    private TextView tv;
    private ImageView imageBtn;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tv = (TextView)findViewById(R.id.tv);
        imageBtn = (ImageView)findViewById(R.id.imageBtnBack);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        imageBtn.bringToFront();
        toolbar.invalidate();

        Intent intent = getIntent();
        BacklogItem backlogItem = intent.getParcelableExtra("selectedListItemObj");

        tv.setText(backlogItem.toString());

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsActivity.this.finish();
            }
        });
    }
}