package teamj.application.appforsnaptest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Rakesh on 22-11-2016.
 */

public class AdminActivity extends Activity implements View.OnClickListener{
    Button pending;
    Button approved;
    Button rejected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        pending = (Button) this.findViewById(R.id.pending_btn);
        approved = (Button) this.findViewById(R.id.approved_btn);
        rejected = (Button) this.findViewById(R.id.reject_btn);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_first);
    }
    public void onClick(View v)
    {
        int id=v.getId();

        if (id==R.id.pending_btn){

            Intent intent = new Intent(this, BookingActivity.class);
            this.startActivity(intent);
        }else if (id==R.id.approved_btn){
            Intent intent = new Intent(this, CancelActivity.class);
            this.startActivity(intent);
        }
        else if (id==R.id.reject_btn){
            Intent intent = new Intent(this, CancelActivity.class);
            this.startActivity(intent);
        }
    }
}
