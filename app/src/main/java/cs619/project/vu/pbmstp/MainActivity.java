package cs619.project.vu.pbmstp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openSearch(View view)
    {
        Intent i=new Intent(getApplicationContext(),SearchActivity.class);
        startActivity(i);
    }

    public void openAddExpense(View view)
    {
        Intent i=new Intent(getApplicationContext(),AddExpenseActivity.class);
        startActivity(i);
    }

    public void openCategories(View view)
    {
        Intent i=new Intent(getApplicationContext(),CategoryActivity.class);
        startActivity(i);
    }
}
