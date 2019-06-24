package simtechnospace.tech.jadhavdairy.pojo_class;

public class UserDetails {

  public static String mUserAddress, mRequirements, mUnit, mDeliveryBoy, mApprovalStatus, mCustomerName, mMobileNo, mEmailId, mUserJoinDate;

    public UserDetails(String userAddress, String requirements, String unit, String deliveryBoy, String approvalStatus, String customerName, String mobileNo, String emailId, String userJoinDate) {
        this.mUserAddress = userAddress;
        this.mRequirements = requirements;
        this.mUnit = unit;
        this.mDeliveryBoy = deliveryBoy;
        this.mApprovalStatus = approvalStatus;
        this.mCustomerName = customerName;
        this.mMobileNo =  mobileNo;
        this.mEmailId = emailId;
        this.mUserJoinDate = userJoinDate;
    }


    public UserDetails() {
    }


    public static String getUserAddress() {
        return mUserAddress;
    }

    public void setUserAddress(String userAddress) {
        this.mUserAddress = userAddress;
    }

    public static String getRequirements() {
        return mRequirements;
    }

    public void setRequirements(String requirements) {
        this.mRequirements = requirements;
    }

    public static String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        this.mUnit = unit;
    }

    public static String getDeliveryBoy() {
        return mDeliveryBoy;
    }

    public void setDeliveryBoy(String deliveryBoy) {
        this.mDeliveryBoy = deliveryBoy;
    }

    public static String getApprovalStatus() {
        return mApprovalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.mApprovalStatus = approvalStatus;
    }

    public static String getCustomerName() {
        return mCustomerName;
    }

    public void setCustomerName(String customerName) {
        this.mCustomerName = customerName;
    }

    public static String getMobileNo() {
        return mMobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mMobileNo = mobileNo;
    }

    public static String getEmailId() {
        return mEmailId;
    }

    public void setEmailId(String emailId) {
        this.mEmailId = emailId;
    }

    public static String getUserJoinDate() {
        return mUserJoinDate;
    }

    public void setUserJoinDate(String userJoinDate) {
        this.mUserJoinDate = userJoinDate;
    }
}
