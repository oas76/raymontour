package com.oas76.raymontour;

import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;


@Entity
public class GolfCourse {


    @Id
    private Long id;

    @Index
    private String name;

    // Name of tee
    private String tee;

    // Length of course from tee
    private int length;

    //par from tee
    private int par;

    // male coursevalue from tee
    private double male_coursevalue;

    // male courseindex from tee
    private int male_courseindex;

    // female coursevalue from tee
    private double female_coursevalue;

    // female courseindex from tee
    private int female_courseindex;

    // List of golfholes from tee
    private ArrayList<GolfHole> golfholes = new ArrayList<GolfHole>(18);


    public GolfCourse(){
        //No args constructor
    }

    /*
    public GolfCourse(String name, String tee, int par) {
        this.name = name;
        this.tee = tee;
        this.par = par;
        this.golfholes = new ArrayList<GolfHole>(18);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTee() {
        return tee;
    }

    public int getLength() {
        return length;
    }

    public int getPar() {
        return par;
    }

    public double getMale_coursevalue() {
        return male_coursevalue;
    }

    public int getMale_courseindex() {
        return male_courseindex;
    }

    public double getFemale_coursevalue() {
        return female_coursevalue;
    }

    public int getFemale_courseindex() {
        return female_courseindex;
    }

    public List<GolfHole> getGolfholes() {
        return Golfholes;
    }

    public GolfHole getGolfHole(int nr)
    {
        if(nr > 0 && nr <= 18)
            return Golfholes.get(nr-1);
        else
            return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTee(String tee) {
        this.tee = tee;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public void setMale_coursevalue(double male_coursevalue) {
        this.male_coursevalue = male_coursevalue;
    }

    public void setMale_courseindex(int male_courseindex) {
        this.male_courseindex = male_courseindex;
    }

    public void setFemale_coursevalue(double female_coursevalue) {
        this.female_coursevalue = female_coursevalue;
    }

    public void setFemale_courseindex(int female_courseindex) {
        this.female_courseindex = female_courseindex;
    }

    public void setGolfhole(int nr, GolfHole newgolfhole) {
        if(nr > 0 && nr <= 18)
            Golfholes.set(nr-1,newgolfhole);

    }
    */


}

class GolfHole {


    private int number;
    private String name;
    private int par;
    private int length;
    private int index;
    @Container GolfCourse golfCourse;

    public GolfHole(){
        // No Args Constructor
    }

    /*
    public GolfHole(int number,String name, int par, int length, int index)
    {
        this.number = number;
        this.name = name;
        this.par = par;
        this.length = length;
        this.index = index;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getPar() {
        return par;
    }

    public int getLength() {
        return length;
    }

    public int getIndex() {
        return index;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    */
}