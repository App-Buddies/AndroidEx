package interviewmaster.admin.interview.com.androidex;

/**
 * Created by ADMIN on 28-04-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

    @SerializedName("employee")
    @Expose
    private List<Employee> employee = null;

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

}
