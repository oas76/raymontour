package com.oas76.raymontour.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.NotFoundException;
import com.oas76.raymontour.*;


import javax.inject.Named;
import java.util.List;
import java.util.Set;

/** An endpoint class we are exposing */
@Api(name = "playerapi",
        version = "v1",
        namespace = @ApiNamespace(ownerDomain = "com.oas76.raymontour",
                ownerName = "com.oas76.raymontour",
                packagePath="api"))
public class GolfPlayerApi {


    @ApiMethod(name = "addPlayer")
    public GolfPlayer addPlayer(@Named("email") String email, @Named("nic") String nic, @Named("sex") boolean sex, @Named("handicap") double hc, User user) {
        ApiUseLogger.logUse(user,"addPlayer");

        GolfPlayer response = new GolfPlayer(email,nic,sex,hc);
        response.setId(response.save());
        return response;
    }


    @ApiMethod(name = "searchPlayers")
    public List<GolfPlayer> searchGolfCourse(@Named("search_str") String search_str, User user) throws com.google.api.server.spi.response.NotFoundException{
        ApiUseLogger.logUse(user,"searchPlayer");

        List<GolfPlayer> response;
        try{
            response = GolfPlayer.getAllEmailStartsWith(search_str);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new com.google.api.server.spi.response.NotFoundException("Search Did not return match");
        }
        return response;
    }
    /*

    @ApiMethod(name = "getMyBuddies")
    public Set<String> getMyBuddies(@Named("myid") long id, User user) throws com.google.api.server.spi.response.NotFoundException{
        ApiUseLogger.logUse(user,"getMyBuddies");

        Set<String> response;
        try{
            GolfPlayer player = GolfPlayer.getById(id);
            response = player.getPlayersFollowing();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new com.google.api.server.spi.response.NotFoundException("Search Did not return match");
        }
        return response;
    }

    @ApiMethod(name = "getMyFavouriteCourse")
    public Set<String> getFavCourse(@Named("myid") long id, User user) throws com.google.api.server.spi.response.NotFoundException{
        ApiUseLogger.logUse(user,"getMyFavouriteCourse");

        Set<String> response;
        try{
            GolfPlayer player = GolfPlayer.getById(id);
            response = player.getFavouriteCourses();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new com.google.api.server.spi.response.NotFoundException("Search Did not return match");
        }
        return response;
    }
    */



}
