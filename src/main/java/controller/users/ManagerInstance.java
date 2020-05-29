package controller.users;

public final class ManagerInstance {

    private static ManagerInstance singleton;

    private final AccountManager accountMng;

    private ManagerInstance() {
        this.accountMng = new AccountOperation();
    }

    public static ManagerInstance getInstance() {
        if (singleton == null) {
            singleton = new ManagerInstance();
        }
        return singleton;
    }

    public AccountManager get() {
        return this.accountMng;
    }
}
