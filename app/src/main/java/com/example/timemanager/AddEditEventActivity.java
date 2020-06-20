package com.example.timemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class AddEditEventActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE =
            "com.example.timemanager.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.timemanager.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.timemanager.EXTRA_PRIORITY";
    public static final String EXTRA_NUM_OF_HOURS =
            "com.example.timemanager.EXTRA_NUM_OF_HOURS";
    public static final String EXTRA_DATE_INPUT =
            "com.example.timemanager.EXTRA_DATE_INPUT";
    public static final String EXTRA_TOTAL_TIME =
            "com.example.timemanager.EXTRA_TOTAL_TIME";


    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;
    private Spinner numOrHours;
    public EditText dueDateInput;
    private EditText totalTime;
    private RelativeLayout Card;
    DatePickerDialog picker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numOrHours = findViewById(R.id.min_or_hours_spinner);
        dueDateInput = findViewById(R.id.due_date_label);
        totalTime = findViewById(R.id.total_time);
        Card = findViewById(R.id.Card);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddEditEventActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.min_or_hour_array));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numOrHours.setAdapter(myAdapter);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);



        dueDateInput.setInputType(InputType.TYPE_NULL);
        dueDateInput.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddEditEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                dueDateInput.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }

                        }, year, month, day);
                picker.show();
            }
        });

    }



    public void saveEvent(){
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();
        String typeOfTime = numOrHours.getSelectedItem().toString();
        String dateDue = dueDateInput.getText().toString();
        String totalTimeTaken = totalTime.getText().toString();



        if (title.trim().isEmpty() || description.trim().isEmpty() || dateDue.trim().isEmpty() || totalTimeTaken.trim().isEmpty()){
            Toast.makeText(this, "Please Insert all Information before creating this Event", Toast.LENGTH_SHORT).show();
    return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);
        data.putExtra(EXTRA_NUM_OF_HOURS, typeOfTime);
        data.putExtra(EXTRA_DATE_INPUT, dateDue);
        data.putExtra(EXTRA_TOTAL_TIME, totalTimeTaken);


    setResult(RESULT_OK,data);
finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_event_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_event:
                saveEvent();
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }
}