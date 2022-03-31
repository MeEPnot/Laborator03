package ro.pub.cs.systems.eim.lab03.phonedialer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneDialerActivity extends AppCompatActivity {

    private class NumberAndSymbolClickListener implements View.OnClickListener {
        public void onClick(View view) {
            ((TextView) findViewById(R.id.textView)).setText(((TextView) findViewById(R.id.textView)).getText().toString() + ((Button) view).getText().toString());
        }
    }

    private NumberAndSymbolClickListener numberAndSymbolClickListener = new NumberAndSymbolClickListener();

    private class BackspaceClickListener implements View.OnClickListener {
        public void onClick(View view) {
            String number = ((TextView) findViewById(R.id.textView)).getText().toString();
            if (number.length() > 0) {
                ((TextView) findViewById(R.id.textView)).setText(number.substring(0, number.length() - 1));
            }
        }
    }

    private BackspaceClickListener backspaceClickListener = new BackspaceClickListener();

    private class CallClickListener implements View.OnClickListener {
        public void onClick(View view) {
            if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        PhoneDialerActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        1);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + ((TextView) findViewById(R.id.textView)).getText().toString()));
                startActivity(intent);
            }
        }
    }

    private CallClickListener callClickListener = new CallClickListener();


    private class RejectClickListener implements View.OnClickListener {
        public void onClick(View view) {
            finish();
        }
    }

    private RejectClickListener rejectClickListener = new RejectClickListener();

    private class AddContactClickListener implements View.OnClickListener {
        public void onClick(View view) {
            String phoneNumber = ((TextView) findViewById(R.id.textView)).getText().toString();
            if (phoneNumber.length() > 0) {
                Intent intent = new Intent("ro.pub.cs.systems.eim.lab04.contactsmanager.intent.action.ContactsManagerActivity");
                intent.putExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY", phoneNumber);
                startActivityForResult(intent, 2022);
            } else {
                Toast.makeText(getApplication(), "Insert Phone Number", Toast.LENGTH_LONG).show();
            }
        };
    }

    private AddContactClickListener addContactClickListener = new AddContactClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);

        ((Button) findViewById(R.id.button_0)).setOnClickListener(numberAndSymbolClickListener);
        ((Button) findViewById(R.id.button_1)).setOnClickListener(numberAndSymbolClickListener);
        ((Button) findViewById(R.id.button_2)).setOnClickListener(numberAndSymbolClickListener);
        ((Button) findViewById(R.id.button_3)).setOnClickListener(numberAndSymbolClickListener);
        ((Button) findViewById(R.id.button_4)).setOnClickListener(numberAndSymbolClickListener);
        ((Button) findViewById(R.id.button_5)).setOnClickListener(numberAndSymbolClickListener);
        ((Button) findViewById(R.id.button_6)).setOnClickListener(numberAndSymbolClickListener);
        ((Button) findViewById(R.id.button_7)).setOnClickListener(numberAndSymbolClickListener);
        ((Button) findViewById(R.id.button_8)).setOnClickListener(numberAndSymbolClickListener);
        ((Button) findViewById(R.id.button_9)).setOnClickListener(numberAndSymbolClickListener);
        ((Button) findViewById(R.id.button_star)).setOnClickListener(numberAndSymbolClickListener);
        ((Button) findViewById(R.id.button_pound)).setOnClickListener(numberAndSymbolClickListener);

        ((ImageButton) findViewById(R.id.backspace_button)).setOnClickListener(backspaceClickListener);
        ((ImageButton) findViewById(R.id.reject_call_button)).setOnClickListener(rejectClickListener);
        ((ImageButton) findViewById(R.id.accept_call_button)).setOnClickListener(callClickListener);
        ((ImageButton) findViewById(R.id.add_number_button)).setOnClickListener(addContactClickListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case 2022:
                Toast.makeText(this, "Activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
                break;
        }
    }
}