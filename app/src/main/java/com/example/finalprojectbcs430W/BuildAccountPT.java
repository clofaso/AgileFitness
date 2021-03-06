package com.example.finalprojectbcs430W;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BuildAccountPT extends AppCompatActivity {

    EditText firstName, lastName, phoneNum, gender, availability, faceUser, prefExercise, certification;
    String fN, lN, pN, a, pE, fU, g, certifi;
    Button finishReg;
    FirebaseFirestore db;
    public static final String TAG = "YOUR-TAG-NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_build_pt);

        //Pulling intent information from previous activity screen
        Intent intent = getIntent();

        firstName = findViewById(R.id.editTextPTFirstName);
        lastName = findViewById(R.id.editTextPTLastName);
        phoneNum = findViewById(R.id.editTextPTPhoneNumber);
        gender = findViewById(R.id.editTextPTGender);
        availability = findViewById(R.id.editTextPTAvailability);
        certification = findViewById(R.id.editTextPTCertifications);
        prefExercise = findViewById(R.id.editTextPTExPref);
        faceUser = findViewById(R.id.editTextPTFacebookUser);

        finishReg = findViewById(R.id.buttonFinishPTRegistration);

        db = FirebaseFirestore.getInstance();

        finishReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fN = firstName.getText().toString();
                lN = lastName.getText().toString();
                pN = phoneNum.getText().toString();
                a = availability.getText().toString();
                fU = faceUser.getText().toString();
                pE = prefExercise.getText().toString();
                g = gender.getText().toString();
                certifi = certification.getText().toString();

                if (fN == "")
                {
                    fN = "Prefer Not to Say";
                }
                if (lN == " ")
                {
                    lN = "Prefer Not to Say";
                }
                if (pN == " ")
                {
                    pN = "Prefer Not to Say";
                }
                if (fU ==" ")
                {
                    fU = "Prefer Not to Say";
                }
                if (a == " ")
                {
                    a = "Prefer Not to Say";
                }
                if (pE == " ")
                {
                    pE = "Prefer Not to Say";
                }
                if (g == " ")
                {
                    g = "Prefer Not to Say";
                }
                if (certifi == " ")
                {
                    certifi = "Prefer Not to Say";
                }

                Map<String, Object> personalTrainer = new HashMap<>();
                personalTrainer.put("First Name", fN);
                personalTrainer.put("Last Name", lN);
                personalTrainer.put("email", intent.getStringExtra("email"));
                personalTrainer.put("Gender", g);
                personalTrainer.put("Availability", a);
                personalTrainer.put("Phone Number", pN);
                personalTrainer.put("Preferred Exercise", pE);
                personalTrainer.put("Certifications", certifi);
                personalTrainer.put("Facebook Username", fU);

                db.collection("PersonalTrainer")
                    .add(personalTrainer)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());

                            Intent myIntent = new Intent(BuildAccountPT.this, MainHomePage.class);
                            BuildAccountPT.this.startActivity(myIntent);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
            }
        });
    }
}
