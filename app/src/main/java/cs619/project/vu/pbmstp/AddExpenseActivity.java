package cs619.project.vu.pbmstp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import cs619.project.vu.pbmstp.common.DatabaseHelper;
import cs619.project.vu.pbmstp.model.Category;
import cs619.project.vu.pbmstp.model.Expense;

public class AddExpenseActivity extends AppCompatActivity {

    EditText editTextName,editTextAmount,editTextDate,editTextRemarks;
    Spinner spinnerCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        editTextName=(EditText) findViewById(R.id.editTextName);
        editTextAmount=(EditText) findViewById(R.id.editTextAmount);
        editTextDate=(EditText) findViewById(R.id.editTextDate);
        editTextRemarks=(EditText) findViewById(R.id.editTextRemarks);
        spinnerCat=(Spinner) findViewById(R.id.spinnerCat);
        // spinner items
        DatabaseHelper db=new DatabaseHelper(this);
        ArrayList<Category> categories=db.getCategories();
        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this,android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(adapter);

    }

    public void save(View view)
    {
        String cat=spinnerCat.getSelectedItem().toString();
        String name=editTextName.getText().toString();
        String date=editTextDate.getText().toString();
        String remarks=editTextRemarks.getText().toString();
        if(cat.equals("") || date.equals("") || editTextAmount.getText().toString().equals(""))
        {
            Toast.makeText(this, "Please enter all data.", Toast.LENGTH_SHORT).show();
            return;
        }
        double amount=Double.parseDouble(editTextAmount.getText().toString());
        Expense expense=new Expense(cat,name,amount,date,remarks);
        DatabaseHelper db=new DatabaseHelper(this);
        if(db.addExpense(expense)>0)
        {
            Toast.makeText(this, "Record inserted successfully.", Toast.LENGTH_SHORT).show();
            this.finish();
        }
        else 
        {
            Toast.makeText(this, "Unknown error", Toast.LENGTH_SHORT).show();
        }
    }
}
