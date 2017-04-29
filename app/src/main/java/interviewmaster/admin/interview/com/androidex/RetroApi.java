package interviewmaster.admin.interview.com.androidex;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ADMIN on 29-04-2017.
 */

public interface RetroApi {
    @GET("/employeesList")
    Call<List<Example>> getEmaployeeData();
}
