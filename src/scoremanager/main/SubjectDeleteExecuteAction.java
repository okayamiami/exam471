package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		SubjectDao sbDao = new SubjectDao();//科目Dao
		SchoolDao scDao = new SchoolDao();
		Subject subject = null;

		String subject_cd = req.getParameter("subject_cd");//科目コード
		String school_cd = req.getParameter("school_cd");//学校コード
		String subject_name = req.getParameter("subject_name");
		School sc = scDao.get(school_cd);
		Teacher teacher = (Teacher) session.getAttribute("user");

		//取得
		subject_cd = req.getParameter("subject_cd");//科目コード
		subject_name = req.getParameter("subject_name");//科目名
		school_cd = req.getParameter("school_cd");

		//検索
		subject = sbDao.get(subject_cd,teacher.getSchool());//科目コードと学校コードを取得
		List<Subject> list = sbDao.filter(teacher.getSchool());//科目コードから科目インスタンスを取得

		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		//Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得

		//リクエストパラメータ―の取得 2
		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で手順4~5の内容が分岐

		if (subject== null) {// 科目が未登録だった場合
			System.out.println("deleteできてない");
			return ;

		} else{//入力された科目がDBに保存されていた場合

			System.out.println("deleteExe開始");
			// 科目インスタンスを初期化
			subject = new Subject();

			subject.setSubject_cd(subject_cd);
			subject.setName(subject_name);
			sc.setCd(subject.getSchool().getCd());
			// 科目情報を削除
			sbDao.delete(subject);
			System.out.println("deleteExe完了");
		}

		//レスポンス値をセット 6
		//JSPへフォワード 7

		//System.out.println("3-2");
		if(!errors.isEmpty()){
			System.out.println("delete3");
			// リクエスト属性をセット
			req.setAttribute("errors", errors);

			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("subject_cd", subject_cd);
			req.setAttribute("subject_name", subject_name);
			req.setAttribute("school_cd", school_cd);
			req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
			return;
		}
		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);

	}

}
