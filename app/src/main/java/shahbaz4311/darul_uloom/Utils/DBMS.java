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

import shahbaz4311.darul_uloom.Models.Record;
import shahbaz4311.darul_uloom.Models.Student;

public class DBMS extends SQLiteOpenHelper {
    private static final String DB_NAME = "darul_uloom.db";
    //Student Table
    private static final String STUDENT_TABLE = "student";
    private static final String STUDENT_ID = "id";
    private static final String STUDENT_NAME = "name";

    //Record Table
    private static final String RECORD_TABLE = "record";
    private static final String RECORD_ID = "id";
    private static final String RECORD_DATE = "date";
    private static final String RECORD_SABAQ = "sabaq";
    private static final String RECORD_START = "start";
    private static final String RECORD_END = "end_";
    private static final String RECORD_SABQI = "sabqi";
    private static final String RECORD_MANZIL = "manzil";
    private static final String RECORD_STUDENT_ID = "student_id";


    public DBMS(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating student table
        db.execSQL("CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE + " (" + STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " TEXT)");

        //creating record table
        db.execSQL("CREATE TABLE IF NOT EXISTS " + RECORD_TABLE + " (" + RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                RECORD_DATE + " TEXT, " + RECORD_SABAQ + " INTEGER, " + RECORD_START + " INTEGER, " + RECORD_END + " INTEGER, " +
                RECORD_SABQI + " INTEGER, " + RECORD_MANZIL + " INTEGER, " + RECORD_STUDENT_ID + " INTEGER, " +
                "FOREIGN KEY (" + RECORD_STUDENT_ID + ") REFERENCES " + STUDENT_TABLE + "(" + STUDENT_ID + "));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop the table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        //drop the table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + RECORD_TABLE);
        onCreate(db);
    }

    //inserting student into the database
    public int addStudent(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME, name);
        int id = (int) db.insert(STUDENT_TABLE, null, contentValues);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + STUDENT_TABLE + ";";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(STUDENT_ID));
            String name = cursor.getString(cursor.getColumnIndex(STUDENT_NAME));
            Student student = new Student(id, name);
            students.add(student);
        }
        cursor.close();
        db.close();
        return students;
    }


    //inserting record into the database
    public int addRecord(Record record) {
        //check if the record already exists
        int recordID = doesExist(record);
        if (recordID != -1) {
            record.setId(recordID);
            updateRecord(record);
            return recordID;
        }

        //if not then insert it
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RECORD_DATE, record.getDate());
        contentValues.put(RECORD_SABAQ, record.getSabaq());
        contentValues.put(RECORD_START, record.getStart());
        contentValues.put(RECORD_END, record.getEnd());
        contentValues.put(RECORD_SABQI, record.getSabqi());
        contentValues.put(RECORD_MANZIL, record.getManzil());
        contentValues.put(RECORD_STUDENT_ID, record.getStudent_id());
        int id = (int) db.insert(RECORD_TABLE, null, contentValues);
        db.close();
        return id;
    }

    public boolean updateRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RECORD_DATE, record.getDate());
        contentValues.put(RECORD_SABAQ, record.getSabaq());
        contentValues.put(RECORD_START, record.getStart());
        contentValues.put(RECORD_END, record.getEnd());
        contentValues.put(RECORD_SABQI, record.getSabqi());
        contentValues.put(RECORD_MANZIL, record.getManzil());
        contentValues.put(RECORD_STUDENT_ID, record.getStudent_id());
        int rowsAffected = db.update(RECORD_TABLE, contentValues, RECORD_ID + " = ?" , new String[]{String.valueOf(record.getId())});
        db.close();
        return rowsAffected > 0;
    }

    @SuppressLint("Range")
    public int doesExist(Record record){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + RECORD_TABLE + " WHERE " + RECORD_DATE + " = '" + record.getDate() + "' AND " + RECORD_STUDENT_ID + " = '" + record.getStudent_id() + "';";
        Cursor cursor = db.rawQuery(query, null);
        int id = -1;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex(RECORD_ID));
        }
        cursor.close();
        db.close();
        return id;
    }


    @SuppressLint("Range")
    public List<Record> getStudentRecords(Student student) {
        List<Record> records = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + RECORD_TABLE + " WHERE " + RECORD_STUDENT_ID + " = '" + student.getId() + "';";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(RECORD_ID));
            String date = cursor.getString(cursor.getColumnIndex(RECORD_DATE));
            int sabaq = cursor.getInt(cursor.getColumnIndex(RECORD_SABAQ));
            int start = cursor.getInt(cursor.getColumnIndex(RECORD_START));
            int end = cursor.getInt(cursor.getColumnIndex(RECORD_END));
            boolean sabqi = cursor.getInt(cursor.getColumnIndex(RECORD_SABQI))>0;
            int manzil = cursor.getInt(cursor.getColumnIndex(RECORD_MANZIL));
            int student_id = cursor.getInt(cursor.getColumnIndex(RECORD_STUDENT_ID));
            Record record = new Record(id, date, sabaq, start, end, sabqi, manzil, student_id);
            records.add(record);
        }
        cursor.close();
        db.close();
        return records;
    }
}
