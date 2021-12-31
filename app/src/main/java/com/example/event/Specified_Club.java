package com.example.event;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.event.databinding.ActivitySpecifiedClubBinding;
import com.example.event.ui.home.Event;
import com.example.event.ui.home.ListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class Specified_Club extends AppCompatActivity {
    ActivitySpecifiedClubBinding binding;
    FirebaseDatabase rootNode;
    private Toolbar toolbar;
    DatabaseReference reference,newPostRef,postsRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySpecifiedClubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Intent intent = this.getIntent();
        String club=intent.getStringExtra("club");
        ArrayList<Event> coursesArrayList = new ArrayList<>();
        ArrayList<Event> reversed = new ArrayList<>();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        postsRef = reference.child("Events");
        ListAdapter adapter=new ListAdapter(getApplicationContext(),reversed);
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year,month,day,0,0,0);
        Date present=calendar.getTime();
        long pts=present.getTime();
        calendar.set(year,month,day,23,59,59);
        Date future=calendar.getTime();
        long fut=future.getTime();
        postsRef.orderByChild("posted_by").equalTo(club).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String prevChildKey) {
                Event e=snapshot.getValue(Event.class);
                String key=snapshot.getKey();
                e.setKey(key);

                    if (e.getEvent_on().getTime() < pts)
                        e.setStatus("Completed");
                    else if (e.getEvent_on().getTime() > fut)
                        e.setStatus("upComing");
                    else if (e.getEvent_on().getTime() < fut && e.getEvent_on().getTime() > pts)
                        e.setStatus("onGoing");
                    coursesArrayList.add(e);
                    reversed.clear();
                    reversed.addAll(coursesArrayList);
                    Collections.reverse(reversed);
                    adapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String prevChildKey) {
                Specified_Club.this.finish();
                Specified_Club.this.overridePendingTransition(0,0);
                startActivity(Specified_Club.this.getIntent());
                Specified_Club.this.overridePendingTransition(0,0);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Specified_Club.this.finish();
                Specified_Club.this.overridePendingTransition(0,0);
                startActivity(Specified_Club.this.getIntent());
                Specified_Club.this.overridePendingTransition(0,0);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String prevChildKey) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.listview.setAdapter(adapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i=new Intent(Specified_Club.this,DescActivity.class);
                TextView key=(TextView) view.findViewById(R.id.key);
                String k=key.getText().toString();
                i.putExtra("key",k);
                startActivity(i);
            }
        });


    }
}