package com.client.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.client.DTOs.ReservationDTO;
import com.client.Models.Room;
import com.client.R;
import com.squareup.picasso.Picasso;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReservationsAdapter extends RecyclerView.Adapter {

        public interface OnClickListener{
            void onItemClick(ReservationDTO room);
        }

        List<ReservationDTO> reservations;
        com.client.Adapters.ReservationsAdapter.OnClickListener listener;
        Context context;

        public ReservationsAdapter(com.client.Adapters.ReservationsAdapter.OnClickListener listener) {
            this.listener = listener;
        }

        public void UpdateData(List<ReservationDTO> list)
        {
            this.reservations = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation, parent, false);
            context = parent.getContext();
            return new com.client.Adapters.ReservationsAdapterViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            com.client.Adapters.ReservationsAdapterViewHolder hld = (com.client.Adapters.ReservationsAdapterViewHolder) holder;
            hld.nameTV.setText(Integer.toString(reservations.get(position).getDoorNum()));
            hld.priceTV.setText("Price: " + reservations.get(position).getPricePerDay() + "€");
            hld.descriptionTV.setText("Guests: " + reservations.get(position).getGuestCount());
            if(reservations.get(position).getImageURL() != null && !reservations.get(position).getImageURL().equals(""))
                Picasso.with(context).load(reservations.get(position).getImageURL()).into(hld.image);
            hld.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(reservations.get(position));
                }
            });

            if(reservations.get(position).isHasPaid()) {
                hld.paidTV.setText("ROOM IS PAID");
                hld.paidTV.setTextColor(Color.GREEN);
            } else {
                hld.paidTV.setText("NOT PAID");
                hld.paidTV.setTextColor(Color.RED);
            }
        }

        @Override
        public int getItemCount() {
            return reservations ==null? 0: reservations.size();
        }
    }

    class ReservationsAdapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameTV, priceTV, descriptionTV, paidTV;
        ImageView image;
        Button button;

        public ReservationsAdapterViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.room_nameCV);
            priceTV = itemView.findViewById(R.id.room_priceCV);
            descriptionTV = itemView.findViewById(R.id.room_descriptionCV);
            image = itemView.findViewById(R.id.bike_imageCV);
            button = itemView.findViewById(R.id.payButton);
            paidTV = itemView.findViewById(R.id.paid);
        }

    }

