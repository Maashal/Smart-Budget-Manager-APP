package cs619.project.vu.pbmstp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cs619.project.vu.pbmstp.R;
import cs619.project.vu.pbmstp.model.Expense;

/**
 * Created by Maria on 8/4/2018.
 */

public class ExpenseListAdapter  extends ArrayAdapter<Expense> {
    private List<Expense> items;
    private Activity activity;


    public ExpenseListAdapter(Activity activity, List<Expense> expenses) {
        super(activity, R.layout.list_item, expenses);
        this.activity = activity;
        this.items = expenses;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //View row=convertView;
        Expense expense=items.get(position);
        // getting current row
        //row=((Activity)context).getLayoutInflater().inflate(layoutResourceId,parent,false);
        LayoutInflater inflater=activity.getLayoutInflater();
        View row=inflater.inflate(R.layout.list_item,null,true);
        // getting views in row one by one
        TextView tvName=(TextView) row.findViewById(R.id.textView1);
        TextView tvDate=(TextView) row.findViewById(R.id.textView2);
        TextView tvAmount =(TextView) row.findViewById(R.id.textView3);
        TextView tvCategory =(TextView) row.findViewById(R.id.textView4);
        TextView tvRemarks =(TextView) row.findViewById(R.id.textView5);
        // setting values
        tvName.setText(expense.getName());
        tvDate.setText(expense.getDate());
        tvAmount.setText(expense.getAmount()+"");
        tvCategory.setText(" ("+expense.getCategory()+") ");
        tvRemarks.setText(expense.getRemarks());
        // setting tag
        //row.setTag(expense);
        return row;
    }
}