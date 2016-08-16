package com.penguin.padang.pasir.listbuah.presenters;

import android.util.Base64;

import com.penguin.padang.pasir.listbuah.helpers.api.ApiEndPointInterfaces;
import com.penguin.padang.pasir.listbuah.helpers.constanta.Constanta;
import com.penguin.padang.pasir.listbuah.helpers.services.Md5Converter;
import com.penguin.padang.pasir.listbuah.interfaces.MainPresenterInterface;
import com.penguin.padang.pasir.listbuah.interfaces.MainViewInterface;
import com.penguin.padang.pasir.listbuah.models.Fruit;
import com.penguin.padang.pasir.listbuah.models.Fruits;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jowy on 8/11/16.
 */
public class MainPresenterImp implements MainPresenterInterface {
    private MainViewInterface view;
    private Retrofit retrofit;
    private Fruits fruits;

    private static final String username = "Rimbunesia";
    private String password;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit
            .Builder()
            .baseUrl(Constanta.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public MainPresenterImp(MainViewInterface view){
        this.view = view;
    }

    public static <S> S createService(Class<S> serviceClass){
        String password = Md5Converter.getMd5(username);
        String creadentials = username + ":" + password;

        final String basic = "Basic " + Base64.encodeToString(creadentials.getBytes(), Base64.NO_WRAP);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", basic)
                        .header("Accept", "application/json")
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }



    @Override
    public void getData() {

        ApiEndPointInterfaces api = createService(ApiEndPointInterfaces.class);
        Call<List<Fruit>> employeeCall = api.getEmployee();
        employeeCall.enqueue(new Callback<List<Fruit>>() {
            @Override
            public void onResponse(Call<List<Fruit>> call, Response<List<Fruit>> response) {
                view.getDialog().dismiss();
                try {
                    fruits = new Fruits();
                    fruits.setFruit(response.body());
                    view.showData(fruits);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Fruit>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
