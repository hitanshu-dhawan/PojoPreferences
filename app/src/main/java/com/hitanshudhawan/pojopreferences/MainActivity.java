package com.hitanshudhawan.pojopreferences;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText booleanEditText;
    private EditText BooleanEditText;
    private EditText floatEditText;
    private EditText FloatEditText;
    private EditText intEditText;
    private EditText IntegerEditText;
    private EditText longEditText;
    private EditText LongEditText;
    private EditText StringEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        booleanEditText = findViewById(R.id.boolean_edit_text);
        BooleanEditText = findViewById(R.id.Boolean_edit_text);
        floatEditText = findViewById(R.id.float_edit_text);
        FloatEditText = findViewById(R.id.Float_edit_text);
        intEditText = findViewById(R.id.int_edit_text);
        IntegerEditText = findViewById(R.id.Integer_edit_text);
        longEditText = findViewById(R.id.long_edit_text);
        LongEditText = findViewById(R.id.Long_edit_text);
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
        DataPref.setB(this, Boolean.valueOf(BooleanEditText.getText().toString()));
        DataPref.setC(this, Float.valueOf(floatEditText.getText().toString()));
        DataPref.setD(this, Float.valueOf(FloatEditText.getText().toString()));
        DataPref.setE(this, Integer.valueOf(intEditText.getText().toString()));
        DataPref.setF(this, Integer.valueOf(IntegerEditText.getText().toString()));
        DataPref.setG(this, Long.valueOf(longEditText.getText().toString()));
        DataPref.setH(this, Long.valueOf(LongEditText.getText().toString()));
        DataPref.setI(this, StringEditText.getText().toString());

        refresh();
    }

    private void refresh() {
        booleanEditText.setText(String.valueOf(DataPref.getA(this)));
        BooleanEditText.setText(String.valueOf(DataPref.getB(this)));
        floatEditText.setText(String.valueOf(DataPref.getC(this)));
        FloatEditText.setText(String.valueOf(DataPref.getD(this)));
        intEditText.setText(String.valueOf(DataPref.getE(this)));
        IntegerEditText.setText(String.valueOf(DataPref.getF(this)));
        longEditText.setText(String.valueOf(DataPref.getG(this)));
        LongEditText.setText(String.valueOf(DataPref.getH(this)));
        StringEditText.setText(DataPref.getI(this));
    }
}

