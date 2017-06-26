package com.planet.noobs.testproject.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.planet.noobs.testproject.Model.Lectures;
import com.planet.noobs.testproject.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rio on 14/6/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = "SQL ";
    //databse version
    private static final int DATABASE_VERSION = 1;
    //database name
    private static final String DATABASE_NAME = "UserManager.db";

    //table name for student
    private static final String TABLE_STUDENT = "student";
    //column's name
    private static final String COLUMN_STUDENT_ID = "student_id";
    private static final String COLUMN_STUDENT_APPROVAL = "student_approval";
    private static final String COLUMN_STUDENT_NAME = "student_name";
    private static final String COLUMN_STUDENT_CONTACT = "student_contact";
    private static final String COLUMN_STUDENT_EMAIL = "student_email";
    private static final String COLUMN_STUDENT_PASSWORD = "student_password";
    //create table query
    private static final String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT + "(" +
            COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_STUDENT_APPROVAL + " NUMBER DEFAULT 0, " +
            COLUMN_STUDENT_NAME + " TEXT, " +
            COLUMN_STUDENT_CONTACT + " NUMBER, " +
            COLUMN_STUDENT_EMAIL + " TEXT, " +
            COLUMN_STUDENT_PASSWORD + " TEXT" + ")";

    //DROP TABLE IF EXISTS
    private static final String DROP_STUDENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_STUDENT;

    //Column's name for Books
    private static final String TABLE_BOOKS = "books";

    private static final String COLUMN_BOOK_ID = "book_id";
    private static final String COLUMN_BOOK_ISSUED = "book_issued";
    private static final String COLUMN_BOOK_TITLE = "book_title";
    private static final String COLUMN_BOOK_AUTHOR = "book_author";
    private static final String COLUMN_BOOK_ISSUE_DATE = "book_issue_date";

    private static final String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOKS + "(" +
            COLUMN_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_BOOK_ISSUED + " NUMBER DEFAULT 0, " +
            COLUMN_BOOK_TITLE + " TEXT, " +
            COLUMN_BOOK_ISSUE_DATE + " TEXT, " +
            COLUMN_BOOK_AUTHOR + " TEXT" + ")";

    private static final String DROP_BOOKS_TABLE = "DROP TABLE IF EXISTS " + TABLE_BOOKS;

    // Columns name for lectures table
    // Table name
    private static final String TABLE_LECTURES = "lectures";

    private static final String COLUMN_LEC_ID = "lec_id";
    private static final String COLUMN_LEC_SUBJECT = "subject";
    private static final String COLUMN_TIME = "lec_time";

    private static final String CREATE_LECTURES_TABLE = "CREATE TABLE " + TABLE_LECTURES + "(" +
            COLUMN_LEC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_LEC_SUBJECT + " TEXT, " +
            COLUMN_TIME + " TEXT" + ")";

    private static final String DROP_LECTURES_TABLE = "DROP TABLE IF EXISTS " + TABLE_LECTURES;

    // teacher table
    private static final String TABLE_TEACHER = "teacher";
    //column's name
    private static final String COLUMN_TEACHER_ID = "teacher_id";
    private static final String COLUMN_TEACHER_APPROVAL = "teacher_approval";
    private static final String COLUMN_TEACHER_NAME = "teacher_name";
    private static final String COLUMN_TEACHER_CONTACT = "teacher_contact";
    private static final String COLUMN_TEACHER_EMAIL = "teacher_email";
    private static final String COLUMN_TEACHER_PASSWORD = "teacher_password";
    //create table query
    private static final String CREATE_TEACHER_TABLE = "CREATE TABLE " + TABLE_TEACHER + "(" +
            COLUMN_TEACHER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TEACHER_APPROVAL + " NUMBER DEFAULT 0, " +
            COLUMN_TEACHER_NAME + " TEXT, " +
            COLUMN_TEACHER_CONTACT + " NUMBER, " +
            COLUMN_TEACHER_EMAIL + " TEXT, " +
            COLUMN_TEACHER_PASSWORD + " TEXT" + ")";

    //DROP TABLE IF EXISTS
    private static final String DROP_TEACHER_TABLE = "DROP TABLE IF EXISTS " + TABLE_TEACHER;

    // department table
    private static final String TABLE_DEPT = "department";
    //column's name
    private static final String COLUMN_DEPT_ID = "dept_id";
    private static final String COLUMN_DEPT_APPROVAL = "dept_approval";
    private static final String COLUMN_DEPT_NAME = "dept_name";
    private static final String COLUMN_DEPT_CONTACT = "dept_contact";
    private static final String COLUMN_DEPT_EMAIL = "dept_email";
    private static final String COLUMN_DEPT_PASSWORD = "dept_password";
    //create table query
    private static final String CREATE_DEPT_TABLE = "CREATE TABLE " + TABLE_DEPT + "(" +
            COLUMN_DEPT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_DEPT_APPROVAL + " NUMBER DEFAULT 0, " +
            COLUMN_DEPT_NAME + " TEXT, " +
            COLUMN_DEPT_CONTACT + " NUMBER, " +
            COLUMN_DEPT_EMAIL + " TEXT, " +
            COLUMN_DEPT_PASSWORD + " TEXT" + ")";

    //DROP TABLE IF EXISTS
    private static final String DROP_DEPT_TABLE = "DROP TABLE IF EXISTS " + TABLE_DEPT;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_LECTURES_TABLE);
        db.execSQL(CREATE_TEACHER_TABLE);
        db.execSQL(CREATE_DEPT_TABLE);
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop student table if exist
//        db.execSQL(DROP_STUDENT_TABLE);
        onCreate(db);
    }
    // Lectures helper methods

    public void addLec(Lectures lectures) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LEC_SUBJECT, lectures.getLecTitle());
        values.put(COLUMN_TIME, lectures.getLecDateTime());

        db.insert(TABLE_LECTURES, null, values);
        Log.v(DBHelper.class.getSimpleName(), "addlec() is Working!");
        db.close();
    }

    public List getAllLec() {
        String[] COLUMNS = {
                COLUMN_LEC_ID,
                COLUMN_LEC_SUBJECT,
                COLUMN_TIME
        };

        List<Lectures> lecList = new ArrayList<>();
        String sortOrder = COLUMN_LEC_SUBJECT + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_LECTURES,
                COLUMNS,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()) {
            do {
                Lectures lec = new Lectures();
                lec.setLecId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_LEC_ID))));
                lec.setLecTitle(cursor.getString(cursor.getColumnIndex(COLUMN_LEC_SUBJECT)));
                lec.setLecDateTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
                lecList.add(lec);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lecList;
    }

    public void deleteLec(Lectures lec) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_LECTURES, COLUMN_LEC_ID + " = ?",
                new String[]{String.valueOf(lec.getLecId())});
        db.close();
    }

    public List<User> getAllUnapprovedStudent() {
        //columns to be fetched
        String[] columns = {
                COLUMN_STUDENT_NAME,
        };
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> userList = new ArrayList<User>();

        String selection = COLUMN_STUDENT_APPROVAL + " = 0";
        String sortOrder = COLUMN_STUDENT_NAME + " ASC";
        Cursor cursor = db.query(
                TABLE_STUDENT,
                columns,
                selection,
                null,
                null,
                null,
                sortOrder
        );

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_EMAIL)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    // Student helpers methods
    public void addStudent(User user) {
        //open database with write permission
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NAME, user.getName());
        values.put(COLUMN_STUDENT_EMAIL, user.getEmail());
        values.put(COLUMN_STUDENT_PASSWORD, user.getPasswd());
        values.put(COLUMN_STUDENT_CONTACT, user.getContact());

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    public void addDept(User user) {
        //open database with write permission
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DEPT_NAME, user.getName());
        values.put(COLUMN_DEPT_EMAIL, user.getEmail());
        values.put(COLUMN_DEPT_PASSWORD, user.getPasswd());
        values.put(COLUMN_DEPT_CONTACT, user.getContact());

        db.insert(TABLE_DEPT, null, values);
        db.close();
    }

    public void addTeacher(User user) {
        //open database with write permission
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TEACHER_NAME, user.getName());
        values.put(COLUMN_TEACHER_EMAIL, user.getEmail());
        values.put(COLUMN_TEACHER_PASSWORD, user.getPasswd());
        values.put(COLUMN_TEACHER_CONTACT, user.getContact());

        db.insert(TABLE_TEACHER, null, values);
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteStudent(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_STUDENT, COLUMN_STUDENT_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public boolean checkStudent(String email) {
        //columns to be fetched
        String[] columns = {
                COLUMN_STUDENT_ID
        };

        SQLiteDatabase db = this.getReadableDatabase();
        //selection criteria in where clause
        String selection = COLUMN_STUDENT_EMAIL + " = ?" + " AND " + COLUMN_STUDENT_APPROVAL + " = 1";
        //selection arguments
        String[] selection_arg = {
                email
        };

        Cursor cursor = db.query(
                TABLE_STUDENT, // table name
                columns,       // columns to return
                selection,     // Where clause selection
                selection_arg, //Where clause selection value
                null,
                null,
                null
        );

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;

    }

    public boolean checkStudent(String email, String passwd) {
        //columns to be fetched
        String[] columns = {
                COLUMN_STUDENT_ID
        };


        SQLiteDatabase db = this.getReadableDatabase();
        //selection criteria in where clause
        String selection = COLUMN_STUDENT_EMAIL + " = ?" + " AND " +
                COLUMN_STUDENT_PASSWORD + " = ?" + " AND " +
                COLUMN_STUDENT_APPROVAL + " = 0";
        //selection arguments
        String[] selection_arg = {
                email,
                passwd,
        };

        Cursor cursor = db.query(
                TABLE_STUDENT, // table name
                columns,       // columns to return
                selection,     // Where clause selection
                selection_arg, //Where clause selection value
                null,
                null,
                null
        );

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }

    public boolean checkDept(String email, String passwd) {
        //columns to be fetched
        String[] columns = {
                COLUMN_DEPT_ID
        };


        SQLiteDatabase db = this.getReadableDatabase();
        //selection criteria in where clause
        String selection = COLUMN_DEPT_EMAIL + " = ?" + " AND " +
                COLUMN_DEPT_PASSWORD + " = ?" + " AND " +
                COLUMN_DEPT_APPROVAL + " = 1";
        //selection arguments
        String[] selection_arg = {
                email,
                passwd,
        };

        Cursor cursor = db.query(
                TABLE_DEPT, // table name
                columns,       // columns to return
                selection,     // Where clause selection
                selection_arg, //Where clause selection value
                null,
                null,
                null
        );

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;

    }

    public boolean checkTeacher(String email, String passwd) {
        //columns to be fetched
        String[] columns = {
                COLUMN_TEACHER_ID
        };


        SQLiteDatabase db = this.getReadableDatabase();
        //selection criteria in where clause
        String selection = COLUMN_TEACHER_EMAIL + " = ?" + " AND " +
                COLUMN_TEACHER_EMAIL + " = ?" + " AND " +
                COLUMN_TEACHER_APPROVAL + " = 1";
        //selection arguments
        String[] selection_arg = {
                email,
                passwd,
        };

        Cursor cursor = db.query(
                TABLE_TEACHER, // table name
                columns,       // columns to return
                selection,     // Where clause selection
                selection_arg, //Where clause selection value
                null,
                null,
                null
        );

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;

    }


}
