package DataModel;

public class AccountModel {

    private String account;
    private String description;
    private String key;

    public AccountModel(String account, String description, String key) {
        this.account = account;
        this.description = description;
        this.key = key;
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

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return getAccount() + ' ' + getDescription() + '\n' + getKey();
    }

}
