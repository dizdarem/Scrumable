package at.htl_villach.scrumable.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.StatusEnum;

public class DetailsActivity extends AppCompatActivity {
    private EditText etTitle;
    private EditText etEditor;
    private EditText etStatus;
    private EditText etDescription;
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
        etTitle = (EditText)findViewById(R.id.etTitle);
        etEditor = (EditText)findViewById(R.id.etEditor);
        etStatus = (EditText)findViewById(R.id.etStatus);
        etDescription = (EditText)findViewById(R.id.etDescription);
        imageBtn = (ImageView)findViewById(R.id.imageBtnBack);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        BacklogItem backlogItem = intent.getParcelableExtra("selectedListItemObj");

        etTitle.setText(backlogItem.getTitle());
        etEditor.setText(backlogItem.getEditor().toString());
        etStatus.setText(backlogItem.getStatus().toString());
        etDescription.setText(backlogItem.getDescribtion());

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
            etTitle = (EditText) findViewById(R.id.etTitle);
            etEditor = (EditText) findViewById(R.id.etEditor);
            etStatus = (EditText) findViewById(R.id.etStatus);
            etDescription = (EditText) findViewById(R.id.etDescription);
            Button btnSave = (Button) findViewById(R.id.btnSave);
            btnSave.setEnabled(true);

            etTitle.setClickable(true);
            etTitle.setFocusable(true);
            etTitle.setFocusableInTouchMode(true);

            etEditor.setClickable(true);
            etEditor.setFocusable(true);
            etEditor.setFocusableInTouchMode(true);

            etStatus.setClickable(true);
            etStatus.setFocusable(true);
            etStatus.setFocusableInTouchMode(true);

            etDescription.setClickable(true);
            etDescription.setFocusable(true);
            etDescription.setFocusableInTouchMode(true);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(etStatus.getText().equals(StatusEnum.PRODUCT_BL)) {
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}