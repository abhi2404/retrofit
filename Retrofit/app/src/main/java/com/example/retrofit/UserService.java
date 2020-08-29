package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("doctor_login/login/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
