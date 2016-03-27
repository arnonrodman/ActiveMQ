package com.springTest.springTest;

public class Restaruant{
	
	private String welcomNote;

	public void setWelcomNote(String welcomNote) {
		this.welcomNote = welcomNote;
	}


	public void greetCustomer(){
		System.out.println(welcomNote);
	}

	
	public void init() throws Exception {
		System.out.println("Restaruant is init");		
	}

	
	public void destroy() throws Exception {
		System.out.println("Restaruant is destroying now");		
	}
	
	
	
	
}
