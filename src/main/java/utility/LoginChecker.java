package utility;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginChecker {

    public static boolean notLogin(HttpSession session) {
        if (session.getAttribute("User") == null) {
            return true;
        } else {
            return false;
        }

    }

    public static String getErrorpage() {
        return "./sessionerror.jsp";

    }
}
