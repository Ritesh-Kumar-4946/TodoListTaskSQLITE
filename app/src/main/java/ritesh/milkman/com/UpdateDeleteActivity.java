package ritesh.milkman.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDeleteActivity extends AppCompatActivity {

    private UserModel userModel;
    private EditText etname, ethobby;
    private DBHelper DBHelper;

    CardView btnupdate, btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        Intent intent = getIntent();
        userModel = (UserModel) intent.getSerializableExtra("user");

        DBHelper = new DBHelper(this);

        etname = (EditText) findViewById(R.id.etname);
        ethobby = (EditText) findViewById(R.id.ethobby);
        btndelete = (CardView) findViewById(R.id.btndelete);
        btnupdate = (CardView) findViewById(R.id.btnupdate);

        etname.setText(userModel.getName());
        ethobby.setText(userModel.getHobby());

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.updateUser(userModel.getId(),etname.getText().toString(),ethobby.getText().toString());
                Toast.makeText(UpdateDeleteActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDeleteActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.deleteUSer(userModel.getId());
                Toast.makeText(UpdateDeleteActivity.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDeleteActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}
