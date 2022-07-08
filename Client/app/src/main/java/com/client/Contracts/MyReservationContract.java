package com.client.Contracts;

import android.content.Context;

import com.android.volley.VolleyError;
import com.client.DTOs.ReservationDTO;
import com.client.Models.Room;

import java.util.List;

public interface MyReservationContract {
    interface MyReservationView{
        void getMyReservationCallback(List<ReservationDTO> rooms);
    }

    interface MyReservationPresenter{
        void getMyReservation();
    }

    interface MyReservationInteractor{
        interface OnGetMyReservationListener{
            void onResponse(List<ReservationDTO> rooms);
            void onError(VolleyError error);
        }

        void getMyReservation(Context context, OnGetMyReservationListener listener);
    }
}
