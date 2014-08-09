package com.junitTest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.socialNetworkProject.SocialNetwork;

public class SuggestedFriendsTest {

	@Test
	public void SuggestedFriendstest() throws FileNotFoundException, IOException, ParseException {

		SocialNetwork.junitTest();
		String result=SocialNetwork.suggestedFriends((long) 5);
		assertEquals("Rob Victor Catriona Katy Laura Susan ", result);
		//fail("Not yet implemented");//
	}

}
