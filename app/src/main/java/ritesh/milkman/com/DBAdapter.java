package ritesh.milkman.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Oclemmy on 5/2/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class DBAdapter {

    Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context c) {
        this.c = c;
        helper=new DBHelper(c);
    }

    //OPEN DB
    public void openDB()
    {
        try
        {
           db=helper.getWritableDatabase();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //CLOSE
    public void closeDB()
    {
        try
        {
            helper.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //INSERT DATA
    public boolean add(String name)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(DBHelper.KEY_FIRSTNAME, name);

            db.insert(DBHelper.TABLE_USER, DBHelper.KEY_ID, cv);

            return true;

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    //RETRIEVE DATA AND FILTER
    public Cursor retrieve(String searchTerm)
    {
        String[] columns={DBHelper.KEY_ID,DBHelper.KEY_FIRSTNAME};
        Cursor c=null;

        if(searchTerm != null && searchTerm.length()>0)
        {
            String sql="SELECT * FROM "+DBHelper.TABLE_USER+" WHERE "+DBHelper.KEY_FIRSTNAME+" LIKE '%"+searchTerm+"%'";
            c=db.rawQuery(sql,null);
            return c;

        }

        c=db.query(DBHelper.TABLE_USER,columns,null,null,null,null,null);
        return c;
    }


}











