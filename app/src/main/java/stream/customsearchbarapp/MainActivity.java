package stream.customsearchbarapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import stream.customsearchbar.CustomSearchBar;

public class MainActivity extends AppCompatActivity{

    CustomSearchBar mCustomSearchBar;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        mCustomSearchBar = findViewById(R.id.search_bar);
    }
}
