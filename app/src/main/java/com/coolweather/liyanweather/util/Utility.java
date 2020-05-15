package com.coolweather.liyanweather.util;

import android.text.TextUtils;

import com.coolweather.liyanweather.db.City;
import com.coolweather.liyanweather.db.County;
import com.coolweather.liyanweather.db.Province;
import com.coolweather.liyanweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /*
     * 解析省列表数据*/
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvince = new JSONArray(response);
                for (int i = 0; i < allProvince.length(); i++) {
                    JSONObject object = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.provinceName = object.getString("name");
                    province.provinceCode = object.getInt("id");
                    province.save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /*
     * 解析市列表数据*/
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject object = allCities.getJSONObject(i);
                    City city = new City();
                    city.cityName = object.getString("name");
                    city.cityCode = object.getInt("id");
                    city.provinceId = provinceId;
                    city.save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /*
     * 解析县列表数据*/
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounty = new JSONArray(response);
                for (int i = 0; i < allCounty.length(); i++) {
                    JSONObject object = allCounty.getJSONObject(i);
                    County county = new County();
                    county.countyName = object.getString("name");
                    county.countyCode = object.getInt("id");
                    county.weatherId = object.getString("weather_id");
                    county.cityId = cityId;
                    county.save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /*
    * 解析天气数据*/
    public static Weather handleWeatherResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONObject object = new JSONObject(response);
                JSONArray jsonArray = object.getJSONArray("HeWeather");
                String weatherContent = jsonArray.getJSONObject(0).toString();
                return new Gson().fromJson(weatherContent,Weather.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
