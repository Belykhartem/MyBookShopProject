
import java.util.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;

import actions.*;
import actions.events.ActionEvent;
import beans.MyDatabaseManager;
import beans.BasicDataSourceDB;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 08.04.2004
 * Time: 14:18:16
 * To change this template use Options | File Templates.
 */

public class ActionServlet extends HttpServlet {

    private ActionFactory factory = new ActionFactory();
    private StateManager stateManager = new StateManager();

    private MyDatabaseManager dbm = null;
    private Connection conn_catalogcnt =null;
    private Statement stmt_catalogcnt;
    private ResultSet rset_catalogcnt;
    private Properties props_catalog_maping;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

//----------------------------------------------------------------------------
        ResourceBundle bundle_menu = null;
        try {
            bundle_menu = ResourceBundle.getBundle(
                    config.getInitParameter("menu-mappings"));
        } catch (MissingResourceException e) {
            throw new ServletException(e);
        }
        getServletContext().setAttribute("menu-mappings", bundle_menu);

//----------------------------------------------------------------------------
        ResourceBundle bundle_application = null;
        try {
            bundle_application = ResourceBundle.getBundle(
                    config.getInitParameter("application-mappings"));
        } catch (MissingResourceException e) {
            throw new ServletException(e);
        }
        getServletContext().setAttribute("application-mappings", bundle_application);

//----------------------------------------------------------------------------
        ResourceBundle bundle_actions = null;
        try {
            bundle_actions = ResourceBundle.getBundle(
                    config.getInitParameter("action-mappings"));
        } catch (MissingResourceException e) {
            throw new ServletException(e);
        }
        getServletContext().setAttribute("action-mappings", bundle_actions);

//----------------------------------------------------------------------------
        ResourceBundle bundle_datasource = null;
        try {
            bundle_datasource = ResourceBundle.getBundle(
                    config.getInitParameter("datasource-mappings"));
        } catch (MissingResourceException e) {
            throw new ServletException(e);
        }

        dbm = new MyDatabaseManager(bundle_datasource);
        BasicDataSourceDB ds = dbm.getDataSource();
        getServletContext().setAttribute("ds", ds);
        getServletContext().setAttribute("dbm", dbm);
//----------------------------------------------------------------------------
        props_catalog_maping=new Properties();
        conn_catalogcnt=null;
        stmt_catalogcnt=null;
        rset_catalogcnt=null;
        try {
            conn_catalogcnt=ds.getConnection();
            stmt_catalogcnt=conn_catalogcnt.createStatement();
            stmt_catalogcnt.executeQuery("select * from catalogcnt");
            rset_catalogcnt=stmt_catalogcnt.getResultSet();
            while (rset_catalogcnt.next()) {
                props_catalog_maping.setProperty(rset_catalogcnt.getString("razdel"),rset_catalogcnt.getString("caption"));
            }

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
        try {
            rset_catalogcnt.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
        try {
            stmt_catalogcnt.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
        try {
            conn_catalogcnt.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }


        getServletContext().setAttribute("props-catalog-mappings", props_catalog_maping);

    }




    public void service(HttpServletRequest req,
                        HttpServletResponse res)
            throws ServletException {
        Action action = getAction(req);

        action.beforeAction(new ActionEvent(action,
                ActionEvent.BEFORE_ACTION,
                req, res));

        ActionRouter router = performAction(action, req, res);

        action.afterAction(new ActionEvent(action,
                ActionEvent.AFTER_ACTION,
                req, res));

        // routers could fire events in a manner similar to actions
        routeAction(router, req, res);
    }

    protected Action getAction(HttpServletRequest req)
            throws ServletException {
        Action action = null;
        try {
            action = factory.getAction(getActionClass(req),
                    getClass().getClassLoader());
            //action.addActionListener(stateManager);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        return action;
    }

    protected ActionRouter performAction(Action action,
                                         HttpServletRequest req,
                                         HttpServletResponse res)
            throws ServletException {
        ActionRouter router = null;
        try {
            router = action.perform(this, req, res);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        return router;
    }

    protected void routeAction(ActionRouter router,
                               HttpServletRequest req,
                               HttpServletResponse res)
            throws ServletException {
        try {
            router.route(this, req, res);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private String getClassname(HttpServletRequest req) {
        String path = req.getServletPath();
        int slash = path.lastIndexOf("/"),
                period = path.lastIndexOf(".");

        if (period > 0 && period > slash)
            path = path.substring(slash + 1, period);

        return path;
    }

    private String getActionClass(HttpServletRequest req) {
        ResourceBundle bundle = (ResourceBundle) getServletContext().
                getAttribute("action-mappings");
        return (String) bundle.getObject(getActionKey(req));
    }

    private String getActionKey(HttpServletRequest req) {
        String path = req.getServletPath();
        int slash = path.lastIndexOf("/"),
                period = path.lastIndexOf(".");

        if (period > 0 && period > slash)
            path = path.substring(slash + 1, period);

        return path;
    }
}

