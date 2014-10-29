package com.oas76.raymontour.api;

import com.google.appengine.api.users.User;

/**
 * Created by oddaskaf on 27.10.14.
 */
public final class ApiUseLogger {

    public static void logUse(User user, String api)
    {
        try {
            System.out.println("User: " + user.getEmail() + " accessed API: " + api);
        }
        catch(NullPointerException e)
        {
            System.out.println("User: " + user.getEmail() + " accessed API: " + api);
        }

    }

}
