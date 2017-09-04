package pneumax.websales.manager;

/**
 * Created by masterung on 9/4/2017 AD.
 */

public class MyConstant {

    private String[] columnEmployeeyStrings = new String[]{
            "STFcode",
            "STFtitle",
            "DPcode",
            "DPname",
            "PSTdes_Eng",
            "PSTCode",
            "SACode",
            "STFfname",
            "STFlname",
            "STFfullname",
            "BRcode1",
            "BRdesc_T",
            "STFstart"};

    //    About URl
    private String urlGetSalesNameWhere = "http://58.181.171.23/webservice/Service.asmx/getEmployeeName";

    public String getUrlGetSalesNameWhere() {
        return urlGetSalesNameWhere;
    }

    public String[] getColumnEmployeeyStrings() {
        return columnEmployeeyStrings;
    }
}   // Main Class
