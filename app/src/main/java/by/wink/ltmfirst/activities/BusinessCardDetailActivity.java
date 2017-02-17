package by.wink.ltmfirst.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import by.wink.ltmfirst.R;

/**
 * Created by amine on 17/02/17.
 */

public class BusinessCardDetailActivity extends Activity {
    private static final int MAX_LENGTH = 18;
    EditText nameET;
    TextView nameTextView;
    Button confirmBtn;

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() <= MAX_LENGTH) {
                nameTextView.setText(editable.toString());
            }
            else {
                Toast.makeText(BusinessCardDetailActivity.this, R.string.name_too_long,Toast.LENGTH_LONG).show();
            }


        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_card_detail);
        nameET = (EditText) findViewById(R.id.business_card_name_edit);
        nameTextView = (TextView) findViewById(R.id.business_card_name);
        confirmBtn = (Button) findViewById(R.id.confirm_button);
        nameET.addTextChangedListener(textWatcher);


        if (getIntent() != null) {
            if (getIntent().getStringExtra(MainActivity.BUSINESS_CARD_NAME) != null) {
                nameTextView.setText(getIntent().getStringExtra(MainActivity.BUSINESS_CARD_NAME));
            }
        }

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nameTextView.getText().toString().isEmpty()){
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(MainActivity.BUSINESS_CARD_NAME, nameTextView.getText());
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }else{
                    Toast.makeText(BusinessCardDetailActivity.this, getString(R.string.invalid_name),Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }
}
