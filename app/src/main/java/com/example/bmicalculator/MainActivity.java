package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bmicalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getroot());

        //  Creating Adapter For Spinner
        ArrayAdapter<CharSequence>adapterUnit =  ArrayAdapter.createFromResource(this,R.array.spinnerllist, android.R.layout.simple_spinner_item);
        adapterUnit.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.spn.setAdapter(adapterUnit);
        binding.spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String checkedDataOfSpinner = binding.spn.getSelectedItem().toString();
                if (checkedDataOfSpinner.equals("MKS")){
                    binding.HeightUnit.setText("CM");
                    binding.WeightUnit.setText("KG");
                    binding.Calculate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String s1 = binding.Height.getText().toString();
                            String s2 = binding.Weight.getText().toString();
                            if(s1.equals("")){
                                binding.Height.setError("Please Enter Your Height");
                                binding.Height.requestFocus();
                                return;
                            }
                            if(s2.equals("")){
                                binding.Weight.setError("Please Enter Your Weight");
                                binding.Weight.requestFocus();
                                return;
                            }
                            float height = Float.parseFloat(s1)/100;
                            float weight = Float.parseFloat(s2);

                            float bmiValue = weight / (height * height);
                            binding.Result.setText("Result :"+ interpreateBMI(bmiValue));
                            binding.BMIValue.setText("BMI :"+bmiValue);
                        }
                    });
                    binding.Reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.Height.setText("");
                            binding.Weight.setText("");
                            binding.Result.setText("");
                            binding.BMIValue.setText("");
                        }
                    });
                }
                else if(checkedDataOfSpinner.equals("FPS")){
                    binding.HeightUnit.setText("Feet");
                    binding.WeightUnit.setText("LB");
                    binding.Calculate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String s1 = binding.Height.getText().toString();
                            String s2 = binding.Weight.getText().toString();
                            if(s1.equals("")){
                                binding.Height.setError("Please Enter Your Height");
                                binding.Height.requestFocus();
                                return;
                            }
                            if(s2.equals("")){
                                binding.Weight.setError("Please Enter Your Weight");
                                binding.Weight.requestFocus();
                                return;
                            }
                            float height = Float.parseFloat(s1);
                            float weight = Float.parseFloat(s2);
                            float heightInInch = height *12;
                            float bmiValue = (weight/heightInInch/heightInInch) * 703;
                            binding.Result.setText("Result :"+ interpreateBMI(bmiValue));
                            binding.BMIValue.setText("BMI :"+bmiValue);

                        }
                    });
                    binding.Reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.Height.setText("");
                            binding.Weight.setText("");
                            binding.Result.setText("");
                            binding.BMIValue.setText("");
                        }
                    });
                }
                else if(checkedDataOfSpinner.equals("Select Unit")){
                    binding.Calculate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TextView errorText = (TextView) binding.spn.getSelectedView();
                            errorText.setError("First Select The Unit");
                            errorText.setTextColor(Color.RED);
                            binding.Height.setError("Please Enter Your Height");
                            binding.Weight.setError("Please Enter Your Weight");
                        }
                    });
                    binding.HeightUnit.setText(null);
                    binding.WeightUnit.setText(null);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


//        RadioGroup radioGroup = binding.radioGroup;
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                int valueMale = Integer.parseInt(binding.male.getTag().toString());
//                int valueFemale = Integer.parseInt(binding.Female.getTag().toString());
//                if(i == valueMale){
//
//                }
//                else if(i == valueFemale){
//
//                }
//
//            }
//        });
    }
    public String interpreateBMI(float bmiValue) {
        if (bmiValue < 16)
            return "Severely UnderWeight";
        else if (bmiValue >= 16 && bmiValue < 18.5)
            return "UnderWeight";
        else if (bmiValue >= 18.5 && bmiValue < 25)
            return "Normal";
        else if (bmiValue >= 25 && bmiValue <= 30)
            return "OverWeight";
        return "Obesity";
    }
}