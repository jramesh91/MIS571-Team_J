package teamj.application.appforsnaptest;

/**
 * Created by Rakesh on 12-11-2016.
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class BookingActivity extends Activity{

    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
    }

}
