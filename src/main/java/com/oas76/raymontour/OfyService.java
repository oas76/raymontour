package com.oas76.raymontour;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;

import java.io.Closeable;

/**
 * Created by oddaskaf on 21.10.14.
 */
public class OfyService {

    static {
        ObjectifyService.factory().register(GolfCourse.class);
        ObjectifyService.factory().register(GolfPlayer.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }

    public static Closeable begin() { return ObjectifyService.begin(); }

}
