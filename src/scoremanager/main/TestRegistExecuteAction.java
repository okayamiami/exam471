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
		System.out.println("test2");

		//リクエストパラメータの取得
		String entYearStr = req.getParameter("f1");
		System.out.println(entYearStr);
		String classNum = req.getParameter("f2");
		System.out.println(classNum);
		String subjectCd = req.getParameter("f3");
		System.out.println(subjectCd);
		String Num = req.getParameter("f4");
		System.out.println(Num);

		//DBからデータ取得 //error箇所↓
		List<Test> list = tDao.filter(Integer.parseInt(entYearStr),classNum, subjectDao.get(subjectCd, teacher.getSchool()),Integer.parseInt(Num),teacher.getSchool());
		System.out.println("test3");
		for(Test test : list){
			System.out.println("test31313");
			String point = req.getParameter("point_" + test.getStudent().getStudent_no());
			System.out.println(point);
			if(point != "")
				test.setPoint(Integer.parseInt(point));
			lists.add(test);
		System.out.println("test333");
		}

		//DBへデータ保存
		tDao.save(lists);
		System.out.println("test2525");
		req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
		System.out.println("test4");
	}
}
