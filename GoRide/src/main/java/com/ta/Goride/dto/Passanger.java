package com.ta.Goride.dto;
//@Entity
public class Passanger {
//	@ID
private int passangerid;
private String passangername;
private String preferences;
private long contactno;
public int getPassangerid() {
	return passangerid;
}
public void setPassangerid(int passangerid) {
	this.passangerid = passangerid;
}
public String getPassangername() {
	return passangername;
}
public void setPassangername(String passangername) {
	this.passangername = passangername;
}
public String getPreferences() {
	return preferences;
}
public void setPreferences(String preferences) {
	this.preferences = preferences;
}
public long getContactno() {
	return contactno;
}
@Override
public String toString() {
	return "Passanger [passangerid=" + passangerid + ", passangername=" + passangername + ", preferences=" + preferences
			+ ", contactno=" + contactno + "]";
}
public void setContactno(long contactno) {
	this.contactno = contactno;
}



}
