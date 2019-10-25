package fr.sylvaindce.DataModel;

public class AccountModel {

    private String account;
    private String description;
    private String private_key;
    private String otp = "--- ---";

    public AccountModel(String account, String description, String private_key) {
        this.setAccount(account);
        this.setDescription(description);
        this.setPrivateKey(private_key);
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrivateKey() {
        return this.private_key;
    }

    public void setPrivateKey(String key) {
        key = key.replaceAll("\\s", "");
        key = key.toUpperCase();
        this.private_key = key;
    }

    public String getOtp() {
        return this.otp;
    }

    public void setOtp(String otp) {
        if (!otp.matches("^\\s*$")) {
            otp = otp.replaceAll("...", "$0 ");
        }
        this.otp = otp;
    }

    @Override
    public String toString() {
        return getAccount() + ' ' + getDescription() + '\n' + getPrivateKey();
    }

}
