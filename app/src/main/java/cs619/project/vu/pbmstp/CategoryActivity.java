package cs619.project.vu.pbmstp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cs619.project.vu.pbmstp.adapter.ExpenseListAdapter;
import cs619.project.vu.pbmstp.common.DatabaseHelper;
import cs619.project.vu.pbmstp.model.Category;

public class CategoryActivity extends AppCompatActivity {

    EditText editTextName;
    ListView listView;
    ArrayList<Category> categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        listView=(ListView) findViewById(R.id.listview);
        editTextName=(EditText) findViewById(R.id.editTextCatName);
        categories=new ArrayList<>();
        setupListView();
    }

    private void setupListView()
    {
        categories.clear();
        DatabaseHelper db=new DatabaseHelper(this);
        categories=db.getCategories();
        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, categories);
        listView.setAdapter(adapter);
    }

    public void save(View view)
    {
        String cat=editTextName.getText().toString();
        if(cat.equals(""))
        {
            Toast.makeText(this, "Please add a category name.", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i=0;i<categories.size();i++)
        {
            if(categories.get(i).getName().equals(cat))
            {
                Toast.makeText(this, "Category Name already exists.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        DatabaseHelper db=new DatabaseHelper(this);
        
        if(db.addCategory(new Category(cat))>0)
        {
            Toast.makeText(this, "New Category added successfully.", Toast.LENGTH_SHORT).show();
            setupListView();
            editTextName.setText("");
        }
        else
        {
            Toast.makeText(this, "Some unknown error.", Toast.LENGTH_SHORT).show();
        }
    }
}
