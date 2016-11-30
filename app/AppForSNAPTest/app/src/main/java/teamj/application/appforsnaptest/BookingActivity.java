package teamj.application.appforsnaptest;

/**
 * Created by Rakesh on 12-11-2016.
 */
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import teamj.application.appforsnaptest.constant.DBConstant;
import teamj.application.appforsnaptest.util.DBOpenHelper;

public class BookingActivity  extends AppCompatActivity implements View.OnClickListener  {

    Button mBookButton;
    Button mCancelButton;
    public static String loginID;
    private EditText mSource;
    private EditText mDestination;
    private EditText mSourceZip;
    private EditText mDestinationZip;
    String email;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        mSource = (EditText) findViewById(R.id.source_edittext);
        mDestination = (EditText) findViewById(R.id.destination_edittext);
        mSourceZip = (EditText) findViewById(R.id.Source_Zip_edittext);
        mDestinationZip = (EditText) findViewById(R.id.DestinationZip_edittext);
        mBookButton = (Button) this.findViewById(R.id.book_btn);
        mBookButton.setOnClickListener(this);
        mCancelButton = (Button) this.findViewById(R.id.cancel_btn);
        mCancelButton.setOnClickListener(this);
        email = getIntent().getStringExtra("email");

    }

    public void onClick(View v)
    {
        int id=v.getId();

        if (id==R.id.book_btn){
            createRequest();
            Intent intent = new Intent(this, RequestStatus.class);
            intent.putExtra("email", email);
            this.startActivity(intent);
        }else if (id==R.id.goCancel_btn){
            Intent intent = new Intent(this, CancelActivity.class);
            this.startActivity(intent);
        }
    }
    public void createRequest()
    {
        String source = mSource.getText().toString();
        String destination = mDestination.getText().toString();
        String source_zip = mSourceZip.getText().toString();
        String dest_zip = mDestinationZip.getText().toString();
        String mSource = source.concat(source_zip);
        String mDestination = destination.concat(dest_zip);
        InsertIntoRequestTable(mSource,mDestination);

    }

    public void InsertIntoRequestTable(String source, String destination)
    {
        //String userRole = "Student";

        Context context = getBaseContext();
        String path = DBConstant.DATABASE_PATH + "/" + DBConstant.DATABASE_FILE;
        DBOpenHelper dbHelp = new DBOpenHelper(context,path,2);
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Long x = System.currentTimeMillis();
        Date date = new Date(x);

        String admin = getAdmin();

        Random r = new Random();

        int requestId = r.nextInt(1000);


        //Log.d("the email ID is",email);


        String[] columns = new String[] {String.valueOf(requestId), source, destination,"113",date.toString(),email, admin, "1"};
        // parameters
        Cursor res = db.rawQuery("INSERT into request values (?,?,?,?,?,?,?,?)", columns);
        //String num = checknumberofrequest();
        Log.d("The number of requests",columns.toString());
       /* while (res.moveToNext()) { // will go in this loop if there is a row with the given credentials
            userRole = res.getString(0);
        }*/
        //Log.d("The insert code is ",res.getString(0));
    }
    public String getAdmin() {
        String admin = "Student";
        Context context = getBaseContext();
        String path = DBConstant.DATABASE_PATH + "/" + DBConstant.DATABASE_FILE;
        DBOpenHelper dbHelp = new DBOpenHelper(context,path,2);
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        //String[] columns = new String[] {}; // parameters
        Cursor res = db.rawQuery("SELECT Admin_id FROM admin",null);
        while (res.moveToNext()) { // will go in this loop if there is a row with the given credentials
            admin = res.getString(0);
        }

        return admin;
    }
    public String checknumberofrequest()
    {
        String count = null;
        ArrayList array = new ArrayList<String>();
        Context context = getBaseContext();
        String path = DBConstant.DATABASE_PATH + "/" + DBConstant.DATABASE_FILE;
        DBOpenHelper dbHelp = new DBOpenHelper(context,path,2);
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        String[] columns = new String[]{email}; // parameters
        Cursor res = db.rawQuery("SELECT * FROM request where user_id = 'jramesh@wpi.edu'",columns);
        while (res.moveToNext()) { // will go in this loop if there is a row with the given credentials
            String userId = res.getString(res.getColumnIndex("user_id"));
            array.add(userId);
            //Log.d("This request is",count);
        }
        if(array.toString() != null)
        Log.d("The array is ", array.toString());

        else
        Log.d("It is null ", columns.toString());


        return columns.toString();
    }


}
