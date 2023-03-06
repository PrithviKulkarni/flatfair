package flatfair;

public class OrganisationUnitConfig {

	boolean has_fixed_membership;
	int fixed_membership_fee;

	public OrganisationUnitConfig(boolean has_fixed_membership, int fixed_membership_fee) {
		this.has_fixed_membership = has_fixed_membership;
		this.fixed_membership_fee = fixed_membership_fee;
	}

	public boolean get_fixed_membership() {
		return has_fixed_membership;
	}

	public void set_fixed_membership(boolean has_fixed_membership) {
		this.has_fixed_membership = has_fixed_membership;
	}

	public int get_fixed_membership_fee() {
		return fixed_membership_fee;
	}

	public void set_fixed_membership_fee(int fixed_membership_fee) {
		this.fixed_membership_fee = fixed_membership_fee;
	}

}
