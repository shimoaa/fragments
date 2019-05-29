package com.example.ass4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import static android.app.Activity.RESULT_OK;
    public class SelectLocation extends Fragment {
        int PLACE_PICKER_REQUEST = 1;
        TextView latitude;
        TextView longitude;
        Button pickerlocation;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.select_location,container,false);
            pickerlocation = view.findViewById(R.id.map);
            latitude = view.findViewById(R.id.latitude);
            longitude = view.findViewById(R.id.longitude);
            pickerlocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                    try {
                        startActivityForResult(builder.build(getActivity()),PLACE_PICKER_REQUEST);
                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }
                }
            });

            Button back2 = view.findViewById(R.id.back2);
            back2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewFragment fragment = new NewFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment,fragment);
                    transaction.commit();
                }
            });

            return view;

        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == PLACE_PICKER_REQUEST){
                if(resultCode == RESULT_OK){
                    Place place = PlacePicker.getPlace(data,getContext());
                    String latitude1 = String.valueOf(place.getLatLng().latitude);
                    String longitude1 = String.valueOf(place.getLatLng().longitude);
                    latitude.setText(latitude1);
                    longitude.setText(longitude1);
                }
            }
        }
    }