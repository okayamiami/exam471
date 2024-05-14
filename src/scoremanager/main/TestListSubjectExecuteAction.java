package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();// セッションを取得
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得
		TestListSubjectDao tlsubDao = new TestListSubjectDao(); //科目別のインスタンス化
		String entYearStr="";// 選択された入学年度
		String classNum = "";//選択されたクラス番号
		String subjectStr="";//選択された科目
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		SubjectDao subDao = new SubjectDao();// 科目Daoを初期化
		int entYear = 0;// 入学年度
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		List<Integer> entYearSet = new ArrayList<>();//入学年度のリストを初期化
		Subject subject = new Subject(); //科目インスタンス


		entYearStr = req.getParameter("f1");//入学年度取得
		classNum = req.getParameter("f2");//クラス取得
		subjectStr = req.getParameter("f3");//科目コード取得

		List<String> clist = cNumDao.filter(teacher.getSchool());// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<Subject> sublist = subDao.filter(teacher.getSchool());// ログインユーザーの学校コードをもとに科目の一覧を取得

		//取得した科目コードと学校情報からsubject型のsubject作成
		subject=subDao.get(subjectStr,teacher.getSchool());
		//subjectが空じゃないなら
		if (subject != null){
			//科目名を変数に追加
			String subjectName = subject.getName();
			//リクエストにセット
			req.setAttribute("subjectName", subjectName);
			req.setAttribute("f3", subjectName);
			}


		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);

		}

		// 現在を起点に前後10年をリストに追加
		for (int i = year - 10; i < year + 10; i++) {
			entYearSet.add(i);
		}

		// 取得したものをもとに科目別の一覧を取得
		List<TestListSubject> list = tlsubDao.filter(entYear,classNum,subject,teacher.getSchool());

		//リクエストにセット
		req.setAttribute("tlsub_set", list);//学生別のlistをセット
		req.setAttribute("f1", entYear);
		req.setAttribute("f2", classNum);
		req.setAttribute("class_num_set", clist);//クラス番号のlistをセット
		req.setAttribute("subject_set", sublist);//科目のlistをセット
		req.setAttribute("ent_year_set", entYearSet);

		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}
}


