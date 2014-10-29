package com.oas76.raymontour.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.NotFoundException;
import com.oas76.raymontour.CourseList;
import com.oas76.raymontour.GolfCourse;
import com.oas76.raymontour.GolfHole;
import com.oas76.raymontour.Result;


import javax.inject.Named;
import java.util.List;

/** An endpoint class we are exposing */
@Api(name = "courseapi",
        version = "v1",
        namespace = @ApiNamespace(ownerDomain = "com.oas76.raymontour",
                ownerName = "com.oas76.raymontour",
                packagePath="api"))
public class GolfCourseApi {

    @ApiMethod(name = "addCourse")
    public GolfCourse addCourse(@Named("name") String name, @Named("tee") String tee, User user) {
        ApiUseLogger.logUse(user,"addCourse");

        GolfCourse response = new GolfCourse(name,tee);
        response.setId(response.save());
        return response;
    }

    @ApiMethod(name = "getCourse")
    public GolfCourse getGolfCourse(@Named("name") String name, @Named("tee") String tee, User user) throws com.google.api.server.spi.response.NotFoundException{
        ApiUseLogger.logUse(user,"getCourse");

        GolfCourse response;
        try{
            response = GolfCourse.get(name,tee);

        }
        catch (NotFoundException e){
            throw new com.google.api.server.spi.response.NotFoundException("Golfcourse " + name + " " + tee + " not in database");
        }
        return response;
    }


    @ApiMethod(name = "searchCourses")
    public List<GolfCourse> searchGolfCourse(@Named("search_str") String search_str, User user) throws com.google.api.server.spi.response.NotFoundException{
        ApiUseLogger.logUse(user,"searchCourses");

        List<GolfCourse> response;
        try{
            response = CourseList.getAllStartsWith(search_str);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new com.google.api.server.spi.response.NotFoundException("Search Did not return match");
        }
        return response;
    }


    @ApiMethod(name = "addCourseLength")
    public GolfCourse addCourseLength(@Named("id") long id, @Named("length") int length, @Named("unit") String unit, User user) throws com.google.api.server.spi.response.NotFoundException{
        ApiUseLogger.logUse(user,"addCourseLength");
        GolfCourse result;

        try{
            result = GolfCourse.getById(id);
            result.setLength(length);
            result.setUnit(unit);
            result.save();
        }
        catch (NotFoundException e) {
            throw new com.google.api.server.spi.response.NotFoundException("Invalid Course ID. No course found");
        }
        return result;
    }

    @ApiMethod(name = "addCoursePar")
    public GolfCourse addCoursePar(@Named("id") long id, @Named("par") int par, User user) throws com.google.api.server.spi.response.NotFoundException{
        ApiUseLogger.logUse(user,"addCoursePar");
        GolfCourse result;

        try{
            result = GolfCourse.getById(id);
            result.setPar(par);
            result.save();
        }
        catch (NotFoundException e) {
            throw new com.google.api.server.spi.response.NotFoundException("Invalid Course ID. No course found");
        }
        return result;
    }

    @ApiMethod(name = "addCourseSlopeRating")
    public GolfCourse addCourseSlopeValue(@Named("id") long id, @Named("maleSlope") int maleSlope, @Named("femaleSlope") int femaleSlope, User user) throws com.google.api.server.spi.response.NotFoundException{
        ApiUseLogger.logUse(user,"addCourseSlopeRating");
        GolfCourse result;

        try{
            result = GolfCourse.getById(id);
            result.setMale_courseslope(maleSlope);
            result.setFemale_courseslope(femaleSlope);
            result.save();
        }
        catch (NotFoundException e) {
            throw new com.google.api.server.spi.response.NotFoundException("Invalid Course ID. No course found");
        }
        return result;
    }


    @ApiMethod(name = "addCourseRating")
    public GolfCourse addCourseRating(@Named("id") long id, @Named("maleRating") double maleRating, @Named("femaleRating") double femaleRating, User user) throws com.google.api.server.spi.response.NotFoundException{
        ApiUseLogger.logUse(user,"addCourseRating");
        GolfCourse result;

        try{
            result = GolfCourse.getById(id);
            result.setMale_coursevalue(maleRating);
            result.setFemale_coursevalue(femaleRating);
            result.save();
        }
        catch (NotFoundException e) {
            throw new com.google.api.server.spi.response.NotFoundException("Invalid Course ID. No course found");
        }
        return result;
    }


    @ApiMethod(name = "addHoleData")
    public GolfHole addHoleData(@Named("id") long id, @Named("holeNr") int hole, @Named("par") int par, @Named("index") int index, @Named("length") int length, @Named("name") String name, User user) throws com.google.api.server.spi.response.NotFoundException{
        ApiUseLogger.logUse(user,"addHoleData");
        GolfCourse result;
        GolfHole endResult;

        try{
            result = GolfCourse.getById(id);
            endResult = result.setGolfhole(hole,par,name,length,index);
            result.save();
        }
        catch (NotFoundException e) {
            throw new com.google.api.server.spi.response.NotFoundException("Invalid Course ID. No course found");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new com.google.api.server.spi.response.NotFoundException("Hole number out of bounds");
        }
        return endResult;
    }

    @ApiMethod(name = "getHoleData")
    public GolfHole getHoleData(@Named("id") long id, @Named("holeNr") int hole, User user) throws com.google.api.server.spi.response.NotFoundException{
        ApiUseLogger.logUse(user,"getHoleData");
        GolfCourse result;
        GolfHole endResult;

        try{
            result = GolfCourse.getById(id);
            endResult = result.getGolfHole(hole);
        }
        catch (NotFoundException e) {
            throw new com.google.api.server.spi.response.NotFoundException("Invalid Course ID. No course found");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new com.google.api.server.spi.response.NotFoundException("Hole data does not exist");
        }
        return endResult;
    }

    @ApiMethod(name = "getHandicapGivenShots")
    public Result getHandicapGivenShots(@Named("id") long id, @Named("handicap") double hc, @Named("sex") boolean male, User user) throws com.google.api.server.spi.response.NotFoundException{
        ApiUseLogger.logUse(user,"getHandicapGivenShots");
        GolfCourse result;
        int givenshots = 0;

        try{
            result = GolfCourse.getById(id);
            if(male)
                givenshots = GolfCourse.getShotsGiven(hc,result.getMale_courseslope(),result.getMale_coursevalue(),result.getPar());
            else
                givenshots = GolfCourse.getShotsGiven(hc,result.getFemale_courseslope(),result.getFemale_coursevalue(),result.getPar());
        }
        catch (NotFoundException e) {
            throw new com.google.api.server.spi.response.NotFoundException("Invalid Course ID. No course found");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new com.google.api.server.spi.response.NotFoundException("Hole data does not exist");
        }
        return new Result(Integer.toString(givenshots));
    }




}