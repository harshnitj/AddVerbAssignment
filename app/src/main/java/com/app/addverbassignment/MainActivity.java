package com.app.addverbassignment;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.addverbassignment.adapter.CountryAdapter;
import com.app.addverbassignment.modal.CountryModal;
import com.app.addverbassignment.rest.ApiInterface;
import com.app.addverbassignment.rest.RestAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView countryRV;
    private CountryAdapter countryAdapter;

    private Call<ArrayList<CountryModal>> apiCallBack = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        apiCall();
    }

    private void init() {
        countryRV = (RecyclerView) findViewById(R.id.countryRV);
    }

    private void apiCall() {
        ApiInterface apiInterface = RestAdapter.createAPI();

        apiCallBack = apiInterface.searchCountries();
        apiCallBack.enqueue(new Callback<ArrayList<CountryModal>>() {
            @Override
            public void onResponse(Call<ArrayList<CountryModal>> call, Response<ArrayList<CountryModal>> response) {
                Gson gson = new Gson();
                String res = gson.toJson(response.body().get(3), CountryModal.class);
                Log.d("TAGG", res.toString());
                countryAdapter = new CountryAdapter(response.body(), getApplicationContext());
                countryRV.setAdapter(countryAdapter);
                countryRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                saveToRoom(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<CountryModal>> call, Throwable t) {
                Log.e("TAGG", "", t);
            }
        });

    }

    private void saveToRoom(ArrayList<CountryModal> responseList) {

    }
}