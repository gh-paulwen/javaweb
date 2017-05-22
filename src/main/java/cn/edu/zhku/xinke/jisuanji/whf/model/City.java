package cn.edu.zhku.xinke.jisuanji.whf.model;

public class City {

	private int id;

	private String name;

	private int superCity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSuperCity() {
		return superCity;
	}

	public void setSuperCity(int superCity) {
		this.superCity = superCity;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", superCity=" + superCity
				+ "]";
	}

}
