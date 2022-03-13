package actions;

import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 08.04.2004
 * Time: 10:18:01
 * To change this template use Options | File Templates.
 */
public class ActionFactory {
    private Hashtable actions = new Hashtable();

    // This method is called by the action servlet

    public Action getAction(String classname,
                            ClassLoader loader)
            throws ClassNotFoundException, IllegalAccessException,
            InstantiationException {
        Action action = (Action)actions.get(classname);

        if(action == null) {
            Class klass = loader.loadClass(classname);
            action = (Action)klass.newInstance();
            actions.put(classname, action);
        }
        return action;
    }
}
