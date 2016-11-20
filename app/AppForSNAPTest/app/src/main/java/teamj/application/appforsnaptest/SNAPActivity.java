package teamj.application.appforsnaptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import teamj.application.appforsnaptest.util.*;
import teamj.application.appforsnaptest.BookingActivity;

/**
 * Created by Rakesh on 12-11-2016.
 */

public class SNAPActivity extends AppCompatActivity implements View.OnClickListener {

    Button bookingBtn,CancelBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap_layout);
        bookingBtn = (Button)this.findViewById(R.id.goBook_btn);
        bookingBtn.setOnClickListener(this);
        CancelBtn = (Button)this.findViewById(R.id.goCancel_btn);
        CancelBtn.setOnClickListener(this);


        //copy database file
        try {
            DBOperator.copyDB(getBaseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v)
    {
        int id=v.getId();

        if (id==R.id.goBook_btn){
            Intent intent = new Intent(this, BookingActivity.class);
            this.startActivity(intent);
        }else if (id==R.id.goCancel_btn){
            Intent intent = new Intent(this, CancelActivity.class);
            this.startActivity(intent);
        }
    }
}
