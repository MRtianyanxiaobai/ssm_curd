package com.atguigu.curd.bean;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    //状态码 100-成功 200-失败
    private  int code;
    private  String msg;
    private Map<String,Object> extend = new HashMap<>();
    public static Msg sucess(){
        Msg res = new Msg();
        res.setCode(100);
        res.setMsg("处理成功");
        return  res;

    }
    public  static  Msg fail(){
        Msg res = new Msg();
        res.setCode(200);
        res.setMsg("处理失败");
        return  res;
    }

    public Msg add(String key,Object value){
        this.getExtend().put(key, value) ;
        return this;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
