package sharychuc.lavanderia.DBSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Shary on 28/01/2016.
 */
public class Querys {
    private Context context;
    private String tableName;
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;
    private String[] values;
    private ContentValues register;
    String selectQuery;

    public Querys(Context context, String tableName) {
        this.context = context;
        this.tableName = tableName;
        admin = new AdminSQLiteOpenHelper(this.context,
                this.tableName, null, 1);
    }

    public void insert(String[] columns, String valuesPreferences) {
        values = valuesPreferences.split(",");
        bd = admin.getWritableDatabase();
        register = new ContentValues();
        for (int i = 0; i < columns.length; i++) {
            register.put(columns[i], values[i]);
        }
        bd.insert(this.tableName, null, register);
        Log.d("INSERT", "Se inserto correctmente");
        bd.close();
    }

    public void truncate() {
        selectQuery = "delete from " + this.tableName;
        bd = admin.getWritableDatabase();
        bd.execSQL(selectQuery);
        bd.close();
    }
}
