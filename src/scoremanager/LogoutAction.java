package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action{

	@Override
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {

		HttpSession session=req.getSession();

		if(session.getAttribute("teacher")!=null) {
			session.removeAttribute("Teacher");
			req.getRequestDispatcher("logout.jsp").forward(req, res);
		}
	}
}
