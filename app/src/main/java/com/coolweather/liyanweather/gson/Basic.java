package com.coolweather.liyanweather.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {
    @SerializedName("city")    //与Gson中的名字，形成映射
    public String cityName;
    @SerializedName("id")
    public String weatherId;
    @SerializedName("update")
    public Updata updata;
    public class Updata {
        @SerializedName("loc")
        public String updataTime;
    }
}
