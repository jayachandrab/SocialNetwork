package com.junitTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.socialNetworkProject.SocialNetwork;

public class FriendsOfFriendsTest {

	@Test
	public void test() throws FileNotFoundException, IOException, ParseException {
		
		SocialNetwork.junitTest();
		String result=SocialNetwork.friendsOfFriends((long) 5);
		assertEquals("Rob,Victor,Peter,Sarah,Peter,Peter,Amy,Catriona,Katy,Peter,Sandra,Ben,Peter,Katy,Laura,Susan,", result);
		//fail("Not yet implemented");//
	}

}
