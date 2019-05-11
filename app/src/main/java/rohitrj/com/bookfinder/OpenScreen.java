package rohitrj.com.bookfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OpenScreen extends AppCompatActivity {

    private TextView textViewTitle,textViewInfo;
    private ImageView logo;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_screen);

        textViewInfo=findViewById(R.id.textViewInfo);
        textViewTitle=findViewById(R.id.textViewTitle);
        logo=findViewById(R.id.imageViewLogo);
        buttonNext=findViewById(R.id.buttonNext);

        Animation animation_mid= AnimationUtils.loadAnimation(this,R.anim.ani_middle);
        textViewTitle.startAnimation(animation_mid);
        logo.setAnimation(animation_mid);

        Animation animation_end= AnimationUtils.loadAnimation(this,R.anim.ani_end);
        textViewInfo.startAnimation(animation_end);
        buttonNext.setAnimation(animation_end);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(OpenScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
