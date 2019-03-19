package com.example.swdb.data;

import java.io.Serializable;

public class SWStarship implements Serializable {
    public String name;
    public String model;
    public String manufacturer;
    public String cost_in_credits;
    public String length;
    public String max_atmosphering_speed;
    public String crew;
    public String passengers;
    public String cargo_capacity;
    public String consumables;
    public String hyperdrive_rating;
    public String MGLT;
    public String starship_class;

    public static final String EXTRA_SHIP_ITEM = "com.example.android.swdb.data.SWStarship";

}
