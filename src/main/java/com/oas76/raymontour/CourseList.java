package com.oas76.raymontour;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oddaskaf on 22.10.14.
 */
public class CourseList {

    private List<GolfCourse> list;

    public CourseList(){
        list = new ArrayList<>();
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



    public void setList(List<GolfCourse> list)
    {
        this.list = list;
    }



}
