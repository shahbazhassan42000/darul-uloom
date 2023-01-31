package shahbaz4311.darul_uloom.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import shahbaz4311.darul_uloom.Models.Student;

public class DBMS extends SQLiteOpenHelper {
    private static final String DB_NAME = "darul_uloom.db";
    //Student Table
    private static final String STUDENT_TABLE = "student";
    private static final String STUDENT_ID = "id";
    private static final String STUDENT_NAME = "name";


    public DBMS(@Nullable Context context) {
        super(context, DB_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating student table
        db.execSQL("CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE + " (" + STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop the table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(db);
    }

    //inserting student into the database
    public int addStudent(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME, name);
        int id= (int) db.insert(STUDENT_TABLE, null, contentValues);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public List<Student> getAllStudents() {
        List<Student> students= new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        String query="SELECT * FROM "+STUDENT_TABLE+";";
        Cursor cursor=db.rawQuery(query,null);
        while(cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex(STUDENT_ID));
            String name=cursor.getString(cursor.getColumnIndex(STUDENT_NAME));
            Student student=new Student(id,name);
            students.add(student);
        }
        cursor.close();
        db.close();
        return students;
    }
}
