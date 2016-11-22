package teamj.application.appforsnaptest;

/**
 * Created by Rakesh on 12-11-2016.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import teamj.application.appforsnaptest.constant.DBConstant;
import teamj.application.appforsnaptest.util.DBOpenHelper;

public class BookingActivity  extends AppCompatActivity implements View.OnClickListener  {

    Button mBookButton;
    Button mCancelButton;
    private EditText mSource;
    private EditText mDestination;
    private EditText mSourceZip;
    private EditText mDestinationZip;

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

    }

    public void onClick(View v)
    {
        int id=v.getId();

        if (id==R.id.book_btn){
            createRequest();
            Intent intent = new Intent(this, BookingActivity.class);
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
        String mSource = source + source_zip;
        String mDestination = destination + dest_zip;
        InsertIntoRequestTable(mSource,mDestination);

    }
    public void InsertIntoRequestTable(String source, String destination)
    {
        String userRole = "Student";

        Context context = getBaseContext();
        String path = DBConstant.DATABASE_PATH + "/" + DBConstant.DATABASE_FILE;
        DBOpenHelper dbHelp = new DBOpenHelper(context,path,2);
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        String[] columns = new String[] {"3", source, destination,  }; // parameters
        Cursor res = db.rawQuery("INSERT Into request values (?,?,?,?user_id=? AND password=? ", columns);
        while (res.moveToNext()) { // will go in this loop if there is a row with the given credentials
            userRole = res.getString(0);
        }
    }


}
