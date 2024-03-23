package cs619.project.vu.pbmstp.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;

import cs619.project.vu.pbmstp.model.Category;
import cs619.project.vu.pbmstp.model.Expense;

/**
 * Created by Maria on 6/28/2018.
 */

public class DatabaseHelper {

    private static final String DB="bpms";
    private static final int DB_VERSION=1;

    private static final String TABLE_EXPENSE ="expense";
    private static final String TABLE_CATEGORY ="category";
    //	COLUMNS
    public static final String ROW_ID ="id";
    public static final String ROW_NAME ="title";
    public static final String ROW_CATEGORY ="cat";
    public static final String ROW_AMOUNT ="city";
    public static final String ROW_DATE ="exp_date";
    public static final String ROW_REMARKS ="remarks";

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DB, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create table
            db.execSQL("CREATE TABlE " + TABLE_CATEGORY +" ( "+
                    ROW_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ROW_NAME +" TEXT "+
                    ");");
            db.execSQL("CREATE TABlE " + TABLE_EXPENSE +" ( "+
                    ROW_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ROW_NAME +" TEXT, " +
                    ROW_CATEGORY +" TEXT, " +
                    ROW_AMOUNT +" REAL, " +
                    ROW_DATE +" TEXT, " +
                    ROW_REMARKS +" TEXT "+
                    ");");
            // inserting values
            ArrayList<Category> categories=new ArrayList<Category>();
            categories.add(new Category("Utility Bills"));
            categories.add(new Category("Grocery"));
            categories.add(new Category("Entertainment"));
            for (int i=0;i<categories.size();i++)
            {
                ContentValues cv=new ContentValues();
                cv.put(ROW_NAME, categories.get(i).getName());
                db.insert(TABLE_CATEGORY, null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CATEGORY);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EXPENSE);
            this.onCreate(db);
        }

    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    public DatabaseHelper(Context c) {
        this.context=c;
    }
    public DatabaseHelper open()
    {
        this.dbHelper=new DbHelper(this.context);
        this.database=dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        this.dbHelper.close();
    }

//    //++++++++++++++++++++++++++++++++++ Adding Record +++++++++++++++++++++++++++++++++
    public int addCategory(Category category)
    {
        ContentValues cv=new ContentValues();
        cv.put(ROW_NAME, category.getName());;
        this.open();
        int rowId=(int) this.database.insert(TABLE_CATEGORY, null, cv);
        this.close();
        return rowId;
    }

    public int addExpense(Expense expense)
    {
        ContentValues cv=new ContentValues();
        cv.put(ROW_NAME, expense.getName());
        cv.put(ROW_CATEGORY, expense.getCategory());
        cv.put(ROW_AMOUNT, expense.getAmount());
        cv.put(ROW_DATE, expense.getDate());
        cv.put(ROW_REMARKS, expense.getRemarks());

        this.open();
        int rowId=(int) this.database.insert(TABLE_EXPENSE, null, cv);
        this.close();
        return rowId;
    }



    public ArrayList<Category> getCategories()
    {
        ArrayList<Category> categories=new ArrayList<Category>();
        this.open();
        String[] columns=new String[]{ROW_ID,ROW_NAME};
        Cursor c=this.database.query(TABLE_CATEGORY, columns, null, null, null, null, null, null);
        if(c!=null)
        {
            while(c.moveToNext())
            {
                // int id, String name, String gender, String city, String contact, String booldGroup, int age
                Category category=new Category(Integer.parseInt(c.getString(c.getColumnIndex(ROW_ID))),
                        c.getString(c.getColumnIndex(ROW_NAME)));
                categories.add(category);
            }
        }
        this.close();
        return categories;
    }

    public ArrayList<Expense> getExpenses(String category)
    {
        ArrayList<Expense> expenses=new ArrayList<Expense>();
        this.open();
        String[] columns=new String[]{ROW_ID,ROW_NAME,ROW_CATEGORY,ROW_AMOUNT,ROW_DATE,ROW_REMARKS};
        Cursor c=this.database.query(TABLE_EXPENSE, columns, ROW_CATEGORY+"=?", new String[]{category}, null, null, null, null);
        if(c!=null)
        {
            while(c.moveToNext())
            {
                Expense exp=new Expense(Integer.parseInt(c.getString(c.getColumnIndex(ROW_ID))),
                        c.getString(c.getColumnIndex(ROW_CATEGORY)),
                        c.getString(c.getColumnIndex(ROW_NAME)),
                        Double.parseDouble(c.getString(c.getColumnIndex(ROW_AMOUNT))),
                        c.getString(c.getColumnIndex(ROW_DATE)),
                        c.getString(c.getColumnIndex(ROW_REMARKS))
                );
                expenses.add(exp);
            }
        }
        this.close();
        return expenses;
    }

//    public ArrayList<Person> search(String bllodGroup,String city,String gender,int age)
//    {
//        ArrayList<Person> persons=new ArrayList<Person>();
//        this.open();
//        String[] columns=new String[]{ROW_ID,ROW_NAME, ROW_CATEGORY, ROW_AMOUNT, ROW_DATE, ROW_REMARKS,ROW_AGE};
//        Cursor c=this.database.query(TABLE_EXPENSE, columns, ROW_GENDER+"=? AND "+ ROW_AMOUNT +"=? AND "+ ROW_REMARKS +"=? AND "+ROW_AGE+"=?", new String[]{gender,city,bllodGroup ,String.valueOf(age)}, null, null, null, null);
//        if(c!=null)
//        {
//            if(c.moveToFirst())
//            {
//                // int id, String name, String gender, String city, String contact, String booldGroup, int age
//                Person p=new Person(Integer.parseInt(c.getString(c.getColumnIndex(ROW_ID))),
//                        c.getString(c.getColumnIndex(ROW_NAME)),
//                        c.getString(c.getColumnIndex(ROW_GENDER)),
//                        c.getString(c.getColumnIndex(ROW_AMOUNT)),
//                        c.getString(c.getColumnIndex(ROW_DATE)),
//                        c.getString(c.getColumnIndex(ROW_REMARKS)),
//                        Integer.parseInt(c.getString(c.getColumnIndex(ROW_AGE))));
//                persons.add(p);
//            }
//        }
//        this.close();
//        return persons;
//    }

}
