package com.oas76.raymontour;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;

/**
 * Created by oddaskaf on 21.10.14.
 */
public class OfyService {

    static {
        ObjectifyService.factory().register(GolfCourse.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }

}
