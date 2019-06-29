package simtechnospace.tech.jadhavdairy.pojo_class;

public class BillDetails {

    String mRequirements, mUnit, mDayCount, mFromDate, mToDate, mPerLiterCost;

    public BillDetails() {
    }

    public BillDetails(String requirements, String unit, String dayCount, String fromDate, String toDate, String  perLiterCost) {
        this.mRequirements = requirements;
        this.mUnit = unit;
        this.mDayCount = dayCount;
        this.mFromDate = fromDate;
        this.mToDate = toDate;
        this.mPerLiterCost = perLiterCost;
    }

    public String getPerLiterCost() {
        return mPerLiterCost;
    }

    public void setPerLiterCost(String perLiterCost) {
        this.mPerLiterCost = perLiterCost;
    }

    public String getRequirements() {
        return mRequirements;
    }

    public void setRequirements(String requirements) {
        this.mRequirements = requirements;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        this.mUnit = unit;
    }

    public String getDayCount() {
        return mDayCount;
    }

    public void setDayCount(String dayCount) {
        this.mDayCount = dayCount;
    }

    public String getFromDate() {
        return mFromDate;
    }

    public void setFromDate(String fromDate) {
        this.mFromDate = fromDate;
    }

    public String getToDate() {
        return mToDate;
    }

    public void setToDate(String toDate) {
        this.mToDate = toDate;
    }

}
