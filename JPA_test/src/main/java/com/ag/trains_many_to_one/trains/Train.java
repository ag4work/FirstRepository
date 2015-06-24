package com.ag.trains_many_to_one.trains;

/**
 * Created by Alexey on 20.06.2015.
 */
public class Train {
    private  int trainId;
    private String name;

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
