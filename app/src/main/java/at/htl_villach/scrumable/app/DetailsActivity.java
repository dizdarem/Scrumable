package at.htl_villach.scrumable.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        setTitle("");

        initControls();
    }

    private void initControls() {
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvEditor = (TextView)findViewById(R.id.tvEditor);
        tvStatus = (TextView)findViewById(R.id.tvStatus);
        tvDescription = (TextView)findViewById(R.id.tvDescription);
        imageBtn = (ImageView)findViewById(R.id.imageBtnBack);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_edituserstory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mitem_EditUserStory) {
            tvTitle = (TextView)findViewById(R.id.tvTitle);
            tvEditor = (TextView)findViewById(R.id.tvEditor);
            tvStatus = (TextView)findViewById(R.id.tvStatus);
            tvDescription = (TextView)findViewById(R.id.tvDescription);
            tvEditor.setEnabled(true);
            Toast.makeText(DetailsActivity.this,"Action clicked",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}