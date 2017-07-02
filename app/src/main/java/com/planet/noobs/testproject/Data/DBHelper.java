package com.planet.noobs.testproject.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.planet.noobs.testproject.Model.Books;
import com.planet.noobs.testproject.Model.Lectures;
import com.planet.noobs.testproject.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rio on 14/6/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = DBHelper.class.getSimpleName();

    private static final String APPROVED = "1";
    private static final String UNAPPROVED = "0";
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

    // Admin table
    private static final String TABLE_ADMIN = "admin";
    //column's name
    private static final String COLUMN_ADMIN_ID = "admin_id";
    private static final String COLUMN_ADMIN_APPROVAL = "admin_approval";
    private static final String COLUMN_ADMIN_NAME = "admin_name";
    private static final String COLUMN_ADMIN_CONTACT = "admin_contact";
    private static final String COLUMN_ADMIN_EMAIL = "admin_email";
    private static final String COLUMN_ADMIN_PASSWORD = "admin_password";
    //create table query
    private static final String CREATE_ADMIN_TABLE = "CREATE TABLE " + TABLE_ADMIN + "(" +
            COLUMN_ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_ADMIN_APPROVAL + " NUMBER DEFAULT 0, " +
            COLUMN_ADMIN_NAME + " TEXT, " +
            COLUMN_ADMIN_CONTACT + " NUMBER, " +
            COLUMN_ADMIN_EMAIL + " TEXT, " +
            COLUMN_ADMIN_PASSWORD + " TEXT" + ")";

    //DROP TABLE IF EXISTS
    private static final String DROP_ADMIN_TABLE = "DROP TABLE IF EXISTS " + TABLE_ADMIN;


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
        db.execSQL(CREATE_ADMIN_TABLE);
        addAdmin(db, addFirstAdmin());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop student table if exist
