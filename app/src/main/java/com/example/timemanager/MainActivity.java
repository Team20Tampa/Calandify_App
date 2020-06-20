package com.example.timemanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final int ADD_EVENT_REQUEST = 1;
    private EventViewModel eventViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditEventActivity.class);
                startActivityForResult(intent, ADD_EVENT_REQUEST);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final EventAdapter adapter = new EventAdapter();
        recyclerView.setAdapter(adapter);
        eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        eventViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                adapter.setEvents(events);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
       ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder alert1 = new AlertDialog.Builder(MainActivity.this);
                alert1.setCancelable(true);
                alert1.setTitle("Warning");
                alert1.setMessage("Are you sure that you want to delete this Event?");

                alert1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                alert1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        eventViewModel.delete(adapter.getEventAt(viewHolder.getAdapterPosition()));
                           Toast.makeText(MainActivity.this, "Event Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                alert1.show();

            }
        }).attachToRecyclerView(recyclerView);

    }
    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == ADD_EVENT_REQUEST && resultCode == RESULT_OK) {
//                String title = data.getStringExtra(AddEditEventActivity.EXTRA_TITLE);
//                String description = data.getStringExtra(AddEditEventActivity.EXTRA_DESCRIPTION);
//                int priority = data.getIntExtra(AddEditEventActivity.EXTRA_PRIORITY, 1);
//                String dueDate = data.getStringExtra(AddEditEventActivity.EXTRA_DATE_INPUT);
//                String time = data.getStringExtra(AddEditEventActivity.EXTRA_TOTAL_TIME);
//                String numOrHours = data.getStringExtra(AddEditEventActivity.EXTRA_NUM_OF_HOURS);
//
//
//            Event event = new Event(title,description,dueDate,numOrHours,time,priority);
//            eventViewModel.insert(event);
//
//
//            Toast.makeText(this, "Event saved", Toast.LENGTH_SHORT).show();
//        }
//            String title = data.getStringExtra(AddEditEventActivity.EXTRA_TITLE);
//            String description = data.getStringExtra(AddEditEventActivity.EXTRA_DESCRIPTION);
//            int priority = data.getIntExtra(AddEditEventActivity.EXTRA_PRIORITY, 1);
//            String dueDate = data.getStringExtra(AddEditEventActivity.EXTRA_DATE_INPUT);
//            String time = data.getStringExtra(AddEditEventActivity.EXTRA_TOTAL_TIME);
//            String numOrHours = data.getStringExtra(AddEditEventActivity.EXTRA_NUM_OF_HOURS);
//
//            Event event = new Event(title,description,dueDate,numOrHours,time,priority);
//            event.setId(id);
//            eventViewModel.update(event);
//
//            Toast.makeText(this, "Event Updated", Toast.LENGTH_SHORT).show();
//
//        }
//            else {
//            Toast.makeText(this, "Event not saved", Toast.LENGTH_SHORT).show();
//        }
//    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_EVENT_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditEventActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditEventActivity.EXTRA_DESCRIPTION);
            String dueDate = data.getStringExtra(AddEditEventActivity.EXTRA_DATE_INPUT);
            String time = data.getStringExtra(AddEditEventActivity.EXTRA_TOTAL_TIME);
            String numOrHours = data.getStringExtra(AddEditEventActivity.EXTRA_NUM_OF_HOURS);
            int priority = data.getIntExtra(AddEditEventActivity.EXTRA_PRIORITY, 1);
            Event event = new Event(title, description,dueDate,numOrHours,time,priority);
            eventViewModel.insert(event);
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        final AlertDialog.Builder alert2 = new AlertDialog.Builder(MainActivity.this);
        alert2.setCancelable(true);
        alert2.setTitle("Warning");
        alert2.setMessage("Are you sure you want to Delete all your events?");

        alert2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alert2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (item.getItemId()){
                    case(R.id.delete_all_events):
                        eventViewModel.deleteAllEvents();
                        Toast.makeText(MainActivity.this, "All Events Deleted", Toast.LENGTH_SHORT).show();
                }


            }
        });
        alert2.show();

        return super.onOptionsItemSelected(item);
    }
}