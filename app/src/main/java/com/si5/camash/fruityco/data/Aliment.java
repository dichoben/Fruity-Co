package com.si5.camash.fruityco.data;

/**
 * Created by benjamin on 13/10/2014.
 */
public class Aliment {
    private String name;
    private int type;

    public Aliment(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
