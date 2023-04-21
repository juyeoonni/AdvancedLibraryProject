package dto;

public class MemberDTO {
	String memberID;
	String name;
	int age;
	String address;
	String phoneNumber;
	String dateOfBirth;
	String dateOfMembership;
	
	public MemberDTO(String string, String string2, int int1, String string3, String string4, String string5) {
		// TODO Auto-generated constructor stub
	}
	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDateOfMembership() {
		return dateOfMembership;
	}
	public void setDateOfMembership(String dateOfMembership) {
		this.dateOfMembership = dateOfMembership;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [memberID=" + memberID + ", name=" + name + ", age=" + age + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", dateOfMembership="
				+ dateOfMembership + "]";
	}
	
	
	
}
