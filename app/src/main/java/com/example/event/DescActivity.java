package com.example.event;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.event.databinding.ActivityDescBinding;
import com.example.event.ui.home.Event;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Collections;

public class DescActivity extends AppCompatActivity {
    ActivityDescBinding binding;
    Button bt;
    private Toolbar toolbar;
    FirebaseDatabase rootNode;
    String link;
    DatabaseReference reference,newPostRef,postsRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDescBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        bt=(Button) findViewById(R.id.register);
        Intent intent = this.getIntent();
        if(intent!=null)
        {
            String key=intent.getStringExtra("key");


            String club=intent.getStringExtra("club");
            binding.club.setText(club);
            String posted=intent.getStringExtra("posted");
            binding.posted.setText(posted);
            String dat=intent.getStringExtra("date");
            binding.date.setText(dat);
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference();
            postsRef = reference.child("Events");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YY");
            postsRef.orderByKey().equalTo(key).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String prevChildKey) {
                    Event e=snapshot.getValue(Event.class);
                    String key=snapshot.getKey();
                    e.setKey(key);
                    binding.name.setText(e.getEvent_name());
                    binding.club.setText(e.getPosted_by());
                    binding.dline.setText(simpleDateFormat.format(e.getDeadline()));
                    binding.desc.setText(e.getDescription());
                    link=e.getLink();
                    binding.posted.setText(simpleDateFormat.format(e.getPosted_on()));
                    binding.date.setText(simpleDateFormat.format(e.getEvent_on()));
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String prevChildKey) {
                    finish();
                    overridePendingTransition(0,0);
                    startActivity(getIntent());
                    overridePendingTransition(0,0);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    finish();
                    overridePendingTransition(0,0);
                    startActivity(getIntent());
                    overridePendingTransition(0,0);

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String prevChildKey) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://forms.gle/NGWjtCjvN7xwdCkW7";
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(i);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}