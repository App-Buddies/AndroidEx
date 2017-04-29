package interviewmaster.admin.interview.com.androidex;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    RetroApi retro;
    RecyclerView recyclerView;
    //RetroAdapter retroAdapter;
    SqliteDatabase sd = new SqliteDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (checkNetworkConnection(getApplicationContext())) {
            parsed();
        } else {
            adp();
        }


    }

    private void parsed() {
        retro = Retroclient.getInstance();
        final Call<List<Example>> res = retro.getEmaployeeData();
        res.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                if (response.isSuccessful()) {
                    List<Example> list = response.body();
                    sd.addFoodCountryName(list);
                    adp();
                }

            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Log.d("Cannot Connect", "failure");


            }
        });
    }

    private void adp() {
        List<Example> list = sd.getData();

        RetroAdapter  retroAdapter = new RetroAdapter(getApplicationContext(),list);
           // Log.d("na",list.get(0).getEmployee().get(0).getFirstName());
            recyclerView.setAdapter(retroAdapter);

    }

    private Boolean checkNetworkConnection(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo wifiInfo, mobileInfo;
        try {
            if (context != null) {
                connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (wifiInfo.getState() == NetworkInfo.State.CONNECTED || mobileInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            } else {
                Log.d(TAG, "Context is null");
            }
        } catch (Exception exception) {
            Log.e(TAG, "CheckConnectivity Exception: " + exception.getMessage(), exception);
        }

        return false;
    }


}

