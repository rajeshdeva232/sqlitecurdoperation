package facebook.com.cog.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.security.PublicKey;

public class Db_Controller extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public Db_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "TEST.db", factory, version);
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STUDENTS( ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT UNIQUE, LASTNAME TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS STUDENTS;");
        onCreate(db);
    }
    public void insert(String firstName, String lastName)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME", firstName);
        contentValues.put("LASTNAME", lastName);
        db.insertOrThrow("STUDENTS","",contentValues);

    }
    public void delete(String firstName)
    {

        db.delete("STUDENTS","FIRSTNAME='"+firstName+"'",null);
    }
    public void update(String oldFirstName, String newFirstName)
    {
        db.execSQL("UPDATE STUDENTS SET FIRSTNAME='"+newFirstName+"' WHERE FIRSTNAME='"+oldFirstName+"'");
    }
    public void listAll(TextView textView)
    {
        textView.setText("");
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM STUDENTS",null);
        while (cursor.moveToNext())
        {
            textView.append(cursor.getString(0)+" "+cursor.getString(1)+"  "+cursor.getString(2)+"\n");
        }
    }
}
