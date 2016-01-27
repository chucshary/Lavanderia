package sharychuc.lavanderia;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import sharychuc.lavanderia.Clases.Ubicacion;
import sharychuc.lavanderia.DBSQLite.AdminSQLiteOpenHelper;

public class ConfigurationLaundry extends AppCompatActivity {
    private Button btnCode;
    private Button btnLaundry;
    private AdminSQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_laundry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Ubicacion ubicacion= new Ubicacion(this);
        ubicacion.informationLocation();
        dbHelper = new AdminSQLiteOpenHelper(this,
                "DBLaundry", null, 1);
        db = dbHelper.getWritableDatabase();

        btnCode = (Button) findViewById(R.id.btn_code);
        btnLaundry = (Button) findViewById(R.id.btn_laundry);

        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Code.class));
                ConfigurationLaundry.this.finish();
            }
        });

        btnLaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterLaundry.class));
                ConfigurationLaundry.this.finish();
            }
        });
    }

}
