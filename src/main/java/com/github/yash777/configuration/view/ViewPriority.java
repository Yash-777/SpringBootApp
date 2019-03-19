package com.github.yash777.configuration.view;

public enum ViewPriority {
	Thymeleaf(1),
	FreeMarker(20),
	Jsp(50);
	
	private int id;

	ViewPriority( int id ) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
