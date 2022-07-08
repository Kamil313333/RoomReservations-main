package com.client.Interactors;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.client.Contracts.PaymentContract;
import com.client.DTOs.PaymentDTO;
import com.client.DTOs.PaymentResponse;
import com.client.LocalStorage.Storage;
import com.client.Models.Room;
import com.client.Models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentInteractor implements PaymentContract.PaymentInteractor {

    @Override
    public void makePayment(PaymentDTO dto, Context context, OnPaymentListener listener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(new Gson().toJson(dto));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ConnectionConstans.API_URL + "reservation/payment", jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        PaymentResponse r = new Gson().fromJson(response.toString(), PaymentResponse.class);
                        listener.OnResponse(r.getClientSecret());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Storage.getToken(context));
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void markAsPaid(Context context, int reservationId) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JSONObject jsonObject = null;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ConnectionConstans.API_URL + "reservation/mark-paid/" + reservationId, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}
