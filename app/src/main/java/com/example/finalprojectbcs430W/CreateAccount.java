package com.example.finalprojectbcs430W;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {

    EditText email, password;
    Button addUser, addPT;
    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    public static final String TAG = "YOUR-TAG-NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_create);

        email = findViewById(R.id.editTextEnterEmail);
        password = findViewById(R.id.editTextEnterPass);

        addUser = findViewById(R.id.buttonCreateFinishUser);
        addPT = findViewById(R.id.buttonCreateAccountPT);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        password.setTransformationMethod(PasswordTransformationMethod.getInstance());


        addUser.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                newRegister();
            }


        });

        addPT.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                newRegisterPT();
            }


        });




    }

    public void newRegister(){
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Map<String, Object> user = new HashMap<>();

                                String path = "BCS430APP";

                                db.collection(path)
                                        .add(user)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                                                Intent myIntent = new Intent(CreateAccount.this, BuildAccountUser.class);
                                                CreateAccount.this.startActivity(myIntent);
                                                finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "Error adding document", e);
                                            }
                                        });
                            } else{
                                Toast.makeText(CreateAccount.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    });


        };

    public void newRegisterPT(){
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Map<String, Object> personalTrainer = new HashMap<>();

                            String path = "BCS430APP";

                            db.collection(path)
                                    .add(personalTrainer)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                                            Intent myIntent = new Intent(CreateAccount.this, BuildAccountPT.class);
                                            CreateAccount.this.startActivity(myIntent);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error adding document", e);

                                        }
                                    });
                        } else{
                            Toast.makeText(CreateAccount.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });


    };

    /*@Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){

        }
    }*/
}
