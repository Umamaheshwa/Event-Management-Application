package com.example.event.ui.home;

import android.content.Intent;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.event.DescActivity;
import com.example.event.R;
import com.example.event.Specified_Club;
import com.example.event.databinding.FragmentHomeBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class HomeFragment extends Fragment {
    FirebaseDatabase rootNode;
    DatabaseReference reference,newPostRef,postsRef;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    Button bt;
    public View onCreateView( LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        //initializeListView();
        FirebaseMessaging.getInstance().subscribeToTopic("notification");
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


        postsRef.limitToLast(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String prevChildKey) {
                Event e=snapshot.getValue(Event.class);
                String key=snapshot.getKey();
                e.setKey(key);
                if(e.event_on.getTime()<pts)
                    e.setStatus("Completed");
                else if(e.event_on.getTime()>fut)
                    e.setStatus("upComing");
                else if(e.event_on.getTime()<fut && e.event_on.getTime()>pts)
                    e.setStatus("onGoing");
                coursesArrayList.add(e);
                reversed.clear();
                reversed.addAll(coursesArrayList);
                Collections.reverse(reversed);
                adapter.notifyDataSetChanged();

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
        CardView cv1=view.findViewById(R.id.cybersync);
        CardView cv2=view.findViewById(R.id.deptocse);
        CardView cv3=view.findViewById(R.id.std_affair);
        CardView cv4=view.findViewById(R.id.all_clubs);

        cv1.setClickable(true);
        cv2.setClickable(true);
        cv3.setClickable(true);
        cv4.setClickable(true);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),Specified_Club.class);
                i.putExtra("club","Cybersync");
                startActivity(i);
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),Specified_Club.class);
                i.putExtra("club","Dept.Of CSE");
                startActivity(i);
            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),Specified_Club.class);
                i.putExtra("club","Student Affairs");
                startActivity(i);
            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getActivity(), AllClubs.class);
                startActivity(it);
            }
        });
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