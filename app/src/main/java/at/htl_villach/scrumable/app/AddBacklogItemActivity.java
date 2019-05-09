package at.htl_villach.scrumable.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.bll.User;

public class AddBacklogItemActivity extends AppCompatActivity {
    private EditText etTitle;
    private Spinner cbEditor;
    private Spinner cbStatus;
    private EditText etDescription;
    private ImageView imageBtn;
    private Button btnSave;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_backlog_item);
        setTitle("");

        initControls();
    }
    private void initControls() {
        etTitle = (EditText)findViewById(R.id.etTitleAdd);
        cbEditor = (Spinner)findViewById(R.id.cbEditorAdd);
        cbStatus = (Spinner)findViewById(R.id.cbStatusAdd);
        etDescription = (EditText)findViewById(R.id.etDescriptionAdd);
        imageBtn = (ImageView)findViewById(R.id.imageBtnBackAdd);
        toolbar = (Toolbar)findViewById(R.id.toolbarAdd);
        btnSave = (Button) findViewById(R.id.btnSaveAdd);
        setSupportActionBar(toolbar);
        fillComboboxEditor();

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
        List<User> list = new ArrayList<>();
        list.add(new User("User_2", "test", new Date()));
        //ToDo: Get Users form Database

        cbEditor.setAdapter(new ArrayAdapter<User>(AddBacklogItemActivity.this, android.R.layout.simple_list_item_1, list));
    }

   /* private void fillComboboxStatus() {
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

        cbStatus.setAdapter(new ArrayAdapter<StatusEnum>(AddBacklogItemActivity.this, android.R.layout.simple_list_item_1, list));
    }*/
}

