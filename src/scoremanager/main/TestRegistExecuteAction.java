package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{

		//ローカル変数の宣言
		TestDao tDao = new TestDao();
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		SubjectDao subjectDao = new SubjectDao();
		List<Test> lists = new ArrayList<>();
		Map<String,String> errors = new HashMap<>();

		//リクエストパラメータの取得
		String entYearStr = req.getParameter("f1");
		String classNum = req.getParameter("f2");
		String subjectCd = req.getParameter("f3");
		String Num = req.getParameter("f4");
		List<Test> list = tDao.filter(Integer.parseInt(entYearStr),classNum, subjectDao.get(subjectCd, teacher.getSchool()),Integer.parseInt(Num),teacher.getSchool());

		for(Test test : list){
			String point = req.getParameter("point_" + test.getStudent().getStudent_no());
			if(point != "")
				test.setPoint(Integer.parseInt(point));
			list.add(test);
		}

		//DBへデータ保存
		tDao.save(lists);
		req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
	}
}
