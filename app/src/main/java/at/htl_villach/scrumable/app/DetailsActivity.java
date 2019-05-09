package at.htl_villach.scrumable.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.bll.User;

public class DetailsActivity extends AppCompatActivity {
    private EditText etTitle;
    private Spinner cbEditor;
    private Spinner cbStatus;
    private EditText etDescription;
    private ImageView imageBtn;
    private Toolbar toolbar;
    private BacklogItem backlogItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle("");

        initControls();
    }

    private void initControls() {
        etTitle = (EditText)findViewById(R.id.etTitle);
        cbEditor = (Spinner)findViewById(R.id.cbEditor);
        cbStatus = (Spinner) findViewById(R.id.cbStatus);
        etDescription = (EditText)findViewById(R.id.etDescription);
        imageBtn = (ImageView)findViewById(R.id.imageBtnBack);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        backlogItem = intent.getParcelableExtra("selectedListItemObj");

        etTitle.setText(backlogItem.getTitle());
        fillComboboxEditor();
        fillComboboxStatus();

        cbEditor.setEnabled(false);
        cbStatus.setEnabled(false);

        etDescription.setText(backlogItem.getDescribtion());

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsActivity.this.finish();
            }
        });
    }

    private void fillComboboxEditor() {
        List<User> list = new ArrayList<>();
        User curEditor = backlogItem.getEditor();
        list.add(curEditor);
        list.add(new User("User_2", "test", new Date()));
        //ToDo: Get Users form Database

        cbEditor.setAdapter(new ArrayAdapter<User>(DetailsActivity.this, android.R.layout.simple_list_item_1, list));
    }

    private void fillComboboxStatus() {
        List<StatusEnum> list = new ArrayList<StatusEnum>();
        StatusEnum curStatus = backlogItem.getStatus();
        list.add(curStatus);
        switch (curStatus) {
            case PRODUCT_BL:
                list.add(StatusEnum.SPRINT_BL);
                list.add(StatusEnum.DONE);
                break;
            case SPRINT_BL:
                list.add(StatusEnum.PRODUCT_BL);
                list.add(StatusEnum.DONE);
                break;
            case TODO:
                list.add(StatusEnum.PRODUCT_BL);
                list.add(StatusEnum.SPRINT_BL);
                list.add(StatusEnum.IN_PROCESS);
                break;
            case IN_PROCESS:
                list.add(StatusEnum.PRODUCT_BL);
                list.add(StatusEnum.SPRINT_BL);
                list.add(StatusEnum.TODO);
                list.add(StatusEnum.TESTING);
                break;
            case TESTING:
                list.add(StatusEnum.PRODUCT_BL);
                list.add(StatusEnum.SPRINT_BL);
                list.add(StatusEnum.IN_PROCESS);
                list.add(StatusEnum.DONE);
                break;
            case DONE:
                list.add(StatusEnum.PRODUCT_BL);
                list.add(StatusEnum.SPRINT_BL);
                list.add(StatusEnum.TESTING);
                break;
                default:
                    break;
        }

        cbStatus.setAdapter(new ArrayAdapter<StatusEnum>(DetailsActivity.this, android.R.layout.simple_list_item_1, list));
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
            cbEditor = (Spinner) findViewById(R.id.cbEditor);
            cbStatus = (Spinner) findViewById(R.id.cbStatus);
            etDescription = (EditText) findViewById(R.id.etDescription);
            Button btnSave = (Button) findViewById(R.id.btnSave);
            btnSave.setEnabled(true);

            etTitle.setClickable(true);
            etTitle.setFocusable(true);
            etTitle.setFocusableInTouchMode(true);

            cbStatus.setEnabled(true);
            cbEditor.setEnabled(true);

            etDescription.setClickable(true);
            etDescription.setFocusable(true);
            etDescription.setFocusableInTouchMode(true);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DetailsActivity.this, ScrumActivity.class);
                    BacklogItem editedbacklogItem = new BacklogItem(backlogItem.getId(), etTitle.getText().toString(), etDescription.getText().toString(), (StatusEnum) cbStatus.getSelectedItem(), (User) cbEditor.getSelectedItem());
                    intent.putExtra("selectedListItemObj", editedbacklogItem);
                    startActivity(intent);
                    Toast.makeText(DetailsActivity.this, "Successful Update", Toast.LENGTH_LONG).show();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}