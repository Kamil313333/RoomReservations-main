package com.client.Contracts;

import android.content.Context;

import com.android.volley.VolleyError;
import com.client.DTOs.PaymentDTO;

public interface PaymentContract {
    interface PaymentPresenter {
        void onPayClick(int amount, int reservationId);
    }

    interface PaymentView
    {
        void makePayment(PaymentDTO dto);
    }

    interface PaymentInteractor {
        interface OnPaymentListener {
            void OnResponse(String clientSecret);
            void onError(VolleyError error);
        }

        void makePayment(PaymentDTO dto, Context context, OnPaymentListener listener);

        void markAsPaid(Context context, int reservationId);
    }
}
