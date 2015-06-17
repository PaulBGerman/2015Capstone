package com.academy.capstone;

public class User {
	public String userID = "";
	public String username = "";
	public String password = "";
	public String firstName = "";
	public String lastName = "";
	public String addr1 = "";
	public String addr2 = "";
	public String city = "";
	public String state = "";
	public String zip = "";
	public String homePhone = "";
	public String cellPhone = "";
	public String officePhone = "";
	public String companyName = "";
	public String branchLocation = "";
	public String foodID = "";
	public String addInfo = "";
	public String active = "";
	
	public User(){
		
	}
	
	public User(String IUsername){
		this.username = IUsername;
	}
	
	public User(String iUsername,
				String iPassword,	
				String IFirstName,
				String ILastName,
				String IAddr1,
				String IAddr2,
				String ICity,
				String IState,
				String IZip,
				String IHomePhone,
				String ICellPhone,
				String IOfficePhone,
				String ICompanyName,
				String IBranchLocation,
				String IFoodID,
				String IAddInfo,
				String IActive) {
		this.username = iUsername;
		this.password = iPassword;
		if(IFirstName != null)
			{this.firstName = IFirstName;}
		if(ILastName != null)
			{this.lastName = ILastName;}
		if(IAddr1 != null)
			{this.addr1 = IAddr1;}
		if(IAddr2 != null)
			{this.addr2 = IAddr2;}
		if(ICity != null)
			{this.city = ICity;}
		if(IState != null)
			{this.state = IState;}
		if(IZip != null)
			{this.zip = IZip;}
		if(IHomePhone != null)
			{this.homePhone = IHomePhone;}
		if(ICellPhone != null)
			{this.cellPhone = ICellPhone;}
		if(IOfficePhone != null)
			{this.officePhone = IOfficePhone;}
		if(ICompanyName != null)
			{this.companyName = ICompanyName;}
		if(IBranchLocation != null)
			{this.branchLocation = IBranchLocation;}
		if(IFoodID != null)
			{this.foodID = IFoodID;}
		if(IAddInfo != null)
			{this.addInfo = IAddInfo;}
		if(IActive != null)
			{this.active = IActive;}
		
	}
	

}
