package actions;

import actions.events.ActionListener;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 08.04.2004
 * Time: 10:10:28
 * To change this template use Options | File Templates.
 */
public interface Action extends ActionListener {
    public ActionRouter perform(HttpServlet servlet,
                                HttpServletRequest req,
                                HttpServletResponse res)
            throws ServletException;

    abstract void addActionListener(ActionListener listener);

    public boolean hasSensitiveForms();
    public boolean isSensitive();
}
