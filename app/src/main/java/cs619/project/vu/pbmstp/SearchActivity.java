package cs619.project.vu.pbmstp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import cs619.project.vu.pbmstp.adapter.ExpenseListAdapter;
import cs619.project.vu.pbmstp.common.DatabaseHelper;
import cs619.project.vu.pbmstp.model.Category;
import cs619.project.vu.pbmstp.model.Expense;

public class SearchActivity extends AppCompatActivity {

    Spinner spinner;
    ListView listView;
    ArrayList<Expense> expenses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listView=(ListView) findViewById(R.id.listViewExp);
        spinner=(Spinner) findViewById(R.id.spinnerCategory);
        expenses=new ArrayList<>();
        // spinner items
        DatabaseHelper db=new DatabaseHelper(this);
        ArrayList<Category> categories=db.getCategories();
        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this,android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void search(View view)
    {
//        String cat=spinner.getSelectedItem().toString();
        if(spinner.getSelectedItem().toString().equals(""))
            return;
        setupListView();
    }

    private void setupListView()
    {
        expenses.clear();
        DatabaseHelper db=new DatabaseHelper(this);
        expenses=db.getExpenses(spinner.getSelectedItem().toString());
        ExpenseListAdapter adapter = new ExpenseListAdapter(SearchActivity.this, expenses);
        listView.setAdapter(adapter);
        if(expenses.isEmpty())
            Toast.makeText(this, "No Record Found.", Toast.LENGTH_SHORT).show();
    }
}
