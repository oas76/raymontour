package com.oas76.raymontour.tests;




import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

import com.oas76.raymontour.*;


import java.io.Closeable;
import java.util.List;

public class GolfPlayerTest {
    GolfPlayer player = null;
    final LocalServiceTestHelper helper =
                new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @org.junit.Before
    public void setUp() throws Exception {
        player = new GolfPlayer("test@test.no","Tester","Testesen","tester76",Constants.MALE_PLAYER,12.8);
        helper.setUp();
        //session = ObjectifyService.begin();
        assertNotEquals(player, null);


    }

    @org.junit.After
    public void tearDown() throws Exception {
        player = null;
        helper.tearDown();
        assertEquals(player,null);
    }

    @org.junit.Test
    public void testEmail() throws Exception {
        player.setEmail("new@test.no");
        assertEquals(player.getEmail(),"new@test.no");
    }

    @org.junit.Test
    public void testFirst_name() throws Exception {
        player.setFirst_name("New");
        assertEquals(player.getFirst_name(),"New");

    }

    @org.junit.Test
    public void testSecond_name() throws Exception {
        player.setSecond_name("Newton");
        assertEquals(player.getSecond_name(),"Newton");

    }

    @org.junit.Test
    public void testNic() throws Exception {
        player.setNic("new76");
        assertEquals(player.getNic(),"new76");
    }

    @org.junit.Test
    public void testSex() throws Exception {
        player.setSex(Constants.MALE_PLAYER);
        assertEquals(player.isSex(),Constants.MALE_PLAYER);
        player.setSex(Constants.FEMALE_PLAYER);
        assertEquals(player.isSex(),Constants.FEMALE_PLAYER);
        assertNotEquals(Constants.MALE_PLAYER,Constants.FEMALE_PLAYER);

    }

    @org.junit.Test
    public void testHandicap() throws Exception {
        player.setHandicap(10.1);
        assertEquals("Checking HC",player.getHandicap(),10.1,0.001);
        player.setHandicap(-4.1);
        assertEquals("Checking for negative HC", player.getHandicap(), -4.0, 0.001);
        player.setHandicap(36.1);
        assertEquals("Checking for excesive HC", player.getHandicap(),36.0,0.001);
        player.setHandicap(10.2345);
        assertEquals("OneDecimal check HC", player.getHandicap(), 10.2, 0.001);

    }

    @org.junit.Test
    public void testFavouriteCourses() throws Exception {

    }

    @org.junit.Test
    public void testPlayersFollowing() throws Exception {

    }

    @org.junit.Test
    public void testId() throws Exception {
        player.setId(123456789l);
        assertEquals((long)player.getId(),123456789l);
        player.setId(null);
        assertEquals(player.getId(),null);
    }

    @org.junit.Test
    public void testDbAccess() throws Exception {

        ObjectifyService.run(new VoidWork() {
            public void vrun() {
                player.setEmail("new@test.no");
                player.setId(null);
                long id = player.save();

                List<GolfPlayer> players = GolfPlayer.getAllEmailStartsWith("new");
                assertEquals(players.size(),1);
                assertEquals(id,players.listIterator().next().getId(),0.0000d);

                GolfPlayer player1 = GolfPlayer.getById(id);
                assertEquals(player1.getId(),id,0.0000d);

                player.delete();
                players = GolfPlayer.getAllEmailStartsWith("new");
                assertEquals(players,null);

            }
        });
    }



    @org.junit.Test
    public void testGetById() throws Exception {

    }
}