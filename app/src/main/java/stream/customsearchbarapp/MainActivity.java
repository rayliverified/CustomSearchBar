package stream.customsearchbarapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import stream.customsearchbar.CustomSearchBar;

public class MainActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    CustomSearchBar mSearchBar;
    ImageButton mBtnFilter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        mSearchBar = findViewById(R.id.search_bar);
        //Override default TextWatcher with custom code.
//        mSearchBar.SetTextWatcher(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                Log.d("Text", editable.toString());
//            }
//        });
        mSearchBar.setEditorActionInterface(new CustomSearchBar.EditorActionInterface() {
            @Override
            public void onEditorActionEnter(String text) {
                Log.d("EnterText", text);
            }
        });

        mBtnFilter = findViewById(R.id.btn_more);
        mBtnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("GetText", mSearchBar.GetText());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
