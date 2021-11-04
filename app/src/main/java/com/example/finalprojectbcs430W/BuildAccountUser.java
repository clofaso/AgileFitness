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

public class BuildAccountUser extends AppCompatActivity {

    EditText gender, weight, firstName, lastName, phoneNum, workOutPref, faceUser, dietPref;
    String g, w, fN, lN, pN, wP, fU, dP;
    Button finishReg;
    FirebaseFirestore db;
    public static final String TAG = "YOUR-TAG-NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_build_user);

        //Pulling intent information from previous activity screen
        Intent intent = getIntent();

        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        gender = findViewById(R.id.editTextUserGender);
        weight = findViewById(R.id.editTextUserWeight);
        phoneNum = findViewById(R.id.editTextPhoneNum);
        workOutPref = findViewById(R.id.editTextPrefWorkout);
        faceUser = findViewById(R.id.editTextFaceUser);
        dietPref = findViewById(R.id.editTextDietPref);

        finishReg = findViewById(R.id.buttonFinishRegis);

        db = FirebaseFirestore.getInstance();

        finishReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fN = firstName.getText().toString();
                lN = lastName.getText().toString();
                w = weight.getText().toString();
                g = gender.getText().toString();
                pN = phoneNum.getText().toString();
                wP = workOutPref.getText().toString();
                dP = dietPref.getText().toString();
                fU = faceUser.getText().toString();

                if (fN == "")
                {
                    fN = "Prefer Not to Say";
                }
                if (lN == "")
                {
                    lN = "Prefer Not to Say";
                }
                if (w == "")
                {
                    w = "Prefer Not to Say";
                }
                if (g == " ")
                {
                    g = "Prefer Not to Say";
                }
                if (pN == " ")
                {
                    pN = "Prefer Not to Say";
                }
                if (wP ==" ")
                {
                    wP = "Prefer Not to Say";
                }
                if (dP == " ")
                {
                    dP = "Prefer Not to Say";
                }
                if (fU == " ")
                {
                    fU = "Prefer Not to Say";
                }

                Map<String, Object> user = new HashMap<>();
                user.put("First Name", fN);
                user.put("Last Name", lN);
                user.put("Email", intent.getStringExtra("EMAIL"));
                user.put("Weight", w);
                user.put("Gender", g);
                user.put("Phone Number", pN);
                user.put("Preferred Workout Time", wP);
                user.put("Diet Preference", dP);
                user.put("Facebook Username", fU);

                db.collection("user")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());

                            Intent myIntent = new Intent(BuildAccountUser.this, MainHomePage.class);
                            BuildAccountUser.this.startActivity(myIntent);
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
