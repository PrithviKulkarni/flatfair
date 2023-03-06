package flatfair;

public class Runner {

	public static void main(String args[]) {
		OrganisationUnit client = new OrganisationUnit("client", new OrganisationUnitConfig(false, 0));
		OrganisationUnit division_a = new OrganisationUnit("division_a", new OrganisationUnitConfig(false, 0), client);
		OrganisationUnit division_b = new OrganisationUnit("division_b", new OrganisationUnitConfig(true, 35000),
				client);
		OrganisationUnit area_a = new OrganisationUnit("area_a", new OrganisationUnitConfig(true, 45000), division_a);
		OrganisationUnit area_b = new OrganisationUnit("area_b", new OrganisationUnitConfig(false, 0), division_a);
		OrganisationUnit area_c = new OrganisationUnit("area_c", new OrganisationUnitConfig(true, 45000), division_b);
		OrganisationUnit area_d = new OrganisationUnit("area_d", new OrganisationUnitConfig(false, 0), division_b);
		OrganisationUnit branch_a = new OrganisationUnit("branch_a", null, area_a);
		OrganisationUnit branch_b = new OrganisationUnit("branch_b", new OrganisationUnitConfig(false, 0), area_a);
		OrganisationUnit branch_c = new OrganisationUnit("branch_c", new OrganisationUnitConfig(false, 0), area_a);
		OrganisationUnit branch_d = new OrganisationUnit("branch_d", null, area_a);
		OrganisationUnit branch_e = new OrganisationUnit("branch_e", new OrganisationUnitConfig(false, 0), area_b);
		OrganisationUnit branch_f = new OrganisationUnit("branch_f", new OrganisationUnitConfig(false, 0), area_b);
		OrganisationUnit branch_g = new OrganisationUnit("branch_g", new OrganisationUnitConfig(false, 0), area_b);
		OrganisationUnit branch_h = new OrganisationUnit("branch_h", new OrganisationUnitConfig(false, 0), area_b);
		OrganisationUnit branch_i = new OrganisationUnit("branch_i", new OrganisationUnitConfig(false, 0), area_c);
		OrganisationUnit branch_j = new OrganisationUnit("branch_j", new OrganisationUnitConfig(false, 0), area_c);
		OrganisationUnit branch_k = new OrganisationUnit("branch_k", new OrganisationUnitConfig(true, 25000), area_c);
		OrganisationUnit branch_l = new OrganisationUnit("branch_l", new OrganisationUnitConfig(false, 0), area_c);
		OrganisationUnit branch_m = new OrganisationUnit("branch_m", null, area_d);
		OrganisationUnit branch_n = new OrganisationUnit("branch_n", new OrganisationUnitConfig(false, 0), area_d);
		OrganisationUnit branch_o = new OrganisationUnit("branch_o", new OrganisationUnitConfig(false, 0), area_d);
		OrganisationUnit branch_p = new OrganisationUnit("branch_p", new OrganisationUnitConfig(false, 0), area_d);

		try {
			System.out.println(calculate_membership_fee(200000, "month", branch_a));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static int calculate_membership_fee(int rent_amount, String rent_period, OrganisationUnit organisation)
			throws Exception {

		final int MIN_AMOUNT_PER_WEEK = 2500;
		final int MIN_AMOUNT_PER_MONTH = 11000;
		final int MAX_AMOUNT_PER_WEEK = 200000;
		final int MAX_AMOUNT_PER_MONTH = 866000;
		final double VAT = 1.2; // 20%

		if (!rent_period.equals("week") && !rent_period.equals("month")) {
			throw new Exception("Rent period incorrect. Should be either: month or week");
		}
		if (organisation.config == null && organisation.parent != null) {
			while (organisation.config == null) {
				organisation.config = organisation.parent.config;
			}
		}
		if ((rent_period.equals("week") && rent_amount < MIN_AMOUNT_PER_WEEK)
				|| (rent_period.equals("week") && rent_amount > MAX_AMOUNT_PER_WEEK)) {
			throw new Exception("Rent amount incorrect for rent period. Should be between: " + MIN_AMOUNT_PER_WEEK
					+ " - " + MAX_AMOUNT_PER_WEEK);
		}
		if ((rent_period.equals("month") && rent_amount < MIN_AMOUNT_PER_MONTH)
				|| (rent_period.equals("month") && rent_amount > MAX_AMOUNT_PER_MONTH)) {
			throw new Exception("Rent amount incorrect for rent period. Should be between: " + MIN_AMOUNT_PER_MONTH
					+ " - " + MAX_AMOUNT_PER_MONTH);
		}
		if (organisation.config.has_fixed_membership) {
			return organisation.config.fixed_membership_fee;
		}
		
		//Default if rent below £120
		if ((rent_period.equals("week") && rent_amount < 12000) 
				|| (rent_period == "month" && (rent_amount / 4.33) < 12000)) {
			return (int) (12000 * VAT);
		}
		if (rent_period.equals("month")) {
			return (int) ((rent_amount / 4.33) * VAT);
		} else {
			return (int) (rent_amount * VAT);
		}
	}
}
