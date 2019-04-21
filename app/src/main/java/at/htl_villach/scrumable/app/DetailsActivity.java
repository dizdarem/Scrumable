package at.htl_villach.scrumable.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.bll.BacklogItem;

public class DetailsActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tv = (TextView)findViewById(R.id.tv);

        Intent intent = getIntent();
        BacklogItem backlogItem = intent.getParcelableExtra("selectedListItemObj");

        tv.setText(backlogItem.toString());
    }
}