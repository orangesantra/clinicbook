package com.example.abcd.EveryoneDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.abcd.Listresult;
import com.example.abcd.R;
import com.google.android.material.textfield.TextInputLayout;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.Calendar;

public class Details extends AppCompatActivity {

   //private EditText fullNameEditText, emailEditText, passwordEditText;
   // private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword, inputTest;

   // private Button signupBtn;
   // ImageView i1;

    private static final String TAG = "Details";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    String text1="",text3="",text2="";

    TextInputLayout textInputLayout1;
    TextInputLayout textInputLayout2;
    TextInputLayout textInputLayout3;

    public ParseObject name = new ParseObject("Data");

    Calendar calendar;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    LinearLayout selectDate;
    TextView date;



    String selectedItemText="null";
    String usertype="null";
    String listItemname="null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent i2=getIntent();//gives us back the current intent that got us here.i2 has taken information from the intentobj of earlier intent.
        usertype = i2.getStringExtra("usertype");
        listItemname =i2.getStringExtra("listitemname");

        setTitle(listItemname);

        name.put("USERTYPE",usertype);

        selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.tvSelectedDate);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Details.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + (month + 1) + "/" + year);
                                name.put("DateofAppointment",date.getText().toString());
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


         textInputLayout1 = findViewById(R.id.t1);
         textInputLayout2 = findViewById(R.id.t2);
         textInputLayout3 = findViewById(R.id.t3);


        initializeWidgets();
      //  initializeListeners();

        String[] arraySpinner = new String[] {
                "Andhra Pradesh",
                "Arunachal Pradesh",
                "Assam",
                "Bihar",
                "Chhattisgarh",
                "Goa",
                "Gujarat",
                "Haryana",
                "Himachal Pradesh",
                "Jammu and Kashmir",
                "Jharkhand",
                "Karnataka",
                "Kerala",
                "Madhya Pradesh",
                "Maharashtra",
                "Manipur",
                "Meghalaya",
                "Mizoram",
                "Nagaland",
                "Odisha",
                "Punjab",
                "Rajasthan",
                "Sikkim",
                "Tamil Nadu",
                "Telangana",
                "Tripura",
                "Uttarakhand",
                "Uttar Pradesh",
                "West Bengal",
                "Andaman and Nicobar Islands",
                "Chandigarh",
                "Dadra and Nagar Haveli",
                "Daman and Diu",
                "Delhi",
                "Lakshadweep",
                "Puducherry"
        };
        Spinner s = findViewById(R.id.spinnerstate);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 selectedItemText = (String) parent.getItemAtPosition(position);
//                // Notify the selected item text
//                Toast.makeText
//                        (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
//                        .show();

                name.put("State",selectedItemText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





         mDisplayDate = (TextView) findViewById(R.id.tvDate);
         mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Details.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = "Your Date of Birth :-" +month + "/" + day + "/" + year;
                String parsedate = +month + "/" + day + "/" + year;
                name.put("Dateofbirth",parsedate);
                mDisplayDate.setText(date);
            }
        };}

    public void onRadioButtonClicked(View view) {


        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.DependentMale:
                if (checked) {
                    name.put("Gender","Male");
                }

                break;
            case R.id.DependentFemale:
                if (checked){
                    name.put("Gender","Female");
                }

                break;
            case R.id.Dependantothers:
                if (checked){
                    name.put("Gender","Others");
                }
                break;
        }
    }



        private void initializeWidgets() {

            Toolbar mToolbar = findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);

    }

        public void back(View view){

                    Intent intent = new Intent(getApplicationContext(), Listresult.class);
                    startActivity(intent);
        }

    public void next(View view){

        text1 = textInputLayout1.getEditText().getText().toString();
        text2 = textInputLayout2.getEditText().getText().toString();
        text3 = textInputLayout3.getEditText().getText().toString();

        Log.i("DATEEEEEEEEEEEEE",date.getText().toString());




        name.put("Patientname",text1);
        name.put("PatientParentname",text2);
        name.put("Contactno",text3);
        name.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if(e==null){
                    //ok
                    Log.i("okay","We saved the Data");
                }else{
                    e.printStackTrace();
                }

            }
        });


        Intent intent = new Intent(getApplicationContext(), MoreDetails.class);
        startActivity(intent);
    }

//        private void initializeListeners() {
//
//            signupBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    signUp();
//                }
//            });
//        }

//        private void signUp() {
//
//            boolean isValid = true;
//
//            if (fullNameEditText.getText().toString().isEmpty()) {
//                inputLayoutName.setError("Your name is mandatory");
//                isValid = false;
//            } else {
//                inputLayoutName.setErrorEnabled(false);
//            }
//
//            if (emailEditText.getText().toString().isEmpty()) {
//                inputLayoutEmail.setError("Email is mandatory");
//                isValid = false;
//            } else {
//                inputLayoutEmail.setErrorEnabled(false);
//            }
//
//            if (passwordEditText.getText().toString().trim().length() < 8 ) {
//                inputLayoutPassword.setError(getString(R.string.pwd_validation_msg));
//                isValid = false;
//            } else {
//                inputLayoutPassword.setErrorEnabled(false);
//            }
//
//            if (isValid) {
//                Toast.makeText(Details.this, R.string.signup_success, Toast.LENGTH_SHORT).show();
//            }
//        }
}