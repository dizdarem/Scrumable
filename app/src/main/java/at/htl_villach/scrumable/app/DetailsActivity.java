package at.htl_villach.scrumable.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.bll.BacklogItem;

public class DetailsActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvEditor;
    private TextView tvStatus;
    private TextView tvDescription;
    private ImageView imageBtn;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initControls();
    }

    private void initControls() {
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvEditor = (TextView)findViewById(R.id.tvEditor);
        tvStatus = (TextView)findViewById(R.id.tvStatus);
        tvDescription = (TextView)findViewById(R.id.tvDescription);
        imageBtn = (ImageView)findViewById(R.id.imageBtnBack);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        Intent intent = getIntent();
        BacklogItem backlogItem = intent.getParcelableExtra("selectedListItemObj");

        tvTitle.setText(backlogItem.getTitle());
        tvEditor.setText(backlogItem.getEditor().toString());
        tvStatus.setText(backlogItem.getStatus().toString());
        tvDescription.setText(backlogItem.getDescribtion());

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsActivity.this.finish();
            }
        });
    }
}