package com.example.event.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.event.PostEvent;
import com.example.event.R;
import com.example.event.databinding.FragmentAdminBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminFragment extends Fragment {
    Button post;
    String pasword;
    TextInputLayout username,password;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private AdminViewModel adminViewModel;
    private FragmentAdminBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_admin,container,false);
        Button post=view.findViewById(R.id.post);
        username=view.findViewById(R.id.username);
        password=view.findViewById(R.id.password);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Password");
        getdata();


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean us=validateUsername();
                boolean pw=validatePassword();
                if(us==true && pw==true)
                {
                    Intent i=new Intent(getActivity(), PostEvent.class);
                    startActivity(i);
                }
                else
                {
                    return;
                }
            }
        });
        return view;
}
    public boolean validateUsername(){
        String val=username.getEditText().getText().toString().trim();
        if(val.isEmpty()){
            username.setError("Field can not be Empty");
            return false;
        }
        else if(!val.equals("Admin")){
            username.setError("username is incorrect");
            password.setError("check yor password");
            return false;

        }
        else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validatePassword(){
        String val=password.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            password.setError("Field can not be Empty");
            return false;
        }
        else if(!val.equals(pasword)){
            password.setError("password is incorrect");
            return false;

        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
    public void getdata()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value=snapshot.getValue(String.class);
                pasword=value;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}