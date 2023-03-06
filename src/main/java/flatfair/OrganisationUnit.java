package flatfair;

public class OrganisationUnit {

	String name;
	OrganisationUnitConfig config;
	OrganisationUnit parent;

	public OrganisationUnit(String name, OrganisationUnitConfig config) {
		this.name = name;
		this.config = config;
	}

	public OrganisationUnit(String name, OrganisationUnit parent) {
		this.name = name;
		this.parent = parent;
	}

	public OrganisationUnit(String name, OrganisationUnitConfig config, OrganisationUnit parent) {
		this.name = name;
		this.config = config;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrganisationUnitConfig getConfig() {
		return config;
	}

	public void setConfig(OrganisationUnitConfig config) {
		this.config = config;
	}

	public OrganisationUnit getParent() {
		return parent;
	}

	public void setParent(OrganisationUnit parent) {
		this.parent = parent;
	}

}
