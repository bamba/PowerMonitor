package za.lolo.www;

import java.util.List;

public class Station {
	public String name;
	public int id;
	public String status;
	public Location location = new Location();
	public List<Detail> details;
}
