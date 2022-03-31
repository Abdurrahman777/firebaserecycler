package com.example.demofirebasetorecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.demofirebasetorecycler.common.BaseActivity;
import com.example.demofirebasetorecycler.databinding.ActivityAdddataBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class adddata extends BaseActivity {
    /* EditText name,course,email,purl;
     Button submit,back;
   */ ActivityAdddataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_adddata);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_adddata);
       /* name=(EditText)findViewById(R.id.add_name);
        email=(EditText)findViewById(R.id.add_email);
        course=(EditText)findViewById(R.id.add_course);
        purl=(EditText)findViewById(R.id.add_purl);

        back=(Button)findViewById(R.id.add_back);*/
        binding.addBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

/*
        submit=(Button)findViewById(R.id.add_submit);
*/
        binding.addSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validation();
                /*processinsert();*/
            }
        });
    }

    private void validation() {
        if (isETEmpty(binding.addName, 1)) {
            customToast("Enter Name", 200, true, null);
        } else if (isETEmpty(binding.addCourse, 1)) {
            customToast("Enter Number", 200, true, null);
        } else if (isETEmpty(binding.addEmail, 1)) {
            customToast("Enter Email", 200, true, null);
        } else if (isETEmpty(binding.addPurl, 1)) {
            customToast("Enter Purl", 200, true, null);
        } else {
            processinsert();
        }
    }
    private void processinsert() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", binding.addName.getText().toString());
        map.put("course", binding.addCourse.getText().toString());
        map.put("email", binding.addEmail.getText().toString());
        map.put("purl", binding.addPurl.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("students")
                .push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                binding.addName.setText("");
                binding.addCourse.setText("");
                binding.addEmail.setText("");
                binding.addPurl.setText("");
                Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Could not insert", Toast.LENGTH_LONG).show();
                    }
                });
    }
}