package at.htl_villach.scrumable.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.bll.User;
import at.htl_villach.scrumable.dal.DatabaseManager;

public class AddBacklogItemActivity extends AppCompatActivity {
    private EditText etTitle;
    private Spinner cbEditor;
    private Spinner cbStatus;
    private EditText etDescription;
    private ImageView imageBtn;
    private Button btnSave;
    private Toolbar toolbar;

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_backlog_item);
        setTitle("");

        initControls();
    }

    private void initControls() {
        databaseManager = new DatabaseManager(AddBacklogItemActivity.this);
        databaseManager.open();
        etTitle = (EditText)findViewById(R.id.etTitleAdd);
        cbEditor = (Spinner)findViewById(R.id.cbEditorAdd);
        cbStatus = (Spinner)findViewById(R.id.cbStatusAdd);
        etDescription = (EditText)findViewById(R.id.etDescriptionAdd);
        imageBtn = (ImageView)findViewById(R.id.imageBtnBackAdd);
        toolbar = (Toolbar)findViewById(R.id.toolbarAdd);
        btnSave = (Button) findViewById(R.id.btnSaveAdd);
        setSupportActionBar(toolbar);
        fillComboboxEditor();
        fillComboboxStatus();

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(AddBacklogItemActivity.this, ScrumActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String title = etTitle.getText().toString();
               // final String edtior = cbEditor.getSelectedItem().toString();
                final String status = cbStatus.getSelectedItem().toString();
                final String description = etDescription.getText().toString();
                Intent main = new Intent(AddBacklogItemActivity.this, ScrumActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
            }
        });
    }

    private void fillComboboxEditor() {
        List<User> list = databaseManager.fetch_Users(null);
        cbEditor.setAdapter(new ArrayAdapter<User>(AddBacklogItemActivity.this, android.R.layout.simple_list_item_1, list));
    }

   private void fillComboboxStatus() {
        List<StatusEnum> list = new ArrayList<StatusEnum>();
        list.add(StatusEnum.PRODUCT_BL);
        list.add(StatusEnum.SPRINT_BL);
        list.add(StatusEnum.TODO);
        cbStatus.setAdapter(new ArrayAdapter<StatusEnum>(AddBacklogItemActivity.this, android.R.layout.simple_list_item_1, list));
    }
}

