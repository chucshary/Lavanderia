package sharychuc.lavanderia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.UUID;

import sharychuc.lavanderia.SharedPreferences.Preferences;

public class RegisterLaundry extends AppCompatActivity {
    private FloatingActionButton fab;
    private EditText nameLaundry;
    private boolean cancel = false;
    private View focusView = null;
    private Preferences preferences;
    private String uuid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_laundry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nameLaundry = (EditText) findViewById(R.id.txt_nameLaundry);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(nameLaundry.getText().toString())) {
                    nameLaundry.setError(getString(R.string.error_field_required));
                    focusView = nameLaundry;
                    cancel = true;
                } else {
                    uuid = UUID.randomUUID().toString();
                    preferences = new Preferences(getApplicationContext(), "Id,Name", uuid + "," + nameLaundry.getText().toString().trim(), "Laundry");
                    preferences.savePreferences();
                    startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                    RegisterLaundry.this.finish();
                }
                if (cancel) {
                    focusView.requestFocus();
                }
            }
        });
    }

}
