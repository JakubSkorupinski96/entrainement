package model;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class CompanyTest {

	@Test
	public void toStringTest() {
		Company company = new Company(1,"Company1");
		String expectedString = "Company [id= 1 , name= Company1 ]";
		expectedString.equals(company.toString());
	}

}
