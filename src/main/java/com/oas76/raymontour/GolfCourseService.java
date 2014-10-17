package com.oas76.raymontour;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.ObjectifyService;


import java.util.ArrayList;

import javax.inject.Named;

/**
 * Defines v1 of a helloworld API, which provides simple "greeting" methods.
 */
@Api(
    name = Constants.API_PRODUCT_NAME,
    version = "v1",
    scopes = {Constants.EMAIL_SCOPE},
    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
    audiences = {Constants.ANDROID_AUDIENCE}
)
public class GolfCourseService {


  public static ArrayList<HelloGreeting> greetings = new ArrayList<>();

  static {
      // Registering Objectify Enteties for datastore
      ObjectifyService.register(GolfCourse.class);
  }

  public HelloGreeting getGreeting(@Named("id") Integer id, User user) throws NotFoundException {
    try {
      return greetings.get(id);
    } catch (IndexOutOfBoundsException e) {
      throw new NotFoundException("Greeting not found with an index: " + id);
    }
  }

  public ArrayList<HelloGreeting> listGreeting(User user) {
    return greetings;
  }

  @ApiMethod(name = "greetings.multiply", httpMethod = "post")
  public HelloGreeting insertGreeting(@Named("times") Integer times, HelloGreeting greeting, User user) {
    HelloGreeting response = new HelloGreeting();
    StringBuilder responseBuilder = new StringBuilder();
    for (int i = 0; i < times; i++) {
      responseBuilder.append(greeting.getMessage());
    }
    response.setMessage(responseBuilder.toString());
    return response;
  }

  @ApiMethod(name = "greetings.authed", path = "hellogreeting/authed")
  public HelloGreeting authedGreeting(User user) {
    HelloGreeting response = new HelloGreeting("hello " + user.getEmail());
    return response;
  }
}
