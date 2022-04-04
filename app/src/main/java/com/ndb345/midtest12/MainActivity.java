package com.ndb345.midtest12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    RadioButton graduated,ungraduated;
    CheckBox accommodation,medical_insurance;
    Spinner coursespin;
    TextView total_hours_val,total_fees_val,hours_val,fees_val,lab;
    Button register;
    ArrayList<Course> cList=new ArrayList<>();
    ArrayList<String>cTypes=new ArrayList<>();
    double med_val=0,acc_val=0,hoursval=0,totalhours=0,totalfees=0;
    String flag="";
    ArrayList<Course>tempList=new ArrayList<>();
    ArrayList<String>tempNames=new ArrayList<>();
    public static Course obj;
    double temptotalhours =0;
    double temptotalfees=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Calling method of findview by id and appending data into array list
        fillData();
        layoutfindViewById();
        //radio and button click event
        graduated.setOnClickListener(this);
        ungraduated.setOnClickListener(this);
        register.setOnClickListener(this);
        if(graduated.isChecked())
        {
            flag="graduated";
        }

        //setting spinner
        ArrayAdapter aa=new ArrayAdapter(this, R.layout.spinnerdes,cTypes);
        coursespin.setAdapter(aa);
        coursespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hours_val.setText(String.valueOf(cList.get(i).getCoursehours()));
                fees_val.setText(String.valueOf(cList.get(i).getCoursefees()));
                temptotalhours+=cList.get(i).getCoursehours();
                temptotalfees+=cList.get(i).getCoursefees();
                if (flag.equals("graduated")) {
                    if (temptotalhours <= 21) {
                        totalhours+=cList.get(i).getCoursehours();
                        totalfees+=cList.get(i).getCoursefees();
                    }
                } else if (flag.equals("ungraduated")) {
                    if (temptotalhours <= 19) {
                        totalhours+=cList.get(i).getCoursehours();
                        totalfees+=cList.get(i).getCoursefees();
                    }
                } else {
                    hoursval = 0;
                    totalhours=0;
                    totalfees=0;
                }

                if(flag.equals("graduated") && temptotalhours>21){
                    lab.setText("Maximum Hours");
                }
                else if(flag.equals("ungraduated") && temptotalfees>19){
                    lab.setText("Maximum Hours");
                }
                else
                {
                    lab.setText("");
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //method for appending data into array list
    private void fillData() {
        // Fill data into array list
        cList.add(new Course("Java",1300,6));
        cList.add(new Course("Swift",1500,5));
        cList.add(new Course("iOS",1350,5));
        cList.add(new Course("Android",1400,7));
        cList.add(new Course("Database",1000,4));

            for(Course pz:cList)
            cTypes.add(pz.getCoursename());
    }

    //method for initialize layout objects
    private void layoutfindViewById() {
        name=findViewById(R.id.name);
        graduated=findViewById(R.id.graduated);
        ungraduated=findViewById(R.id.ungraduated);
        accommodation=findViewById(R.id.accommodation);
        medical_insurance=findViewById(R.id.medical_insurance);
        coursespin=findViewById(R.id.coursespin);
        total_hours_val=findViewById(R.id.total_hours_val);
        total_fees_val=findViewById(R.id.total_fees_val);
        register=findViewById(R.id.register);
        hours_val=findViewById(R.id.hours_val);
        fees_val=findViewById(R.id.fees_val);
        lab=findViewById(R.id.lab);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.graduated:
                hoursval=21;
                flag="graduated";
                break;
            case R.id.ungraduated:
                hoursval=19;
                flag="ungraduated";
                break;
            case R.id.register:
                for(int i=0;i<tempList.size();i++)
                {

                }
                for(Course prd:cList) {
                    if (flag.equals("graduated")) {
                        if (totalhours <= 21) {
                            tempList.add(prd);
                            tempNames.add(prd.getCoursename());
                        }
                       /* if(tempNames.contains(prd.getCoursename()))
                        {
                            lab.setText("Course already in");
                            tempList.remove(prd);
                            tempNames.remove(prd.getCoursename());
                        }
                        else
                        {
                            lab.setText("");

                        }*/
                    }
                    else if (flag.equals("ungraduated")) {
                        if (totalhours <= 19) {
                            tempList.add(prd);
                            tempNames.add(prd.getCoursename());
                        }
                    }
                    else {
                        hoursval = 0;
                    }
                }
                if (accommodation.isChecked()) {
                    acc_val = 1000;
                }
                else
                {
                    acc_val = 0;
                }
                if (medical_insurance.isChecked()) {
                    med_val = 700;
                }
                else
                {
                    med_val = 0;
                }
                total_hours_val.setText(String.valueOf(totalhours));
                total_fees_val.setText(String.valueOf(totalfees+acc_val+med_val));
                break;
        }
    }
    //method to return the person object if the username and password are valid and null otherwise
    public Course verifycourse(String usr)
    {
        for(Course prs:cList)
            if(usr.equalsIgnoreCase(prs.getCoursename()))
                return prs;
        return null;
    }
}