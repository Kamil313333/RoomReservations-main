package com.client.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.client.Interactors.PaymentInteractor;
import com.client.Presenters.PaymentActivityPresenter;
import com.client.R;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;

public class PaymentActivity extends AppCompatActivity {
    PaymentActivityPresenter presenter;
    int reservationId;
    float totalCost;
    Button payButton;
    TextView paymentCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().hide();
        reservationId = getIntent().getIntExtra("reservationId", 0);
        totalCost = getIntent().getFloatExtra("totalCost", 0);

        paymentCost = findViewById(R.id.reservation_cost);
        paymentCost.setText(Float.toString(totalCost));

        payButton = findViewById(R.id.payButton);
        payButton.setOnClickListener(this::onPayClick);

        presenter = new PaymentActivityPresenter(this, new PaymentInteractor());
    }

    private void onPayClick(View view) {


        presenter.onPayClick(Math.round(totalCost), reservationId);
    }
}