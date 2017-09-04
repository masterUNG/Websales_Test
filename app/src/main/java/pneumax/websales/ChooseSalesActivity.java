package pneumax.websales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ChooseSalesActivity extends AppCompatActivity {

    private Employees mEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_sales);

        // get inbound intent
        getInboundIntent();


    }   // onCreate

    private void getInboundIntent() {
        String tag = "4SepV1";
        Intent inboundIntent = getIntent();
        mEmployees = (Employees) inboundIntent.getParcelableExtra(Employees.TABLE_NAME);
        Log.d(tag, "STFFULLNAME ==> " + mEmployees.STFfullname);


    }

}   // Main Class
