package utils;

/**
 * Created by Alexey on 29.06.2015.
 */

public class Constants {
    public static final int ADMIN = 0;
    public static final int CLIENT = 1;
    public static final int DEFAULT_TARIFF_ID = 1;
    public static final int DEFAULT_PHONE_CODE = 921;
    public static final int DEFAULT_QUANTITY_OF_PHONESNUMBERS_FOR_CHOOSE = 7;
    public static final String SESSION_USER_INFO_STR = "sessionUserInfo";
    public static final String[] URLS_ALLOWED_FOR_CLIENT = {
            "contractEdit.sec",
            "ContractEditAddToCart.sec",
            "contractEditPayForCart.sec",
            "ContractEditRemoveOption.sec",
            "clientContractDashboard.sec",
            "clientContractDashboardBlockUnblock.sec"};
    public static final String[] URLS_ALLOWED_FOR_BLOCKED_CLIENT =
            {       "contractEdit.sec",
                    "clientContractDashboard.sec",
                    "clientContractDashboardBlockUnblock.sec"};

}
