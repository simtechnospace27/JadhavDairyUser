package simtechnospace.tech.jadhavdairy.pojo_class;

public class UserDetails {

    String mUserAddress, mRequirements, mUnit, mDeliveryBoy, mApprovalStatus, mCustomerName, mMobileNo, mEmailId, mUserJoinDate;

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


    public String getUserAddress() {
        return mUserAddress;
    }

    public void setUserAddress(String userAddress) {
        this.mUserAddress = userAddress;
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

    public String getDeliveryBoy() {
        return mDeliveryBoy;
    }

    public void setDeliveryBoy(String deliveryBoy) {
        this.mDeliveryBoy = deliveryBoy;
    }

    public String getApprovalStatus() {
        return mApprovalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.mApprovalStatus = approvalStatus;
    }

    public String getCustomerName() {
        return mCustomerName;
    }

    public void setCustomerName(String customerName) {
        this.mCustomerName = customerName;
    }

    public String getMobileNo() {
        return mMobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mMobileNo = mobileNo;
    }

    public String getEmailId() {
        return mEmailId;
    }

    public void setEmailId(String emailId) {
        this.mEmailId = emailId;
    }

    public String getUserJoinDate() {
        return mUserJoinDate;
    }

    public void setUserJoinDate(String userJoinDate) {
        this.mUserJoinDate = userJoinDate;
    }
}
