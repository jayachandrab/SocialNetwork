/*
 * Author:B.Jayachandra 
 * Eamil:jayachandra1805@gmail.com
 * 
 * 
 * */
package com.jsonread;

import java.io.FileNotFoundException; 
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// to store each person details 

class Person {
	Long personId;
	String firstName;
	String surName;
	int personAge;
	String gender;
	ArrayList<Friends> frinds;

	public Person(Long personId, String firstName, String surname, int age,
			String gender, ArrayList<Friends> frinds) {
		this.frinds = frinds;

		this.personId = personId;
		this.firstName = firstName;
		this.surName = surname;
		this.personAge = age;
		this.gender = gender;

		// TODO Auto-generated constructor stub
	}

}

// to store list of friend id's
class Friends {
	Long friendId;

	Friends(Long id) {
		this.friendId = id;
	}

}

public class SocialNetwork {
	static Hashtable<Long, Person> network;

	public SocialNetwork() {
		network = new Hashtable<Long, Person>();
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ParseException {
		// TODO Auto-generated method stub
		SocialNetwork ob = new SocialNetwork();
		JSONParser parser = new JSONParser();
		// ht=new Hashtable();
		// HashMap<Person,Friends> ht=new HashMap();

		JSONArray a = (JSONArray) parser.parse(new FileReader("9KHVFSpj.txt"));

		for (Object o : a) {
			JSONObject jsonObject = (JSONObject) o;

			Long id = (Long) jsonObject.get("id");
			String fname = (String) jsonObject.get("firstName");
			String surname = (String) jsonObject.get("surname");			
			String gender = (String) jsonObject.get("gender");
			Object age = jsonObject.get("age");
			int page = 0;
			if (age != null) {
				page = Integer.parseInt(age.toString());
			}

			// System.out.println(page);

			JSONArray friends = (JSONArray) jsonObject.get("friends");
			ArrayList frind = new ArrayList<Friends>();
			for (Object c : friends) {
				// int n=Integer.parseInt((String) c);
				// Long a = (((Long) c).longValue();
				String tempid = c.toString();
				Long aa = Long.parseLong(tempid);
				Friends f = new Friends(aa);
				frind.add(f);
				// System.out.println(c+"");
			}
			Person p = new Person(id, fname, surname, page, gender, frind);
			// System.out.println(id + p.firstName);
			network.put(id, p);

		}

		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out
					.println("==============Enter your choice===============");
			System.out.println("1 to direct friends");
			System.out.println("2 to find friends of friend");
			System.out.println("3 for Suggested friends");
			System.out.println("4 to exit");
			System.out
					.println("==============Enter your choice===============\n");

			int key = sc.nextInt();

			switch (key) {
			case 1:
				Long id;
				System.out.println("user id");

				id = sc.nextLong();
				findDirectFriends(id);
				
				break;
			case 2:

				System.out.println("user id");

				id = sc.nextLong();
				friendsOfFriends(id);
				
			case 3:
				System.out.println("user id");

				id = sc.nextLong();
				suggestedFriends(id);
				break;
			case 4:
				System.exit(0);
			default:
				System.out.println("choose correct option");
				break;

			}
		}
	}

	public static String findDirectFriends(Long id) {
		String temp="";
		if (network.containsKey(id)) {
			Person p = (Person) network.get(id);

			System.out.println("Direct Friends of " + p.firstName + ":");
			for (int i = 0; i < p.frinds.size(); i++) {
				Long FrindId = p.frinds.get(i).friendId;
				Person pp = (Person) network.get(FrindId);
				System.out.print(pp.firstName + ",");
				temp=temp+pp.firstName + ",";

			}
			System.out.println();

		}
		return temp;

	}

