package com.oas76.raymontour;

/**
 * Created by oddaskaf on 28.10.14.
 */
public class Result {

    private String res;

    public Result(){
        res = "Empty Result";
    }

    public Result(String str){
        res = str;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
