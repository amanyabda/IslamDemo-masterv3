package com.hassan.islamicdemo.PrayersService;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hassan.islamicdemo.Base.ApiClient;
import com.hassan.islamicdemo.Home.MainActivity;
import com.hassan.islamicdemo.Utils.AppConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrayersInteractorImpl implements PrayersInteractor{

    private Callback mCallback;

    @Override
    public void getPrayers(double latitude, double longitude, int method, Callback callback) {
        this.mCallback = callback;
//        Log.e("PrayersInteractorImpl", "getPrayers: ");
        ApiClient.getAladhanApiClient().create(PrayersApiService.class).getPrayersTimes(latitude, longitude, method)
                .enqueue(new retrofit2.Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            PrayersTimesJsonParser parser = PrayersTimesJsonParser.parse(new JSONObject(response.body().string()));
                            mCallback.onSuccessHDate(parser.gethDate());
                            mCallback.onSuccessGDate(parser.getgDate());
                            mCallback.onSuccess(parser.getTimes());

//                            MainActivity.progressCircular.setVisibility(View.GONE);

                        } catch (Exception e) {
                            e.printStackTrace();
                            mCallback.onError(e.getMessage());
                            Log.e("Fail in response PrayersTimes : ", ""+e.getLocalizedMessage());

                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        mCallback.onError(t.getMessage());
                        Log.e("Fail in get PrayersTimes : ", ""+t.getLocalizedMessage());
                    }
                });

        ApiClient.getGoogleApiClient().create(PrayersApiService.class).getLocation(String.format("%s,%s", latitude, longitude), true, AppConstants.GOOGLE_API_KEY).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String location = new JSONObject(response.body().string()).getJSONArray("results").getJSONObject(0).getString("formatted_address");
                    mCallback.onLocation(location);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mCallback.onError(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    mCallback.onError(e.getMessage());
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mCallback.onError(t.getMessage());
            }
        });
    }


}