	// Function to find friends of friends
	public static String friendsOfFriends(Long id) {
String temp="";
		if (network.containsKey(id)) {
			Person p = (Person) network.get(id);
			System.out.println("=====Friends of Friends====");
			System.out.println();
			for (int i = 0; i < p.frinds.size(); i++) {
				Long FrindId = p.frinds.get(i).friendId;
				Person pp = (Person) network.get(FrindId);
				System.out.println("\nFriends of " + pp.firstName + ":");
				ArrayList<Person> ob = findFriend(pp.personId);
				for (int j = 0; j < ob.size(); j++) {
					System.out.print(ob.get(j).firstName + ",");
					temp=temp+ob.get(j).firstName + ",";
				}
				System.out.println();
			}

		} else {
			System.out.println("The preson doesn't exisit with this key");
		}
		return temp;

	}

	// To find the Friends of friends
	public static ArrayList<Person> findFriend(Long id) {
		ArrayList<Person> persons = new ArrayList<Person>();
		if (network.containsKey(id)) {
			Person p = (Person) network.get(id);
			
			for (int i = 0; i < p.frinds.size(); i++) {
				Long FrindId = p.frinds.get(i).friendId;
				Person pp = (Person) network.get(FrindId);
				persons.add(pp);
				
			}
		

		}
		return persons;

	}

	// Finding suggested friends
	public static String suggestedFriends(Long id) {
		String suggestFriends="";
		ArrayList suggested = new ArrayList();
		if (network.containsKey(id)) {
			Person p = (Person) network.get(id);
			ArrayList<Friends> checkfriends = p.frinds;
			ArrayList<Long> checkfriend2 = new ArrayList<Long>(); // it is used
																	// to check
																	// whether
																	// the
																	// suggested
																	// friends
																	// is direct
																	// friend of
																	// him/her
			for (int i = 0; i < checkfriends.size(); i++) {
				checkfriend2.add(checkfriends.get(i).friendId);
			}

			System.out.println("Suggested Friends for " + p.firstName + ":");
			for (int i = 0; i < p.frinds.size(); i++) {
				Long FrindId = p.frinds.get(i).friendId;
				Person pp = (Person) network.get(FrindId);

				ArrayList<Person> ob = findFriend(pp.personId); // re using
																// findFriend
																// Function

				for (int j = 0; j < ob.size(); j++) {

					if (!checkfriend2.contains(ob.get(j).personId)
							&& !suggested.contains(ob.get(j).firstName)
							&& ob.get(j).personId != id) {
						suggested.add(ob.get(j).firstName);

					}

				}

			}
			for (int k = 0; k < suggested.size(); k++) {
				System.out.println(suggested.get(k));
				suggestFriends=suggestFriends+suggested.get(k)+" ";
			}
			System.out.println();

		} else {
			System.out.println("The id doesn't exist");
		}
		//System.out.println("==="+suggestFriends);
		return suggestFriends;

	}
	public static void junitTest() throws FileNotFoundException, IOException, ParseException{
		// TODO Auto-generated method stub
		SocialNetwork ob = new SocialNetwork();
		JSONParser parser = new JSONParser();
	

		JSONArray a = (JSONArray) parser.parse(new FileReader("9KHVFSpj.txt"));

		for (Object o : a) {
			JSONObject jsonObject = (JSONObject) o;

			Long id = (Long) jsonObject.get("id");
			String fname = (String) jsonObject.get("firstName");
			String surname = (String) jsonObject.get("surname");			
			String gender = (String) jsonObject.get("gender");
			Object age = jsonObject.get("age");
			int page = 0;
			if (age != null) {
				page = Integer.parseInt(age.toString());
			}

	

			JSONArray friends = (JSONArray) jsonObject.get("friends");
			ArrayList frind = new ArrayList<Friends>();
			for (Object c : friends) {
			
				String tempid = c.toString();
				Long aa = Long.parseLong(tempid);
				Friends f = new Friends(aa);
				frind.add(f);
				
			}
			Person p = new Person(id, fname, surname, page, gender, frind);
		
			network.put(id, p);

		}
	}

}
