package interviewmaster.admin.interview.com.androidex;

/**
 * Created by ADMIN on 28-04-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Skill {

    @SerializedName("technical")
    @Expose
    private List<String> technical = null;
    @SerializedName("extra_curricular")
    @Expose
    private List<String> extraCurricular = null;

    public List<String> getTechnical() {
        return technical;
    }

    public void setTechnical(List<String> technical) {
        this.technical = technical;
    }

    public List<String> getExtraCurricular() {
        return extraCurricular;
    }

    public void setExtraCurricular(List<String> extraCurricular) {
        this.extraCurricular = extraCurricular;
    }

}
