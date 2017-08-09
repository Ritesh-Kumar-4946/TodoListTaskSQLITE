package ritesh.milkman.com;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetAllUsersActivity extends AppCompatActivity {

    @BindView(R.id.lv)
    ListView listView;

    @BindView(R.id.sv)
    SearchView sv;

    private Filter planetFilter;

    private ArrayList<UserModel> userModelArrayList = new ArrayList<>();
    ;
    private CustomAdapter customAdapter;
    private DBHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_users);
        ButterKnife.bind(this);

        DBHelper = new DBHelper(this);

        userModelArrayList = DBHelper.getAllUsers();

        customAdapter = new CustomAdapter(GetAllUsersActivity.this, userModelArrayList);

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.e("List click :", "ok " + " Position :" + userModelArrayList.get(position).getId());
                Intent intent = new Intent(GetAllUsersActivity.this, UpdateDeleteActivity.class);
                intent.putExtra("user", userModelArrayList.get(position));
                startActivity(intent);
            }
        });

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getPlanets(newText);
                return false;
            }
        });


    }

    private void getPlanets(String searchTerm) {
        userModelArrayList.clear();

        DBAdapter db = new DBAdapter(this);
        db.openDB();
//        UserModel p = new UserModel();
        UserModel p = null;
        Cursor c = db.retrieve(searchTerm);


        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            String hobby = c.getString(2);

            p = new UserModel();
            p.setId(id);
            p.setName(name);
            p.setHobby(hobby);

            userModelArrayList.add(p);
        }

        db.closeDB();

        listView.setAdapter(customAdapter);

    }

    private class CustomAdapter extends BaseAdapter {

        Context context;
        ArrayList<UserModel> userModelArrayList;

        public CustomAdapter(Context context, ArrayList<UserModel> userModelArrayList) {

            this.context = context;
            this.userModelArrayList = userModelArrayList;
        }


        @Override
        public int getCount() {
            return userModelArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return userModelArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.card_model, null, true);

                holder.tvname = (TextView) convertView.findViewById(R.id.tv_tittle);
                holder.tvcountry = (TextView) convertView.findViewById(R.id.tv_text_name);


                convertView.setTag(holder);
            } else {
                // the getTag returns the viewHolder object set as a tag to the view
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvname.setText("Tag: " + userModelArrayList.get(position).getName());
            holder.tvcountry.setText("Tittle: " + userModelArrayList.get(position).getHobby());

            return convertView;
        }

        public class ViewHolder {

            TextView tvname, tvcountry;
        }

    }

}
