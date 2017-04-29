package interviewmaster.admin.interview.com.androidex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.List;



public class SqliteDatabase extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "employee.dp";

    public SqliteDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DbVari.CREATETABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }


    public void addFoodCountryName(List<Example> item) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (item != null) {
            for (int i = 0; i < item.size(); i++) {
                List<Employee> employeeList = item.get(i).getEmployee();
                for (int j = 0; j < employeeList.size(); j++) {
                    try {
                        Employee employee = employeeList.get(j);
                        ContentValues values = new ContentValues();
                        values.put(DbVari.ID, employee.getId());
                        values.put(DbVari.FIRSTNAME, employee.getFirstName());
                        values.put(DbVari.LASTNAME, employee.getLastName());
                        values.put(DbVari.ADDRESS, employee.getAddress());
                        values.put(DbVari.CITY, employee.getCity());
                        values.put(DbVari.ZIPCODE, employee.getZipcode());
                        values.put(DbVari.GENDER, employee.getGender());
                        values.put(DbVari.DOB, employee.getDob());
                        values.put(DbVari.DESIGNATION, employee.getDesignation());
                        values.put(DbVari.MOBILE, employee.getMobile());
                        values.put(DbVari.EMAIL, employee.getEmail());
                        values.put(DbVari.NATIONALITY, employee.getNationality());
                        values.put(DbVari.LANGUAGE, employee.getLanguage());
                        values.put(DbVari.IMAGEURL, employee.getImageURL());
                        values.put(DbVari.SKILL, employee.getSkills() != null ? employee.getSkills().toString() : null);
                        db.insertWithOnConflict(DbVari.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (db != null) db.close();
    }


    public List<Example> getData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + DbVari.TABLE_NAME;
        List<Example> exampleList = null;
        List<Employee> employeeList = null;


        try {
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (!cursor.isLast()) {
                employeeList = new ArrayList<Employee>();
                exampleList = new ArrayList<Example>();

                if (cursor.moveToFirst()) {
                    Employee item = new Employee();
                    item.setId(cursor.getString(cursor.getColumnIndex(DbVari.ID)));
                    item.setFirstName(cursor.getString(cursor.getColumnIndex(DbVari.FIRSTNAME)));
                    item.setLastName(cursor.getString(cursor.getColumnIndex(DbVari.LASTNAME)));
                    item.setAddress(cursor.getString(cursor.getColumnIndex(DbVari.ADDRESS)));
                    item.setCity(cursor.getString(cursor.getColumnIndex(DbVari.CITY)));
                    item.setZipcode(cursor.getString(cursor.getColumnIndex(DbVari.ZIPCODE)));
                    item.setGender(cursor.getString(cursor.getColumnIndex(DbVari.GENDER)));
                    item.setDob(cursor.getString(cursor.getColumnIndex(DbVari.DOB)));
                    item.setMobile(cursor.getString(cursor.getColumnIndex(DbVari.MOBILE)));
                    item.setEmail(cursor.getString(cursor.getColumnIndex(DbVari.EMAIL)));
                    item.setNationality(cursor.getString(cursor.getColumnIndex(DbVari.NATIONALITY)));
                    item.setLanguage(cursor.getString(cursor.getColumnIndex(DbVari.LANGUAGE)));
                   // String sklildata = cursor.getString(cursor.getColumnIndex(DbVari.SKILL));
                    if (cursor.getString(cursor.getColumnIndex(DbVari.SKILL) )!= null) {

                        try {
                            List<Skill> skill = new ArrayList<>();
                            Skill sd = new Skill();
                            JSONArray j = new JSONArray(cursor.getString(cursor.getColumnIndex(DbVari.SKILL) ));
                            for (int z = 0; z <j.length(); z++) {
                                JSONObject o = j.getJSONObject(z);

                                if (o.has("technical")) {
                                    JSONArray jsonArray1 = o.getJSONArray("technical");
                                    ArrayList<String> list = new ArrayList<String>();
                                    for (int d = 0; d <= o.length(); d++) {
                                        list.add(jsonArray1.getString(d));
                                    }
                                    if (list != null) {
                                        sd.setTechnical(list);
                                    }
                                }
                                if (o.has("extra_curricular")) {
                                    ArrayList<String> s = new ArrayList<>();
                                    JSONArray sdd = o.getJSONArray("extra_curricular");
                                    for (int k = 0; k <= sdd.length(); k++) {
                                        s.add(sdd.getString(k));

                                    }
                                    if (s != null) {
                                        sd.setExtraCurricular(s);
                                    }

                                }
                                skill.add(sd);

                            }
                            item.setSkills(skill);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    employeeList.add(item);
                }
                while (cursor.moveToNext()) ;


            }
            Example example = new Example();
            example.setEmployee(employeeList);
            if (exampleList != null) {
                exampleList.add(example);
            }
        } finally {
            sqLiteDatabase.close();
        }

        return exampleList;
    }
}