//        db.execSQL(DROP_STUDENT_TABLE);
        onCreate(db);
    }

    // Adds admin at the creation of the tables in Oncreate method
    private User addFirstAdmin() {
        User user = new User();
        user.setName("Admin");
        user.setEmail("admin@admin.com");
        user.setContact(Long.parseLong("9586496666"));
        user.setPasswd("admin");

        return user;
    }

    /**
     * resets password for user
     * @param user
     */
    public void resetStudentPasswd(User user) {
        SQLiteDatabase db = getWritableDatabase();
        user = new User();
        String email = user.getEmail();
        String newPasswd = user.getPasswd();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_PASSWORD,newPasswd);

        String selection = COLUMN_STUDENT_EMAIL + " = ?" + " AND "
                + COLUMN_STUDENT_APPROVAL + " = " + APPROVED;
        String[] selection_args = {
                email
        };

        db.update(TABLE_STUDENT,
                values,
                selection,
                selection_args
                );
        db.close();
    }

    public void resetTeacherPasswd(User user) {
        SQLiteDatabase db = getWritableDatabase();
        user = new User();
        String email = user.getEmail();
        String newPasswd = user.getPasswd();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TEACHER_PASSWORD,newPasswd);

        String selection = COLUMN_TEACHER_EMAIL + " = ?" + " AND "
                + COLUMN_TEACHER_APPROVAL + " = " + APPROVED;
        String[] selection_args = {
                email
        };

        db.update(TABLE_TEACHER,
                values,
                selection,
                selection_args
        );
        db.close();
    }

    public void resetDeptPasswd(User user) {
        SQLiteDatabase db = getWritableDatabase();
        user = new User();
        String email = user.getEmail();
        String newPasswd = user.getPasswd();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DEPT_PASSWORD,newPasswd);

        String selection = COLUMN_DEPT_EMAIL + " = ?" + " AND "
                + COLUMN_DEPT_APPROVAL + " = " + APPROVED;
        String[] selection_args = {
                email
        };

        db.update(TABLE_DEPT,
                values,
                selection,
                selection_args
        );
        db.close();
    }

    /**
     * add lectures
     *
     * @param lectures
     */
    public void addLec(Lectures lectures) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LEC_SUBJECT, lectures.getLecTitle());
        values.put(COLUMN_TIME, lectures.getLecDateTime());

        db.insert(TABLE_LECTURES, null, values);
        Log.v(DBHelper.class.getSimpleName(), "addlec() is Working!");
        db.close();
    }

    /**
     * Adds student
     *
     * @param user
     */
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

    /**
     * Adds departement member
     *
     * @param user
     */
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

    /**
     * Adds teacher
     *
     * @param user
     */
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
     * adds admin
     *
     * @param user
     */
    public void addAdmin(SQLiteDatabase db, User user) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_ADMIN_NAME, user.getName());
        values.put(COLUMN_ADMIN_EMAIL, user.getEmail());
        values.put(COLUMN_ADMIN_PASSWORD, user.getPasswd());
        values.put(COLUMN_ADMIN_CONTACT, user.getContact());

        db.insert(TABLE_ADMIN, null, values);
    }

    /**
     * Delete's lecture
     *
     * @param lec
     */
    public void deleteLec(Lectures lec) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_LECTURES, COLUMN_LEC_ID + " = ?",
                new String[]{String.valueOf(lec.getLecId())});
        db.close();
    }

    public void deleteStudent(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_STUDENT, COLUMN_STUDENT_EMAIL + " = ?",
                new String[]{user.getEmail()});
        db.close();
    }

    public void deleteTeacher(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_TEACHER, COLUMN_TEACHER_EMAIL + " = ?",
                new String[]{user.getEmail()});
        db.close();
    }

    public void deleteDept(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_DEPT, COLUMN_DEPT_EMAIL + " = ?",
                new String[]{user.getEmail()});
        db.close();
    }

    /**
     * Get all lecture table record
     * @return
     */
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

    /**
     * Get all books list
     *
     * @return booksList
     */
    public List getAllBooks() {
        String[] COLUMNS = {
                COLUMN_BOOK_ID,
                COLUMN_BOOK_AUTHOR,
                COLUMN_BOOK_TITLE,
        };

        List<Books> bookList = new ArrayList<>();
        String sortOrder = COLUMN_BOOK_TITLE + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_BOOKS,
                COLUMNS,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()) {
            do {
                Books books = new Books();
                books.setBookId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_ID))));
                books.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_TITLE)));
                books.setAuthor(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_AUTHOR)));
                bookList.add(books);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bookList;
    }

    public List getAllIssuedBooks() {
        String[] COLUMNS = {
                COLUMN_BOOK_ID,
                COLUMN_BOOK_AUTHOR,
                COLUMN_BOOK_TITLE,
                COLUMN_BOOK_ISSUE_DATE
        };

        List<Books> bookList = new ArrayList<>();
        String sortOrder = COLUMN_BOOK_TITLE + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_BOOKS,
                COLUMNS,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()) {
            do {
                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_ISSUED))) == 1) {
                    Books books = new Books();
                    books.setBookId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_ID))));
                    books.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_TITLE)));
                    books.setAuthor(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_AUTHOR)));
                    books.setIssueDate(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_ISSUE_DATE)));
                    bookList.add(books);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bookList;
    }

    /**
     * checks existing email
     * @param email
     * @return
     */
    public boolean checkStudentEmail(String email) {
        //columns to be fetched
        String[] columns = {
                COLUMN_STUDENT_ID
        };

        SQLiteDatabase db = this.getReadableDatabase();
        //selection criteria in where clause
        String selection = COLUMN_STUDENT_EMAIL + " = ?" + " AND " + COLUMN_STUDENT_APPROVAL + " = "+APPROVED;
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

    public boolean checkTeacherEmail(String email) {
        //columns to be fetched
        String[] columns = {
                COLUMN_TEACHER_ID
        };

        SQLiteDatabase db = this.getReadableDatabase();
        //selection criteria in where clause
        String selection = COLUMN_TEACHER_EMAIL + " = ?" + " AND " + COLUMN_TEACHER_APPROVAL + " = "+APPROVED;
        //selection arguments
        String[] selection_arg = {
                email
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

    public boolean checkDeptEmail(String email) {
        //columns to be fetched
        String[] columns = {
                COLUMN_DEPT_ID
        };

        SQLiteDatabase db = this.getReadableDatabase();
        //selection criteria in where clause
        String selection = COLUMN_DEPT_EMAIL + " = ?" + " AND " + COLUMN_DEPT_APPROVAL + " = "+APPROVED;
        //selection arguments
        String[] selection_arg = {
                email
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

    public boolean checkAdminEmail(String email) {
        //columns to be fetched
        String[] columns = {
                COLUMN_ADMIN_ID
        };

        SQLiteDatabase db = this.getReadableDatabase();
        //selection criteria in where clause
        String selection = COLUMN_ADMIN_EMAIL + " = ?";
        //selection arguments
        String[] selection_arg = {
                email
        };

        Cursor cursor = db.query(
                TABLE_ADMIN, // table name
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

    /**
     * checks if user exist
     ** @param email
     * @param passwd
     * @return
     */
    public boolean checkStudent(String email, String passwd) {
        //columns to be fetched
        String[] columns = {
                COLUMN_STUDENT_ID,
        };

        SQLiteDatabase db = this.getReadableDatabase();
        //selection criteria in where clause
        String selection = COLUMN_STUDENT_EMAIL + " = ?" + " AND " +
                COLUMN_STUDENT_PASSWORD + " = ?" + " AND " +
                COLUMN_STUDENT_APPROVAL + " = "+APPROVED;
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
                COLUMN_DEPT_APPROVAL + " = "+APPROVED;
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
                COLUMN_TEACHER_PASSWORD + " = ?" + " AND " +
                COLUMN_TEACHER_APPROVAL + " = "+APPROVED;
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

    public boolean checkAdmin(String email, String passwd) {
        //columns to be fetched
        String[] columns = {
                COLUMN_ADMIN_ID
        };


        SQLiteDatabase db = this.getReadableDatabase();
        //selection criteria in where clause
        String selection = COLUMN_ADMIN_EMAIL + " = ?" + " AND " +
                COLUMN_ADMIN_PASSWORD + " = ?";
        //selection arguments
        String[] selection_arg = {
                email,
                passwd,
        };

        Cursor cursor = db.query(
                TABLE_ADMIN, // table name
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

    /**
     * retrive's all the unapproved users
     * @return
     */
    public List<User> getAllUnapprovedStudent() {
        //columns to be fetched
        String[] columns = {
                COLUMN_STUDENT_EMAIL,
        };
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> userList = new ArrayList<User>();

        String selection = COLUMN_STUDENT_APPROVAL + " = "+UNAPPROVED;
        String sortOrder = COLUMN_STUDENT_EMAIL + " ASC";
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
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_EMAIL)));
                user.setUserType("Student");
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    public List<User> getAllUnapprovedTeacher() {
        //columns to be fetched
        String[] columns = {
                COLUMN_TEACHER_EMAIL,
        };
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> userList = new ArrayList<User>();

        String selection = COLUMN_TEACHER_APPROVAL + " = "+UNAPPROVED;
        String sortOrder = COLUMN_TEACHER_EMAIL + " ASC";
        Cursor cursor = db.query(
                TABLE_TEACHER,
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
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_TEACHER_EMAIL)));
                user.setUserType("Teacher");
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    public List<User> getAllUnapprovedDept() {
        //columns to be fetched
        String[] columns = {
                COLUMN_DEPT_EMAIL,
        };
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> userList = new ArrayList<User>();

        String selection = COLUMN_DEPT_APPROVAL + " = "+ UNAPPROVED;
        String sortOrder = COLUMN_DEPT_EMAIL + " ASC";
        Cursor cursor = db.query(
                TABLE_DEPT,
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
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_DEPT_EMAIL)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    /**
     * approve function for admin's
     * @param user
     */
    public void approveStudent(User user){
        //open database with write permission
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_APPROVAL, APPROVED);
        String email = user.getEmail();
        String selection = COLUMN_STUDENT_EMAIL + " = ?";
        //selection arguments
        String[] selection_arg = {
                email
        };

        db.update(TABLE_STUDENT,
                values,
                selection,
                selection_arg);
        db.close();
    }

    public void approveTeacher(User user) {
        //open database with write permission
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TEACHER_APPROVAL, APPROVED);
        String email = user.getEmail();

        String selection = COLUMN_TEACHER_EMAIL + " = ?";
        //selection arguments
        String[] selection_arg = {
                email
        };

        db.update(TABLE_TEACHER,
                values,
                selection,
                selection_arg);
        db.close();
    }

    public void approveDept(User user) {
        //open database with write permission
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DEPT_APPROVAL, APPROVED);
        String email = user.getEmail();
        String selection = COLUMN_DEPT_EMAIL + " = ?";
        //selection arguments
        String[] selection_arg = {
                email
        };

        db.update(TABLE_DEPT,
                values,
                selection,
                selection_arg);

        Log.v(LOG_TAG, "approveDept() working!!");
        db.close();
    }

}
