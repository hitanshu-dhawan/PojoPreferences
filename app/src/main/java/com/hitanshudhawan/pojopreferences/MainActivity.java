package com.hitanshudhawan.pojopreferences;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText booleanEditText;
    private EditText floatEditText;
    private EditText intEditText;
    private EditText longEditText;
    private EditText StringEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        booleanEditText = findViewById(R.id.boolean_edit_text);
        floatEditText = findViewById(R.id.float_edit_text);
        intEditText = findViewById(R.id.int_edit_text);
        longEditText = findViewById(R.id.long_edit_text);
        StringEditText = findViewById(R.id.String_edit_text);

        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_save:
                save();
                Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_refresh:
                refresh();
                Toast.makeText(this, "refreshed", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void save() {
        DataPref.setA(this, Boolean.valueOf(booleanEditText.getText().toString()));
        DataPref.setB(this, Float.valueOf(floatEditText.getText().toString()));
        DataPref.setC(this, Integer.valueOf(intEditText.getText().toString()));
        DataPref.setD(this, Long.valueOf(longEditText.getText().toString()));
        DataPref.setE(this, StringEditText.getText().toString());

        refresh();
    }

    private void refresh() {
        booleanEditText.setText(String.valueOf(DataPref.getA(this)));
        floatEditText.setText(String.valueOf(DataPref.getB(this)));
        intEditText.setText(String.valueOf(DataPref.getC(this)));
        longEditText.setText(String.valueOf(DataPref.getD(this)));
        StringEditText.setText(DataPref.getE(this));
    }
}

