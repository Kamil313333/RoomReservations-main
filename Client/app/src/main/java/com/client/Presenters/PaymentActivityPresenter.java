package com.client.Presenters;

import android.widget.Toast;

import com.android.volley.VolleyError;
import com.client.Activities.PaymentActivity;
import com.client.Contracts.PaymentContract;
import com.client.DTOs.PaymentDTO;
import com.client.Interactors.PaymentInteractor;
import com.client.LocalStorage.Storage;
import com.client.Models.User;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

public class PaymentActivityPresenter implements PaymentContract.PaymentPresenter {
    private PaymentActivity activity;
    private PaymentInteractor paymentInteractor;
    private PaymentSheet paymentSheet;
    private int recervationId;
    String publishKey = "pk_test_51LCPghFrUnnbVHsvTZMbWqZYGRpwHrSKq6PfvBRjdd5bCrO8GL6h72NTKprbkSLpJwt0LeUOyEg2bt0IXggtArCG007KAQbmq9";


    public PaymentActivityPresenter(PaymentActivity activity, PaymentInteractor interactor) {
        this.activity = activity;
        this.paymentInteractor = interactor;

        PaymentConfiguration.init(activity, publishKey);

        paymentSheet = new PaymentSheet(activity, this::onPaymentResult);
    }


    @Override
    public void onPayClick(int amount, int reservationId) {
        this.recervationId = reservationId;

        paymentInteractor.makePayment(new PaymentDTO(amount), activity, new PaymentContract.PaymentInteractor.OnPaymentListener() {
            @Override
            public void OnResponse(String clientSecret) {
                User u = Storage.getUser(activity);
                PaymentSheet.Configuration cfg = new PaymentSheet.Configuration(u.getEmail());
                paymentSheet.presentWithPaymentIntent(clientSecret, cfg);
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(activity, "Something went wrong with payment.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onPaymentResult(PaymentSheetResult result) {
        if(result instanceof PaymentSheetResult.Completed) {
            Toast.makeText(activity, "Payment complete!", Toast.LENGTH_SHORT).show();
            paymentInteractor.markAsPaid(activity, recervationId);
            activity.finish();
        }
    }
}
