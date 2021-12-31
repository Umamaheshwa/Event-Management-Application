package com.example.event.ui.completed;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.event.DescActivity;
import com.example.event.R;
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

public class CompletedFragment extends Fragment {
    TextView tv;
    ListView lv;
    FirebaseDatabase rootNode;
    DatabaseReference reference,newPostRef,postsRef;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed, container, false);
        //initializeListView();
        ListView lv = (ListView) view.findViewById(R.id.listview);
        ArrayList<Event> coursesArrayList = new ArrayList<>();
        ArrayList<Event> reversed = new ArrayList<>();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        postsRef = reference.child("Events");
        ListAdapter adapter=new ListAdapter(getActivity(),reversed);
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
        postsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String prevChildKey) {
                Event e=snapshot.getValue(Event.class);
                String key=snapshot.getKey();
                e.setKey(key);
                if(e.getEvent_on().getTime()<pts) {
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

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String prevChildKey) {
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                startActivity(getActivity().getIntent());
                getActivity().overridePendingTransition(0,0);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                startActivity(getActivity().getIntent());
                getActivity().overridePendingTransition(0,0);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String prevChildKey) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        lv.setAdapter(adapter);
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i=new Intent(getActivity(),DescActivity.class);
                TextView key=(TextView) view.findViewById(R.id.key);
                String k=key.getText().toString();
                i.putExtra("key",k);
                startActivity(i);
            }
        });

        return view;
    }
}