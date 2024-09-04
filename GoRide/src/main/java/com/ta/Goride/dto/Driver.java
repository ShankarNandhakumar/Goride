package com.ta.Goride.dto;

public class Driver {
private int driverId;
private String drivername;
private long licenseno;
private String vehicletype;

private boolean available;

public int getDriverId() {
	return driverId;
}
public void setDriverId(int driverId) {
	this.driverId = driverId;
}
public boolean isAvailable() {
	return available;
}
public void setAvailable(boolean available) {
	this.available = available;
}
public String getDrivername() {
	return drivername;
}
public void setDrivername(String drivername) {
	this.drivername = drivername;
}
public long getLicenseno() {
	return licenseno;
}
public void setLicenseno(long licenseno) {
	this.licenseno = licenseno;
}
public String getVehicletype() {
	return vehicletype;
}
public void setVehicletype(String vehicletype) {
	this.vehicletype = vehicletype;
}

@Override
public String toString() {
	return "Driver [driverId=" + driverId + ", drivername=" + drivername + ", licenseno=" + licenseno + ", vehicletype="
			+ vehicletype + "]";
}


}
