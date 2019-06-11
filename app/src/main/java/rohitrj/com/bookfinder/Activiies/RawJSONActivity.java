package rohitrj.com.bookfinder.Activiies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import rohitrj.com.bookfinder.R;

public class RawJSONActivity extends AppCompatActivity {

    String json;

    TextView textViewRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_json);
        setTitle("Raw JSON");

        json=getIntent().getStringExtra("RES");

        textViewRes=findViewById(R.id.textViewRes);

        textViewRes.setText(json);

    }
}
