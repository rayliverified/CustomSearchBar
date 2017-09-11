package stream.customsearchbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomSearchBar extends FrameLayout {

    String mHintText, mCancelText;
    float mTextSize, mHintSize, mCancelTextSize;
    int mSearchFrameBackground, mSearchBoxBackground, mTextColor, mHintColor, mCancelTextColor;

    EditText mSearchEditText;
    TextView mBtnCancel;
    LinearLayout mSearchLayout;
    TextView mSearchHint;
    FrameLayout mSearchFrame;

    TextWatcher mTextWatcher;
    TextView.OnEditorActionListener mEditorActionListener;

    Context mContext;
    private final String mActivity = this.getClass().getSimpleName();

    public CustomSearchBar(Context context) {
        this(context, null, 0, 0);
    }

    public CustomSearchBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public CustomSearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomSearchBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomSearchBar);
        mSearchFrameBackground = a.getColor(R.styleable.CustomSearchBar_search_frame_background, Color.parseColor("#C9C9C9"));
        mSearchBoxBackground = a.getColor(R.styleable.CustomSearchBar_search_frame_background, Color.parseColor("#FFFFFF"));
        mTextSize = a.getDimension(R.styleable.CustomSearchBar_search_text_size, 14);
        mTextColor = a.getColor(R.styleable.CustomSearchBar_search_text_color, Color.parseColor("#2D2D2D"));
        mHintText = a.getString(R.styleable.CustomSearchBar_search_hint_text);
        mHintSize = a.getDimension(R.styleable.CustomSearchBar_search_hint_size, 14);
        mHintColor = a.getColor(R.styleable.CustomSearchBar_search_hint_color, Color.parseColor("#8E8E8E"));
        mCancelText = a.getString(R.styleable.CustomSearchBar_search_cancel_text);
        mCancelTextSize = a.getDimension(R.styleable.CustomSearchBar_search_cancel_size, 12);
        mCancelTextColor = a.getColor(R.styleable.CustomSearchBar_search_cancel_color, Color.parseColor("#008AF9"));
        a.recycle();

        init(context);
    }

    private void init(Context context)
    {
        mContext = context;

        FrameLayout searchView = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.searchview, null);
        mSearchEditText = searchView.findViewById(R.id.search_edittext);
        mBtnCancel = searchView.findViewById(R.id.tv_cancel);
        mSearchLayout = searchView.findViewById(R.id.search_layout);
        mSearchHint = searchView.findViewById(R.id.search_hint);
        mSearchFrame = searchView.findViewById(R.id.search_frame);
        addView(searchView);

        if (mTextWatcher == null)
        {
            mTextWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            };
        }
        mSearchEditText.addTextChangedListener(mTextWatcher);
        if (mEditorActionListener == null)
        {
            mEditorActionListener = new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    return false;
                }
            };
        }
        mSearchEditText.setOnEditorActionListener(mEditorActionListener);
        mSearchEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                mSearchFrame.setVisibility(hasFocus ? GONE : VISIBLE);
                mSearchLayout.setVisibility(hasFocus ? VISIBLE : GONE);
            }
        });
        mBtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchEditText.setText("");
                hideSoftInput(mSearchEditText);
                mSearchEditText.clearFocus();
            }
        });
        mSearchFrame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showSoftInput(mSearchEditText);
            }
        });

        mHintText = (mHintText == null ? "Search" : mHintText);
        mSearchHint.setText(mHintText);
        mSearchHint.setTextSize(mHintSize);
        mSearchHint.setTextColor(mHintColor);
        mSearchEditText.setHint(mHintText);
        mSearchEditText.setHintTextColor(mHintColor);

        mSearchEditText.setTextSize(mTextSize);
        mSearchEditText.setTextColor(mTextColor);

        if (mCancelText != null) mBtnCancel.setText(mCancelText);
        mBtnCancel.setTextSize(mCancelTextSize);
        mBtnCancel.setTextColor(mCancelTextColor);
    }

    public void SetTextWatcher(TextWatcher textWatcher)
    {
        mTextWatcher = textWatcher;
        init(mContext);
    }

    public void SetEditorActionListener(TextView.OnEditorActionListener editorActionListener)
    {
        mEditorActionListener = editorActionListener;
        init(mContext);
    }

    public String GetText()
    {
        return mSearchEditText.getText().toString();
    }

    //Input helpers
    public void showSoftInput(EditText edit) {
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        InputMethodManager imm = (InputMethodManager) edit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.showSoftInput(edit, 0);
    }

    public void hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
