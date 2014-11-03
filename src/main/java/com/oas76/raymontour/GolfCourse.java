package com.oas76.raymontour;

import com.googlecode.objectify.Result;
import com.googlecode.objectify.annotation.*;
import com.googlecode.objectify.cmd.Query;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.cmd.SimpleQuery;

import javax.jdo.annotations.Embedded;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Cache
public class GolfCourse {


    // Auto genereated identifier
    @Id
    private Long id;

    // Name of cource
    @Index
    private String name;

    // Name of tee
    private String tee;

    // Length of course from tee
    private int length;

    // Metric Unit used for course
    private String unit;

    //par from tee
    private int par;

    // male coursevalue from tee
    private double male_coursevalue;

    // male courseindex from tee
    private int male_courseslope;

    // female coursevalue from tee
    private double female_coursevalue;

    // female courseindex from tee
    private int female_courseslope;

    // course url
    private String homepage_url;

    // List of golfholes from tee
    @Embedded
    private HashMap<String,GolfHole> Golfholes;


    public GolfCourse(){
        //No args constructor
    }


    public GolfCourse(String name, String tee){
        this.name = name;
        this.tee = tee;
        this.Golfholes = new HashMap();
    }


    public long save() {
        Result<Key<GolfCourse>> result = OfyService.ofy().save().entity(this);
        return result.now().getId();
    }

    public void delete() {
        OfyService.ofy().delete().entity(this);
    }



    public Long getId() {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
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

    public int getMale_courseslope() {
        return male_courseslope;
    }

    public double getFemale_coursevalue() {
        return female_coursevalue;
    }

    public int getFemale_courseslope() {
        return female_courseslope;
    }

    public HashMap<String,GolfHole> getGolfholes() {
        return Golfholes;
    }

    public GolfHole getGolfHole(int nr)
    {
        if(nr > 0 && nr <= 18)
            return Golfholes.get(Integer.toString(nr));
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

    public void setMale_courseslope(int male_courseslope) {
        this.male_courseslope = male_courseslope;
    }

    public void setFemale_coursevalue(double female_coursevalue) {
        this.female_coursevalue = female_coursevalue;
    }

    public void setFemale_courseslope(int female_courseslope) {
        this.female_courseslope = female_courseslope;
    }

    public GolfHole setGolfhole(int nr, int par, String name, int length, int index) throws ArrayIndexOutOfBoundsException {
        GolfHole result;
        if(nr > 0 && nr <= 18) {
            if(Golfholes == null)
                Golfholes = new HashMap<>();
            result = new GolfHole(nr, name, par, length, index);
            Golfholes.put(Integer.toString(nr),result);
        }
        else
           throw new ArrayIndexOutOfBoundsException("Holenumber outside range");

        return result;
    }

    public String getHomepage_url() {
        return homepage_url;
    }

    public void setHomepage_url(String homepage_url) {
        this.homepage_url = homepage_url;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public static GolfCourse get(String name, String tee) throws NotFoundException {
        Query<GolfCourse> query = OfyService.ofy().load().type(GolfCourse.class).filter("name", name);

        if (query != null && query.count() > 0){
            for (GolfCourse course : query) {
                if (course.tee.equals(tee))
                    return course;
            }
        }

        throw new NotFoundException(Key.create(GolfCourse.class,name));
    }

    public static GolfCourse getById(long id) throws NotFoundException {
        SimpleQuery<GolfCourse> query = OfyService.ofy().load().type(GolfCourse.class).filterKey(Key.create(GolfCourse.class, id));
        if (query != null && query.count() > 0){
            for (GolfCourse course : query) {
                if (course.getId() == id)
                    return course;
            }
        }

        throw new NotFoundException(Key.create(GolfCourse.class,Long.toString(id)));
    }

    public static List<GolfCourse> getAllStartsWith(String search_str) throws NotFoundException {
        List<GolfCourse> result;
        Query<GolfCourse> query = OfyService.ofy().load().type(GolfCourse.class).filter("name >=", search_str).filter("name <", search_str + "\ufffd");
        if (query != null && query.count() > 0){
            result = query.list();
            return result;
        }
        throw new NotFoundException(Key.create(GolfCourse.class, "No Courses starting with " + search_str + " in Database"));
    }

    public static int getShotsGiven(double handicap, int gSlope, double gValue, int gPar){
        double temp = 0;
        temp = handicap*gSlope/113.0f;

        return (int)Math.round(temp + (gValue-gPar));
    }






}

