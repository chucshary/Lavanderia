package sharychuc.lavanderia.DBSQLite;

/**
 * Created by Shary on 26/01/2016.
 */
public class Tables {
    private String[] Laundry = {"Laundry", "Id", "Name", "Latitude", "Longitude", "Country", "State", "City", "Address", "IdOwner"};
    private String[] Account = {"Account", "Id", "Email", "Password", "CreateDate"};
    private String[] User = {"User", "Id", "AccountId", "Name", "LastName", "SecondLastName"};

    public String[] getLaundry() {
        return Laundry;
    }

    public void setLaundry(String[] laundry) {
        Laundry = laundry;
    }

    public String[] getAccount() {
        return Account;
    }

    public void setAccount(String[] account) {
        Account = account;
    }

    public String[] getUser() {
        return User;
    }

    public void setUser(String[] user) {
        User = user;
    }
}
