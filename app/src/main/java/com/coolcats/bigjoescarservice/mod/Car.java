package com.coolcats.bigjoescarservice.mod;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "cars")
public class Car implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "make")
    private String make;

    @ColumnInfo(name = "model")
    private String model;

    @ColumnInfo(name = "year")
    private int year;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "tag")
    private String tag;

    @ColumnInfo(name = "price")
    private double price;

    @ColumnInfo(name = "available")
    private boolean available;

    //Database constructor
    public Car(int id, String make, String model, int year, String color, String tag, double price, boolean available) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.tag = tag;
        this.price = price;
        this.available = available;
    }

    //User constructor
    @Ignore
    public Car(String make, String model, int year, String color, String tag, double price, boolean available) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.tag = tag;
        this.price = price;
        this.available = available;
    }

    protected Car(Parcel in) {
        id = in.readInt();
        make = in.readString();
        model = in.readString();
        year = in.readInt();
        color = in.readString();
        available = in.readByte() != 0;
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public int getId() {
        return id;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(make);
        dest.writeString(model);
        dest.writeInt(year);
        dest.writeString(color);
        dest.writeByte((byte) (available ? 1 : 0));
    }

    @Override
    public String toString() {
        if(this != null)
            return "id: " + id + "\nmake: " + make + "\nmodel: " + model + "\ncolor: " + color + "\ntag: " + tag + "\nprice: " + price + "\navailable: " + available;
        else return "Empty!";
    }
}
