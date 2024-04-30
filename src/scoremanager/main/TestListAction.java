package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();// セッションを取得
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得

		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		SubjectDao subDao = new SubjectDao();// 科目Daoを初期化
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		List<Integer> entYearSet = new ArrayList<>();//入学年度のリストを初期化


		List<String> clist = cNumDao.filter(teacher.getSchool());// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<Subject> sublist = subDao.filter(teacher.getSchool());// ログインユーザーの学校コードをもとに科目の一覧を取得



		for (int i = year - 10; i < year + 10; i++) {
			entYearSet.add(i);
		}// 現在を起点に前後10年をリストに追加


		req.setAttribute("class_num_set", clist);//クラス番号のlistをセット
		req.setAttribute("subjet_set", sublist);//のlistをセット
		req.setAttribute("ent_year_set", entYearSet);
		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}


}
