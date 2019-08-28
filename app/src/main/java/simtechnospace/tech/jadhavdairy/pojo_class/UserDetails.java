package simtechnospace.tech.jadhavdairy.pojo_class;

public class UserDetails {

  public static String mUserAddress, mRequirements, mUnit,mAddnUnit,mAddnRequirement,mAddnMilkType, mDeliveryBoy, mApprovalStatus, mCustomerName, mMobileNo, mEmailId, mUserJoinDate, mMilkType, mSocietyName, mWingName, mFlatNo;

    public UserDetails(String userAddress, String requirements, String unit, String deliveryBoy, String approvalStatus, String customerName, String mobileNo, String emailId, String userJoinDate, String mMilkType,String mAddnRequirement,String mAddnUnit,String mAddnMilkType,String mSocietyName, String mWingName, String mFlatNo) {
        this.mUserAddress = userAddress;
        this.mRequirements = requirements;
        this.mUnit = unit;
        this.mDeliveryBoy = deliveryBoy;
        this.mApprovalStatus = approvalStatus;
        this.mCustomerName = customerName;
        this.mMobileNo =  mobileNo;
        this.mEmailId = emailId;
        this.mUserJoinDate = userJoinDate;
        this.mMilkType = mMilkType;
        this.mAddnMilkType = mAddnMilkType;
        this.mAddnUnit = mAddnUnit;
        this.mAddnRequirement = mAddnRequirement;
        this.mSocietyName = mSocietyName;
        this.mWingName = mWingName;
        this.mFlatNo = mFlatNo;
    }

    public static String getmSocietyName() {
        return mSocietyName;
    }

    public static void setmSocietyName(String mSocietyName) {
        UserDetails.mSocietyName = mSocietyName;
    }

    public static String getmWingName() {
        return mWingName;
    }

    public static void setmWingName(String mWingName) {
        UserDetails.mWingName = mWingName;
    }

    public static String getmFlatNo() {
        return mFlatNo;
    }

    public static void setmFlatNo(String mFlatNo) {
        UserDetails.mFlatNo = mFlatNo;
    }

    public static String getmMilkType() {
        return mMilkType;
    }

    public void setmMilkType(String mMilkType) {
        UserDetails.mMilkType = mMilkType;
    }

    public UserDetails() {
    }

    public static String getmUnit() {
        return mUnit;
    }

    public static void setmUnit(String mUnit) {
        UserDetails.mUnit = mUnit;
    }

    public static String getmAddnUnit() {
        return mAddnUnit;
    }

    public static String getmAddnRequirement() {
        return mAddnRequirement;
    }

    public static String getmAddnMilkType() {
        return mAddnMilkType;
    }

    public void setmAddnMilkType(String mMilkType) {
        UserDetails.mAddnMilkType = mMilkType;
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
