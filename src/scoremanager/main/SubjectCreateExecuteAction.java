package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		System.out.println("create0.1");
		HttpSession session = req.getSession();//セッション
		SubjectDao sbDao = new SubjectDao();//科目Dao
		String subject_cd = "";//科目コード
		Subject subject = null;
		String name = "";//科目名
		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得

		// ログインユーザーの学校コードをもとに科目の一覧を取得
		List<Subject> list = sbDao.filter(teacher.getSchool());
		//req.setAttribute("sblist", list);

		//リクエストパラメータ―の取得 2
		subject_cd = req.getParameter("subject_cd");//科目コード
		name = req.getParameter("name");//科目名

		System.out.println("create1");
		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で手順4~5の内容が分岐

		if (subject_cd == null) {// 科目コードが未登録だった場合
			System.out.println("create2");
		// 科目インスタンスを初期化
		subject = new Subject();
		// インスタンスに値をセット
		subject.setSubject_cd(subject_cd);
		subject.setName(name);

		// 科目情報を保存
		sbDao.save(subject);

		} else{//入力された学番がDBに保存されていた場合
			System.out.println("create3");
		errors.put("subject_cd", "科目コードが重複しています");
		}

		//レスポンス値をセット 6
		//JSPへフォワード 7

		//System.out.println("3-2");
		if(!errors.isEmpty()){
			System.out.println("create4");
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("subject_cd", subject_cd);
			req.setAttribute("subject_name", name);

			req.getRequestDispatcher("subject_create.jsp").forward(req, res);

			return;
		}
		req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
	}
}
