package teamj.application.appforsnaptest;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;
import java.util.Random;

import teamj.application.appforsnaptest.constant.DBConstant;
import teamj.application.appforsnaptest.util.DBOpenHelper;

/**
 * Created by Rakesh on 30-11-2016.
 */

public class RequestStatus extends AppCompatActivity implements View.OnClickListener  {

Button mCancel;
    String email;
    String status;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_status);

        mCancel = (Button) this.findViewById(R.id.Cancel_Request);
        mCancel.setOnClickListener(this);
        email = getIntent().getStringExtra("email");


    }


    public void onClick(View v)
    {
        int id=v.getId();

        if (id==R.id.Cancel_request){
            cancelRequest();
            Intent intent = new Intent(this, BookingActivity.class);
            this.startActivity(intent);
        }else if (id==R.id.goCancel_btn){
            Intent intent = new Intent(this, CancelActivity.class);
            this.startActivity(intent);
        }
    }

    public void cancelRequest()
    {
        Context context = getBaseContext();
        String path = DBConstant.DATABASE_PATH + "/" + DBConstant.DATABASE_FILE;
        DBOpenHelper dbHelp = new DBOpenHelper(context,path,2);
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Long x = System.currentTimeMillis();
        Date date = new Date(x);





        String[] columns = new String[] {email};
        // parameters
        Cursor res = db.rawQuery("Delete from request where R_id = ?", columns);

        //String num = checknumberofrequest();
        //Log.d("The number of requests",num);
       /* while (res.moveToNext()) { // will go in this loop if there is a row with the given credentials
            userRole = res.getString(0);
        }*/
        //Log.d("The insert code is ",res.getString(0));

    }

}
