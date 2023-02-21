package com.sparklab.car_rental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Signup extends AppCompatActivity {
EditText fullname,contact,email,username,password;
RadioButton male,female;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 22;
    Uri downloadUrl;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        fullname=(EditText) findViewById(R.id.txt_sfullname);
        contact=(EditText) findViewById(R.id.txt_scontact);
        email=(EditText) findViewById(R.id.txt_semail);
        username=(EditText) findViewById(R.id.txt_susername);
        password=(EditText) findViewById(R.id.txt_spassword);

        male=(RadioButton) findViewById(R.id.male);
        female=(RadioButton) findViewById(R.id.female);
        Button btn_signup=(Button) findViewById(R.id.txt_btnsignup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Map<String, String> sign_data = new HashMap<>();

                    sign_data.put("fname", fullname.getText().toString());
                    sign_data.put("contact", contact.getText().toString());
                    sign_data.put("email", email.getText().toString());
                    sign_data.put("username", username.getText().toString());
                    sign_data.put("password", password.getText().toString());
                    sign_data.put("gender", "Male");
                    sign_data.put("license", downloadUrl.toString());

                    SharedPreferences keyValues = getSharedPreferences("Car_Rental", Context.MODE_PRIVATE);
                    SharedPreferences.Editor keyValuesEditor = keyValues.edit();

                    for (String s : sign_data.keySet()) {
                        keyValuesEditor.putString(s, sign_data.get(s));
                    }

                    keyValuesEditor.commit();

                    DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference taskref = rootref.child("User_Signup").push();
                    taskref.setValue(sign_data);
                    Toast.makeText(Signup.this, "Signup Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Signup.this,Login.class);
                    startActivity(intent);
                }catch(Exception ex)
                {
                    Toast.makeText(Signup.this, ""+ex.toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });


        Button btn_licence=(Button) findViewById(R.id.btn_licence);
        btn_licence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            SelectImage();
            }
        });

    }




    // Select Image method
    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);

            }

            catch (IOException e) {
                // Log the exception
                Toast.makeText(this, "Exception"+e.toString(), Toast.LENGTH_SHORT).show();
            }


            uploadImage();
        }
    }




    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "Licence/"+ UUID.randomUUID().toString()
                                    );

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                             downloadUrl = uri;
                                            //Toast.makeText(Signup.this, ""+downloadUrl.toString(), Toast.LENGTH_LONG).show();
                                            Log.d("Image URl",uri.toString());
                                        }
                                    });

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(Signup.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(Signup.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }


}