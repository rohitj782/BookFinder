package rohitrj.com.bookfinder.Activiies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import rohitrj.com.bookfinder.R;

public class OpenScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_screen);

        TextView textViewInfo = findViewById(R.id.textViewInfo);
        TextView textViewTitle = findViewById(R.id.textViewTitle);
        ImageView logo = findViewById(R.id.imageViewLogo);
        Button buttonNext = findViewById(R.id.buttonNext);

        Animation animation_mid = AnimationUtils.loadAnimation(this, R.anim.ani_top);
        textViewTitle.startAnimation(animation_mid);
        logo.setAnimation(animation_mid);

        Animation animation_end = AnimationUtils.loadAnimation(this, R.anim.ani_top);
        textViewInfo.startAnimation(animation_end);
        buttonNext.setAnimation(animation_end);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(OpenScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
