package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class LoginAction extends Action{

	@Override
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {

		req.getRequestDispatcher("login.jsp").forward(req, res);

		req.getRequestDispatcher("login-error.jsp").forward(req, res);
	}
}
