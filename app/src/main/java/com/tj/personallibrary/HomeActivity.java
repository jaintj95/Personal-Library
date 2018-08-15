package com.tj.personallibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private Button mAddButton;
    private EditText mBookNameField;
    private EditText mAuthorField;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAddButton = findViewById(R.id.button_add_book);
        mBookNameField = findViewById(R.id.edit_text_book);
        mAuthorField = findViewById(R.id.edit_text_author);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBook();
            }
        });

        spinner = findViewById(R.id.book_format_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.book_type_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String selected = spinner.getSelectedItem().toString();
//                Toast.makeText(getBaseContext(),selected,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        }

        private void addBook()
        {
         String book = mBookNameField.getText().toString();
         String author = mAuthorField.getText().toString();
         String format = spinner.getSelectedItem().toString();
            Log.d("AddBook","Book- " + book + "\nAuthor- " + author + "\nFormat- " +format);
        }
}
