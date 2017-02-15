package by.wink.ltmfirst;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by amine on 06/02/17.
 */

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    Intent intent;
    String email = "";
    static RelativeLayout layout;


    // recyclerView items
    RecyclerView businessCardsRV;
    LinearLayoutManager layoutManager;
    BusinessCardAdapter adapter;

    // constants
    private static final String ELIS_ADDRESS = "via Sandro Sandri 71";
    private static final String LTM_COURSE = "LTM 11";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout) findViewById(R.id.main_layout);
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

    }




    public static void  showSnackBar(String name){

        Snackbar snackbar = Snackbar.make(layout,name,Snackbar.LENGTH_SHORT);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        snackbar.show();

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
        dialogBuilder.setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();

                BusinessCard businessCard = new BusinessCard(studentName.getText().toString(),
                        studentEmail.getText().toString(),studentPhone.getText().toString(),LTM_COURSE,ELIS_ADDRESS);
                adapter.addBusinessCard(businessCard);
                businessCardsRV.scrollToPosition(0);

            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        adapter.setDataSet(getBusinessCards());

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
