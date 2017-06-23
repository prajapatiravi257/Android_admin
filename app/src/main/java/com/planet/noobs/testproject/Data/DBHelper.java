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
    //table name
    private static final String TABLE_STUDENT = "student";

    //column's name
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_CONTACT = "user_contact";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    //create table query
    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_STUDENT + "(" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USER_NAME + " TEXT, " +
            COLUMN_CONTACT + " NUMBER, " +
            COLUMN_USER_EMAIL + " TEXT, " +
            COLUMN_USER_PASSWORD + " TEXT" + ")";

    //DROP TABLE IF EXISTS
    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_STUDENT;

    //Column's name for Books

/*
    private static final String TABLE_BOOKS = "books";

    private static final String COLUMN_BOOK_ID = "user_id";
    private static final String COLUMN_BOOK_TITLE = "book_title";
    private static final String COLUMN_BOOK_DESC = "book_desc";
    private static final String COLUMN_BOOK_ISSUE_DATE = "book_issue_date";

    private static final String CREATE_BOOK_TABLE = "CREATE TABLE "+ TABLE_BOOKS + "(" +
            COLUMN_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_BOOK_ID +
            " Text, "+ COLUMN_BOOK_TITLE + " Text, " + COLUMN_BOOK_ISSUE_DATE + " Text, " +
            COLUMN_BOOK_DESC + " Text" + ")";

    private static final String DROP_BOOKS_TABLE = "DROP TABLE IF EXISTS " + TABLE_BOOKS;
*/

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

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_LECTURES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop student table if exist
/*
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_LECTURES_TABLE);
*/
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
                lec.setLecTitle(cursor.getString(cursor.getColumnIndex(COLUMN_LEC_SUBJECT)));
                lec.setLecDateTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
                lecList.add(lec);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lecList;
    }

    public List<User> getAllUser() {
        //columns to be fetched
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_CONTACT,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> userList = new ArrayList<User>();

        String sortOrder = COLUMN_USER_NAME + " ASC";
        Cursor cursor = db.query(
                TABLE_STUDENT,
                columns,
                null,
                null,
                null,
                null,
                sortOrder
        );

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setContact(Long.parseLong(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT))));
                user.setPasswd(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }


    // user helpers
    public void addUser(User user) {
        //open database with write permission
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPasswd());
        values.put(COLUMN_CONTACT, user.getContact());

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_STUDENT, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public boolean checkUser(String email) {
        //columns to be fetched
        String[] columns = {
                COLUMN_USER_ID
        };

        SQLiteDatabase db = this.getReadableDatabase();
        //selection criteria in where clause
        String selection = COLUMN_USER_EMAIL + " = ?";
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

    public boolean checkUser(String email, String passwd) {
        //columns to be fetched
        String[] columns = {
                COLUMN_USER_ID
        };

        SQLiteDatabase db = this.getReadableDatabase();
        //selection criteria in where clause
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        //selection arguments
        String[] selection_arg = {
                email,
                passwd
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

}
