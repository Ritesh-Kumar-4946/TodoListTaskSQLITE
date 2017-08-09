package ritesh.milkman.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.hujiaweibujidao.wava.Techniques;
import com.github.hujiaweibujidao.wava.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edt_todo_tag)
    EditText Edt_todo_tag;

    @BindView(R.id.edt_todo_headline)
    EditText Edt_todo_headline;

    @BindView(R.id.cv_et_todo_tag)
    CardView Cv_et_todo_tag;

    @BindView(R.id.cv_et_todo_headline)
    CardView Cv_et_todo_headline;

    @BindView(R.id.cv_save)
    CardView Cv_save;

    @BindView(R.id.tv_listall)
    TextView Tv_listall;


    private DBHelper DBHelper;


    String strPut_tag = "", strPut_tittle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DBHelper = new DBHelper(this);

        Cv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean iserror = false;

                strPut_tag = Edt_todo_tag.getText().toString().trim();
                strPut_tittle = Edt_todo_headline.getText().toString().trim();

                Log.e(" Save Fields data :", "\n"
                        + "strPut_tag :" + "" + strPut_tag + "\n"
                        + "strPut_tittle :" + "" + strPut_tittle + "\n");


                if (strPut_tag.equals("")) {
                    iserror = true;
                    Log.e(" Error :", "Ok");
                    v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                    /**************** Start Animation ****************/
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .playOn(Cv_et_todo_tag);
                    /**************** End Animation ****************/

                    Toast.makeText(getApplicationContext(),
                            "Please enter your Tag", Toast.LENGTH_SHORT).show();


                } else if (strPut_tittle.equals("")) {
                    iserror = true;
                    Log.e(" Error :", "Ok");
                    v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                    /**************** Start Animation ****************/
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .playOn(Cv_et_todo_headline);
                    /**************** End Animation ****************/

                    Toast.makeText(getApplicationContext(),
                            "Please enter your Tittle", Toast.LENGTH_SHORT).show();


                }
                if (!iserror) {
                    Log.e("No Error :", "Ok");
                    v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                    DBHelper.addUserDetail(strPut_tag, strPut_tittle);
                    Edt_todo_tag.setText("");
                    Edt_todo_headline.setText("");
                    Toast.makeText(MainActivity.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();


                }

            }
        });

        Tv_listall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetAllUsersActivity.class);
                startActivity(intent);
            }
        });

    }


}
