package com.tj.personallibrary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;

public class AddBookActivity extends AppCompatActivity{

    private Button mAddButton;
    private EditText mBookNameField;
    private EditText mAuthorField;
    private Spinner spinner;
    private FloatingActionButton fab;

    private Map<String,Book> bookMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mAddButton = findViewById(R.id.button_add_book);
        mBookNameField = findViewById(R.id.edit_text_book);
        mAuthorField = findViewById(R.id.edit_text_author);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBook();
                finish();
            }
        });

//        mAddButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addBook();
//            }
//        });

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
        bookMap.put(book,new Book(book,author,format));
    }
}


class Book
{
    private String book;
    private String author;
    private String format;

    public Book(String book, String author, String format)
    {
        this.book = book;
        this.author = author;
        this.format = format;
    }

    @Override
    public String toString()
    {
        return this.book + "\n" + this.author + "\n" + this.format;
    }
}