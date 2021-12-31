package com.example.event;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.event.ui.home.Event;
import com.example.event.ui.home.HomeFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class PostEvent extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextInputLayout name,club,description,link;
    TextView event_on,posted_on,deadline;
    Button post;
    private Toolbar toolbar;
    Date posted_date,event_date,deadline_date;
    String club_name;
    DatePickerDialog.OnDateSetListener setListener_pd,setListener_ed,setListener_dl;
    FirebaseDatabase rootNode;
    DatabaseReference reference,newPostRef,postsRef;
    String[] Clubs = {"Cybersync","IEEE","Elecsol","Nirmaan","Robotics Club","Nrutya","Raaga","Reflexa","Fine Arts","Literature","Genesis","Sports","Cultural","FOSS","VCE LOT","Campus Radio","EWB","Books Club","Minds and Movements","Abhinaya","NSS","Dept.Of CSE","Dept.Of ECE","Dept.Of EEE","Dept.Of Civil","Dept.Of Mech","Dept.Of IT","ACM","Student Affairs","CIE","Others"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Clubs);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
         name=findViewById(R.id.name);
         club=findViewById(R.id.club);
        TextView posted_on=findViewById(R.id.posted_on);
        TextView evet_on=findViewById(R.id.event_on);
         description=findViewById(R.id.description);
         link=findViewById(R.id.link);
        TextView deadline=findViewById(R.id.deadline);
        Button post=findViewById(R.id.post);
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        posted_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(PostEvent.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener_pd,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
               datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });
        setListener_pd=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                calendar.set(year,month,dayOfMonth);
                posted_on.setText(simpleDateFormat.format(calendar.getTime()));
                posted_date=calendar.getTime();
            }
        };
        evet_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(PostEvent.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener_ed,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });
        setListener_ed=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                calendar.set(year,month,dayOfMonth);
                evet_on.setText(simpleDateFormat.format(calendar.getTime()));
                event_date=calendar.getTime();
            }
        };
        deadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(PostEvent.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener_dl,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
               datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });
        setListener_dl=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                calendar.set(year,month,dayOfMonth);
                deadline.setText(simpleDateFormat.format(calendar.getTime()));
                deadline_date=calendar.getTime();

            }
        };
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateClubName() & validateEventName() & validateDescription() & validateLink()) {
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference();
                    Event e = new Event(name.getEditText().getText().toString(), club.getEditText().getText().toString(), posted_date, event_date, deadline_date, description.getEditText().getText().toString(), link.getEditText().getText().toString());
                    postsRef = reference.child("Events");
                    newPostRef = postsRef.push();
                    newPostRef.setValue(e);
                    Intent i = new Intent(PostEvent.this, MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    return;
                }

            }
        });
    }
    public boolean validateEventName()
    {
        String val=name.getEditText().getText().toString().trim();
        if(val.isEmpty())
        {
            name.setError("field cannot be empty");
            return false;
        }
        else
        {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validateClubName()
    {
        String val=club.getEditText().getText().toString().trim();
        if(val.isEmpty())
        {
            club.setError("Field cannot be empty");
            return false;
        }
        else
        {
            club.setError(null);
            club.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validateLink()
    {
        String val=link.getEditText().getText().toString().trim();
        if(val.isEmpty())
        {
            link.setError("field cannot be empty");
            return false;
        }
        else
        {
            link.setError(null);
            link.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validateDescription()
    {
        String val=description.getEditText().getText().toString().trim();
        if(val.isEmpty())
        {
            description.setError("field cannot be empty");
            return false;
        }
        else
        {
            description.setError(null);
            description.setErrorEnabled(false);
            return true;
        }
    }
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

        club.getEditText().setText(Clubs[position]);
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}