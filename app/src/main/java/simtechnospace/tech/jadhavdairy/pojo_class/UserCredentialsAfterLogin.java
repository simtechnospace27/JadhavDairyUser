package simtechnospace.tech.jadhavdairy.pojo_class;

import android.content.Context;
import android.content.SharedPreferences;

public class UserCredentialsAfterLogin {

    SharedPreferences mSharedPreferences;
    String mUserEmail,mUserPassword,mUserName, mUserId;
    Context mContext;



    public UserCredentialsAfterLogin(Context context) {
        this.mContext = context;
        mSharedPreferences = mContext.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }



    public void removeUserInfo(){
        mSharedPreferences.edit().clear().commit();
    }


    public String getName() {
        mUserName = mSharedPreferences.getString("Name","");
        return mUserName;
    }

    public void setName(String name) {
        this.mUserName = name;
        mSharedPreferences.edit().putString("Name",mUserName).commit();
    }


    public String getUserId() {
        mUserId = mSharedPreferences.getString("Login Id","");
        return mUserId;
    }

    public void setUserId(String userId) {
        this.mUserId = userId;
        mSharedPreferences.edit().putString("Login Id",mUserId).commit();
    }


    public String getEmail() {
        mUserEmail = mSharedPreferences.getString("email","");
        return mUserEmail;
    }

    public void setEmail(String email) {
        this.mUserEmail = email;
        mSharedPreferences.edit().putString("email",mUserEmail).commit();
    }

    public String getPassword() {
        mUserPassword = mSharedPreferences.getString("password","");
        return mUserPassword;
    }

    public void setPassword(String password) {
        this.mUserPassword = password;
        mSharedPreferences.edit().putString("password",mUserPassword).commit();
    }


}
