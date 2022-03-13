package beans;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 28.07.2004
 * Time: 10:39:12
 * To change this template use Options | File Templates.
 */

// These constants are mostly used by this application's actions
// -- see /WEB-INF/classes/actions.

public interface Constants {
   // this prefix provides some degree of uniqueness for the
   // constants defined below.
   static final String prefix = "beans";

   static final String
      // Keys for attributes
      LOCALE_KEY           = prefix + ".locale",
      SHOPPING_CART_KEY    = prefix + ".cart",
      USERS_KEY            = prefix + ".users",
      USERNAME_KEY         = prefix + ".username",
      PASSWORD_KEY         = prefix + ".password",
      CONFIRM_PASSWORD_KEY = prefix + ".cnfrmpwd",
      PASSWORD_HINT_KEY    = prefix + ".pwdhint",

      // Default values
      DEFAULT_I18N_BASE = "app";
}