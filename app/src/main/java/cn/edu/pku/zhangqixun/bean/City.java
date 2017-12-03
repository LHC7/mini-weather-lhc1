package cn.edu.pku.zhangqixun.bean;

/**
 * Created by test on 2017/11/14.
 */

public class City {
    private String province;
    private String city;
    private String number;
    private String firstPY;
    private String allPY;
    private String allFristPY;
    //增加一个构造函数City
    public City(String province, String city, String number, String
            firstPY, String allPY, String allFristPY) {
        this.province = province;
        this.city = city;
        this.number = number;
        this.firstPY = firstPY;
        this.allPY = allPY;
        this.allFristPY = allFristPY;
    }
    private String getProvince(){
        return province;
    }
    private String getCitycity(){
        return city;
    }
    private String getNumber(){
        return number;
    }
    private String getFirstPY(){
        return firstPY;
    }
    private String getAllPY(){
        return allPY;
    }
    private String getAllFristPY(){
        return allFristPY;
    }

    public String getCity() {

        return null;
    }

}
