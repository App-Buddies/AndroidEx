package interviewmaster.admin.interview.com.androidex;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ADMIN on 29-04-2017.
 */

public class Retroclient {

    public static String Url = "https://private-2a004-androidtest3.apiary-mock.com";


    public static Retrofit getM() {

        return new Retrofit.Builder().baseUrl(Url).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static RetroApi getInstance() {
        return getM().create(RetroApi.class);
    }
}

