package com.app.addverbassignment.rest;


import com.app.addverbassignment.modal.CountryModal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("asia")
    Call<ArrayList<CountryModal>> searchCountries ();


}