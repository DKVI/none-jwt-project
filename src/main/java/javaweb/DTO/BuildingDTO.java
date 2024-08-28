package javaweb.DTO;

public class BuildingDTO {
	private Integer numberOfBasement;
	private String name;
	private String address;
	
	public BuildingDTO() {
		// TODO Auto-generated constructor stub
	}

	public BuildingDTO(Integer numberOfBasement, String name, String address) {
		super();
		this.numberOfBasement = numberOfBasement;
		this.name = name;
		this.address = address;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "BuildingDTO [numberofbasement=" +numberOfBasement + ", name=" + name + ", address=" + address + "]";
	}

}
