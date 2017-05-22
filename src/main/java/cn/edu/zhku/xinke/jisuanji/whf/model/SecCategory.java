package cn.edu.zhku.xinke.jisuanji.whf.model;

public class SecCategory {

	private int id;

	private String name;

	private int category;

	@Override
	public String toString() {
		return "SecCategory [id=" + id + ", name=" + name + ", category="
				+ category + "]";
	}

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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

}
