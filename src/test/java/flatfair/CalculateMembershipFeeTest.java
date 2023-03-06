package flatfair;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class CalculateMembershipFeeTest {

    @Test
    public void testInvalidRentPeriod() {
        try {
            Runner.calculate_membership_fee(3000, "year",
                    new OrganisationUnit("test", new OrganisationUnitConfig(false, 0)));
            fail("Missing Exception");
        } catch (Exception e) {
            assertEquals("Rent period incorrect. Should be either: month or week", e.getMessage());
        }
    }
    @Test
    public void testRentAmountOutOfRangeForWeek() throws Exception {
        try {
            Runner.calculate_membership_fee(1000, "week",
                    new OrganisationUnit("test", new OrganisationUnitConfig(false, 0)));
            fail("Missing Exception");
        } catch (Exception e) {
            assertEquals("Rent amount incorrect for rent period. Should be between: " + 2500
                    + " - " + 200000, e.getMessage());
        }

    }
    @Test
    public void testRentAmountOutOfRangeForMonth() throws Exception {
        try {
            Runner.calculate_membership_fee(1000000, "month",
                    new OrganisationUnit("test", new OrganisationUnitConfig(false, 0)));
            fail("Missing Exception");
        } catch (Exception e) {
            assertEquals("Rent amount incorrect for rent period. Should be between: " + 11000
                    + " - " + 866000, e.getMessage());
        }

    }
    @Test
    public void testFixedMembershipFee() throws Exception {
        OrganisationUnitConfig config = new OrganisationUnitConfig(true, 5000);
        assertEquals(5000, Runner.calculate_membership_fee(3000, "week", new OrganisationUnit("test", config)));
    }
    @Test
    public void testRentPerWeekLessThanMinimum() throws Exception {
        try {
            Runner.calculate_membership_fee(1000, "week",
                    new OrganisationUnit("test", new OrganisationUnitConfig(false, 0)));
        } catch (Exception e) {
            assertEquals("Rent amount incorrect for rent period. Should be between: " + 2500
                    + " - " + 200000, e.getMessage());
        }
    }
    @Test
    public void testRentPerMonthLessThanMinimum() throws Exception {
        try {
            Runner.calculate_membership_fee(1000, "month",
                    new OrganisationUnit("test", new OrganisationUnitConfig(false, 0)));
        } catch (Exception e) {
            assertEquals("Rent amount incorrect for rent period. Should be between: " + 11000
                    + " - " + 866000, e.getMessage());
        }
    }
    @Test
    public void testRentPerWeekEqualToMinimum() throws Exception {
        assertEquals((int) (12000 * 1.2), Runner.calculate_membership_fee(2500, "week",
                new OrganisationUnit("test", new OrganisationUnitConfig(false, 0))));
    }
    @Test
    public void testRentPerMonthEqualToMinimum() throws Exception {
        assertEquals((int) ((200000 / 4.33) * 1.2), Runner.calculate_membership_fee(200000, "month",
                new OrganisationUnit("test", new OrganisationUnitConfig(false, 0))));
    }
    @Test
    public void testRentPerWeekBetweenMinimumAndMaximum() throws Exception {
        assertEquals((int) (12000 * 1.2), Runner.calculate_membership_fee(5500, "week",
                new OrganisationUnit("test", new OrganisationUnitConfig(false, 0))));
    }
    @Test
    public void testRentPerMonthBetweenMinimumAndMaximum() throws Exception {
        assertEquals((int) ((400000 / 4.33) * 1.2), Runner.calculate_membership_fee(400000, "month",
                new OrganisationUnit("test", new OrganisationUnitConfig(false, 0))));
    }
    @Test
    public void testRentPerWeekEqualToMaximum() throws Exception {
        assertEquals((int) (12000 * 1.2), Runner.calculate_membership_fee(11000, "week",
                new OrganisationUnit("test", new OrganisationUnitConfig(false, 0))));
    }
    @Test
    public void testRentPerMonthEqualToMaximum() throws Exception {
        assertEquals((int) ((866000 / 4.33) * 1.2), Runner.calculate_membership_fee(866000, "month",
                new OrganisationUnit("test", new OrganisationUnitConfig(false, 0))));
    }
    @Test
    public void testMembershipFeeDefaultIfLessThan12000() throws Exception {
        assertEquals((int) (12000 * 1.2), Runner.calculate_membership_fee(11000, "week",
                new OrganisationUnit("test", new OrganisationUnitConfig(false, 0))));
    }
    @Test
    public void testRentPerWeekWithParentOrganisation() throws Exception {
        OrganisationUnit parent = new OrganisationUnit("test_parent", new OrganisationUnitConfig(true, 45000));
        OrganisationUnit org = new OrganisationUnit("test_org", new OrganisationUnitConfig(false, 0), parent);
        assertEquals((int) (13000 * 1.2), Runner.calculate_membership_fee(13000, "week", org));
    }
    @Test
    public void testRentPerMonthWithParentOrganisation() throws Exception {
        OrganisationUnit parent = new OrganisationUnit("test_parent", new OrganisationUnitConfig(true, 45000));
        OrganisationUnit org = new OrganisationUnit("test_org", new OrganisationUnitConfig(false, 0), parent);
        assertEquals((int) ((300000 / 4.33) * 1.2), Runner.calculate_membership_fee(300000, "month", org));
    }
    @Test
    public void testRentPerWeekWithNoConfigAndParent() throws Exception {
        OrganisationUnit parent = new OrganisationUnit("test_parent", new OrganisationUnitConfig(true, 45000));
        OrganisationUnit org = new OrganisationUnit("test_org", parent);
        assertEquals(45000, Runner.calculate_membership_fee(5000, "week", org));
    }
    @Test
    public void testRentPerMonthWithNoConfigAndParent() throws Exception {
        OrganisationUnit parent = new OrganisationUnit("test_parent", new OrganisationUnitConfig(true, 45000));
        OrganisationUnit org = new OrganisationUnit("test_org", parent);
        assertEquals(45000, Runner.calculate_membership_fee(70000, "month", org));
    }

}
