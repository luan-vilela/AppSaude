package com.example.myapplication.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {

    private int id;
    private Date data;
    private String local;

    public Data(String data, String local) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.data = dateFormat.parse(data);
        this.local = local;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", data=" + data +
                ", local='" + local + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
