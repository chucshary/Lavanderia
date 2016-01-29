package sharychuc.lavanderia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Arrays;

import sharychuc.lavanderia.DBSQLite.Querys;
import sharychuc.lavanderia.DBSQLite.Tables;
import sharychuc.lavanderia.SharedPreferences.Preferences;

public class PricesActivity extends AppCompatActivity {
    private Preferences preferences;
    private Tables tables;
    private Querys querys;
    private String columnsTable;
    private String values;
    private EditText article;
    private EditText price;
    private boolean cancel = false;
    private View focusView = null;
    private String[] Laundry = {"Id", "Name", "Latitude", "Longitude", "CountryCode", "Country", "State", "City", "Address"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        article = (EditText) findViewById(R.id.txt_article);
        price = (EditText) findViewById(R.id.txt_price);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(article.getText().toString())) {
                    article.setError(getString(R.string.error_field_required));
                    focusView = article;
                    cancel = true;
                } else if (TextUtils.isEmpty(price.getText().toString())) {
                    price.setError(getString(R.string.error_field_required));
                    focusView = price;
                    cancel = true;
                } else {
                    tables = new Tables();
                    columnsTable = Arrays.toString(tables.getLaundry()).replaceAll("\\[|\\]", "");
                    preferences = new Preferences(PricesActivity.this, columnsTable, "", "Laundry");
                    values = Arrays.toString(preferences.loadPreferences()).replaceAll("\\[,|\\]", "");
                    Log.d("EXAMPLE", values);
                    querys = new Querys(PricesActivity.this, "Laundry");
                    querys.insert(Laundry, values);
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    PricesActivity.this.finish();
                }
                if (cancel) {
                    focusView.requestFocus();
                }

            }
        });
    }

}
