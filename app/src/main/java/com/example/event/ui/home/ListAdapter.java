package com.example.event.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.event.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ListAdapter extends ArrayAdapter<Event> {
    public ListAdapter(Context context, ArrayList<Event> eventList){
        super(context, R.layout.listitem,eventList);

    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Event event=getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.listitem,parent,false);
        }
        TextView event_name=convertView.findViewById(R.id.event_name);
        TextView status=convertView.findViewById(R.id.status);
        TextView club=convertView.findViewById(R.id.club);
        TextView postedon=convertView.findViewById(R.id.posted);
        TextView event_on=convertView.findViewById(R.id.event_date);
        TextView dline=convertView.findViewById(R.id.dline);
        TextView key=convertView.findViewById(R.id.key);




        event_name.setText(event.getEvent_name());
        status.setText(event.getStatus());
        club.setText(event.getPosted_by());
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YY");
        postedon.setText(simpleDateFormat.format(event.posted_on));
        event_on.setText(simpleDateFormat.format(event.event_on));
        dline.setText(simpleDateFormat.format(event.deadline));
        key.setText(event.key);


        return convertView;
    }
}
