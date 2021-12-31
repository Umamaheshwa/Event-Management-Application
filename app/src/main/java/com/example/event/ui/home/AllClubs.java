package com.example.event.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.event.R;
import com.example.event.Specified_Club;

public class AllClubs extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_clubs);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        CardView cybersync=findViewById(R.id.cybersync);
        CardView ieee=findViewById(R.id.ieee);
        CardView elecsol=findViewById(R.id.elecsol);
        CardView nirmaan=findViewById(R.id.nirmaan);
        CardView robotics=findViewById(R.id.robotics);
        CardView nrutya=findViewById(R.id.nrutya);
        CardView raaga=findViewById(R.id.raaga);
        CardView reflexa=findViewById(R.id.reflexa);
        CardView finearts=findViewById(R.id.finearts);
        CardView literature=findViewById(R.id.literature);
        CardView genesis=findViewById(R.id.genesis);
        CardView sports=findViewById(R.id.sports);
        CardView cultural=findViewById(R.id.cultural);
        CardView foss=findViewById(R.id.foss);
        CardView vcelot=findViewById(R.id.vcelot);
        CardView campusradio=findViewById(R.id.campusradio);
        CardView ewb=findViewById(R.id.ewb);
        CardView booksclub=findViewById(R.id.books);
        CardView minds=findViewById(R.id.mindsnmove);
        CardView abhinaya=findViewById(R.id.abhinaya);
        CardView nss=findViewById(R.id.nss);
        CardView cse=findViewById(R.id.deptocse);
        CardView ece=findViewById(R.id.deptoece);
        CardView eee=findViewById(R.id.deptoeee);
        CardView civil=findViewById(R.id.deptocvl);
        CardView mech=findViewById(R.id.deptomech);
        CardView it=findViewById(R.id.deptoit);
        CardView acm=findViewById(R.id.acm);
        CardView cie=findViewById(R.id.cie);
        CardView others=findViewById(R.id.others);
        cybersync.setClickable(true);
        cybersync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Cybersync");
                startActivity(i);
            }
        });
        ieee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","IEEE");
                startActivity(i);
            }
        });
        elecsol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Elecsol");
                startActivity(i);
            }
        });
        nirmaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Nirmaan");
                startActivity(i);
            }
        });
        robotics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Robotics Club");
                startActivity(i);
            }
        });
        nrutya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Nrutya");
                startActivity(i);
            }
        });
        raaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Raaga");
                startActivity(i);
            }
        });
        reflexa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Reflexa");
                startActivity(i);
            }
        });
        finearts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Fine Arts");
                startActivity(i);
            }
        });
        literature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Literature");
                startActivity(i);
            }
        });
        genesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Genesis");
                startActivity(i);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Sports");
                startActivity(i);
            }
        });
        cultural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Cultural");
                startActivity(i);
            }
        });
        foss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","FOSS");
                startActivity(i);
            }
        });
        vcelot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","VCE LOT");
                startActivity(i);
            }
        });
        campusradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Campus Radio");
                startActivity(i);
            }
        });
        ewb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","EWB");
                startActivity(i);
            }
        });
        booksclub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Books Club");
                startActivity(i);
            }
        });
        minds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Minds and Movements");
                startActivity(i);
            }
        });
        abhinaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Abhinaya");
                startActivity(i);
            }
        });
        nss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","NSS");
                startActivity(i);
            }
        });
        cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Dept.Of CSE");
                startActivity(i);
            }
        });
        ece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Dept.Of ECE");
                startActivity(i);
            }
        });
        eee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Dept.Of EEE");
                startActivity(i);
            }
        });
        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Dept.Of Civil");
                startActivity(i);
            }
        });
        mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Dept.Of Mech");
                startActivity(i);
            }
        });
        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Dept.Of IT");
                startActivity(i);
            }
        });
        acm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","ACM");
                startActivity(i);
            }
        });
        cie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","CIE");
                startActivity(i);
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Specified_Club.class);
                i.putExtra("club","Others");
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