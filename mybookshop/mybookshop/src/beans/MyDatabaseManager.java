package beans;


import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 16.04.2004
 * Time: 10:15:56
 * To change this template use Options | File Templates.
 */
public class MyDatabaseManager {

    private BasicDataSourceDB ds=null;

    public MyDatabaseManager(ResourceBundle bandle_datasource){

        int MaxActive;
        int MaxIdle;
        int MinIdle;
        boolean RemoveAbandoned;
        int RemoveAbandonedTimeout;
        int MaxWait;
        String DriverClassName;
        String Url;
        String Username;
        String Password;


        MaxActive=Integer.valueOf(bandle_datasource.getString("MaxActive-prop")).intValue();
        MaxIdle=Integer.valueOf(bandle_datasource.getString("MaxIdle-prop")).intValue();
        MinIdle=Integer.valueOf(bandle_datasource.getString("MinIdle-prop")).intValue();
        RemoveAbandoned=Boolean.valueOf(bandle_datasource.getString("RemoveAbandoned-prop")).booleanValue();
        RemoveAbandonedTimeout=Integer.valueOf(bandle_datasource.getString("RemoveAbandonedTimeout-prop")).intValue();
        MaxWait=Integer.valueOf(bandle_datasource.getString("MaxWait-prop")).intValue();
        DriverClassName=bandle_datasource.getString("DriverClassName-prop");
        Url=bandle_datasource.getString("Url-prop");
        Username=bandle_datasource.getString("Username-prop");
        Password=bandle_datasource.getString("Password-prop");

        ds=new BasicDataSourceDB();


        ds.setMaxActive(MaxActive);
        ds.setMaxIdle(MaxIdle);
        ds.setMinIdle(MinIdle);
        ds.setRemoveAbandoned(RemoveAbandoned);
        ds.setRemoveAbandonedTimeout(RemoveAbandonedTimeout);
        ds.setMaxWait(MaxWait);
        ds.setDriverClassName(DriverClassName);
        ds.setUrl(Url);
        ds.setUsername(Username);
        ds.setPassword(Password);
    }
    public BasicDataSourceDB getDataSource() {
        return ds;
    }

}
