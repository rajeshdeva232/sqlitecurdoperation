package facebook.com.cog.database;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstName,lastName;
    TextView textView;
    Button insert,delete,update,list;
    Db_Controller  db_controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.etFirst);
        lastName = findViewById(R.id.etLast);
        textView = findViewById(R.id.tv);
        insert = findViewById(R.id.btnAdd);
        delete = findViewById(R.id.btnDelete);
        update = findViewById(R.id.btnUpdate);
        list = findViewById(R.id.btnList);
        db_controller = new Db_Controller(this,"",null,1);
        performAction();
    }

    private void performAction() {
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    db_controller.insert(firstName.getText().toString(),lastName.getText().toString());
                } catch (SQLiteException e) {
                    Toast.makeText(MainActivity.this,"Already Exist",Toast.LENGTH_LONG).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db_controller.delete(firstName.getText().toString());
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog= new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Enter the new  firsName");

                final EditText newFirstName =new EditText(MainActivity.this);
                dialog.setView(newFirstName);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db_controller.update(firstName.getText().toString(),newFirstName.getText().toString());
                    }
                });
                dialog.show();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db_controller.listAll(textView);

            }
        });

    }
}
