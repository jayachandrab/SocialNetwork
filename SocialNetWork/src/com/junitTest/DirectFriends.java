package com.junitTest;

import static org.junit.Assert.*; 

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.socialNetworkProject.SocialNetwork;

public class DirectFriends {

	@Test
	public void testFindDirectFriends() throws FileNotFoundException, IOException, ParseException 
	{		
		//SocialNetwork test=new SocialNetwork();
		SocialNetwork.junitTest();
		String result=SocialNetwork.findDirectFriends((long) 5);
		assertEquals("Ben,John,Sandra,Amy,Sarah,", result);
	}

}
