package by.wink.ltmfirst.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import by.wink.ltmfirst.R;
import by.wink.ltmfirst.adapters.BusinessCardAdapter;
import by.wink.ltmfirst.models.BusinessCard;

/**
 * Created by amine on 06/02/17.
 */

public class MainActivity extends Activity {
    // constants
    private static final String TAG = "MainActivity";
    public static final String BUSINESS_CARD_NAME = "BUSINESS_CARD_NAME";
    private static final String ELIS_ADDRESS = "via Sandro Sandri 71";
    private static final String LTM_COURSE = "LTM 11";
    public static final int EDIT_NAME_REQUEST = 10;
    public static final String BUSINESS_CARD_POSITION = "BUSINESS_CARD_POSITION";

    Intent intent;
    String email = "";


    // recyclerView items
    RecyclerView businessCardsRV;
    LinearLayoutManager layoutManager;
    BusinessCardAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        intent = getIntent();

        if (intent.getStringExtra(LoginActivity.EMAIL_KEY) != null) {
            email = intent.getStringExtra(LoginActivity.EMAIL_KEY);
        } else {
            String action = intent.getAction();
            if (Intent.ACTION_SEND.equals(action)) {
                email = intent.getStringExtra(Intent.EXTRA_TEXT);

            }

        }

        businessCardsRV = (RecyclerView) findViewById(R.id.business_cards_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new BusinessCardAdapter();
        businessCardsRV.setLayoutManager(layoutManager);
        businessCardsRV.setAdapter(adapter);

        findViewById(R.id.add_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddStudentDialog();
            }
        });

        adapter.setDataSet(getBusinessCards());


    }




    public void showAddStudentDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_student_add, null);
        dialogBuilder.setView(dialogView);

        final EditText studentName = (EditText) dialogView.findViewById(R.id.dialog_student_name);
        final EditText studentEmail = (EditText) dialogView.findViewById(R.id.dialog_student_email);
        final EditText studentPhone = (EditText) dialogView.findViewById(R.id.dialog_student_phone);

        dialogBuilder.setTitle(R.string.student);
        dialogBuilder.setMessage(R.string.insert_student_name);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == DialogInterface.BUTTON_POSITIVE) {
                    //do something with edt.getText().toString();

                    BusinessCard businessCard = new BusinessCard(studentName.getText().toString(),
                            studentEmail.getText().toString(), studentPhone.getText().toString(), LTM_COURSE, ELIS_ADDRESS);
                    adapter.addBusinessCard(businessCard);
                    businessCardsRV.scrollToPosition(0);

                }

            }
        };
        dialogBuilder.setPositiveButton(R.string.done,dialogClickListener);


        dialogBuilder.setNegativeButton(R.string.cancel,dialogClickListener);
        AlertDialog b = dialogBuilder.create();
        b.show();


    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        getBusinessCards();

    }


    private ArrayList<BusinessCard> getBusinessCards() {
        ArrayList<BusinessCard> businessCards = new ArrayList<>();
        BusinessCard francescoBC = new BusinessCard("Francesco Cipolla", "francescocpll@gmail.com", "333281213", LTM_COURSE, ELIS_ADDRESS);
        BusinessCard matteoBC = new BusinessCard("Matteo Manfreda", "manfredamatteo44@gmail.com", "348974379", LTM_COURSE, ELIS_ADDRESS);
        BusinessCard micheleBC = new BusinessCard("Michele Foderaro", "michele.foderaro@virgilio.it", "3891379123", LTM_COURSE, ELIS_ADDRESS);
        BusinessCard domenicoBC = new BusinessCard("Domenico Licciardi", "licciardi.domenico98@gmail.com", "333281213", LTM_COURSE, ELIS_ADDRESS);
        BusinessCard gaetanoBC = new BusinessCard("Gaetano Ciccone", "gaetano.ciccone97@gmail.com", "333281213", LTM_COURSE, ELIS_ADDRESS);

        businessCards.add(francescoBC);
        businessCards.add(matteoBC);
        businessCards.add(micheleBC);
        businessCards.add(domenicoBC);
        businessCards.add(gaetanoBC);

        return businessCards;


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDIT_NAME_REQUEST && resultCode ==RESULT_OK ){
            int position = data.getIntExtra(BUSINESS_CARD_POSITION,0);
            BusinessCard editedCard = adapter.getBusinessCard(position);
            editedCard.setName(data.getStringExtra(BUSINESS_CARD_NAME));
            adapter.editBusinessCard(editedCard,position);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

    }
}
