package com.oas76.raymontour;

import com.googlecode.objectify.*;
import com.googlecode.objectify.annotation.*;
import com.googlecode.objectify.cmd.Query;
import com.googlecode.objectify.cmd.SimpleQuery;

import javax.jdo.annotations.Embedded;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


/**
 * Created by oddaskaf on 29.10.14.
 */

@Entity
@Cache
public class GolfPlayer {

    @Id
    private Long id;

    @Index
    private String email;

    private String first_name;

    private String second_name;

    private String nic;

    private boolean sex;

    private double handicap;

    @Embedded
    private Set<String> playersFollowing;

    @Embedded
    private Set<String> favouriteCourses;


    public GolfPlayer(){

    }

    public GolfPlayer(String email, String first_name, String second_name, String nic, boolean sex, double hc){
        this.email = email;
        this.first_name = first_name;
        this.second_name = second_name;
        this.nic = nic;
        this.sex = sex;
        this.handicap = hc;

        playersFollowing = new HashSet<>();
        favouriteCourses = new HashSet<>();

    }

    public GolfPlayer(String email, String nic, boolean sex, double hc){
        this.email = email;
        this.nic = nic;
        this.sex = sex;
        this.handicap = hc;

        playersFollowing = new HashSet<>();
        favouriteCourses = new HashSet<>();

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public double getHandicap() {
        return handicap;
    }

    public void setHandicap(double handicap) {
        if(handicap < -4.0)
            this.handicap = -4.0;
        else if(handicap > 36)
            this.handicap = 36.0;
        else {
            double d_temp = handicap * 10;
            int i_temp = (int)d_temp;
            d_temp = (double)i_temp;
            this.handicap = d_temp/10.0d;
        }
    }

    public Set<String> getPlayersFollowing() {
        return playersFollowing;
    }

    public Set<String> getFavouriteCourses() {
        return favouriteCourses;
    }

    public void setPlayersFollowing(String id){
        this.playersFollowing.add(id);

    }

    public void setFavouriteCourses(String id){
        this.favouriteCourses.add(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public long save() {
        com.googlecode.objectify.Result<Key<GolfPlayer>> result = OfyService.ofy().save().entity(this);
        return result.now().getId();
    }

    public void delete() {
        OfyService.ofy().delete().entity(this);
    }

    public static List<GolfPlayer> getAllEmailStartsWith(String search_str) throws NotFoundException {
        List<GolfPlayer> result = null;
        Query<GolfPlayer> query = OfyService.ofy().load().type(GolfPlayer.class).filter("email >=", search_str).filter("email <", search_str + "\ufffd");
        if (query != null && query.count() > 0){
            result = query.list();

        }
        return result;
    }

    public static GolfPlayer getById(long id) throws NotFoundException {
        SimpleQuery<GolfPlayer> query = OfyService.ofy().load().type(GolfPlayer.class).filterKey(Key.create(GolfPlayer.class, id));
        if (query != null && query.count() > 0){
            for (GolfPlayer player : query) {
                if (player.getId() == id)
                    return player;
            }
        }

        throw new NotFoundException(Key.create(GolfCourse.class,Long.toString(id)));
    }



}
