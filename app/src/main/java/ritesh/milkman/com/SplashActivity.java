package ritesh.milkman.com;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.hujiaweibujidao.wava.Techniques;
import com.github.hujiaweibujidao.wava.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ritesh on 8/8/17.
 */

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.banner)
    ImageView Iv_banner;

    @BindView(R.id.tv_banner)
    TextView Tv_banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        YoYo.with(Techniques.FadeIn).duration(1500)
                .interpolate(new AccelerateDecelerateInterpolator())
                .playOn(Iv_banner);

        YoYo.with(Techniques.FadeIn).duration(1500)
                .interpolate(new AccelerateDecelerateInterpolator())
                .playOn(Tv_banner);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent Gomainscreen = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Gomainscreen);
                finish();

            }

        }, 3000);


    }


}
