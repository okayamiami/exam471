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

		System.out.println("クリエイト２");
		HttpSession session = req.getSession();//セッション
		SubjectDao sbDao = new SubjectDao();//科目Dao
		String subject_cd = "";//科目コード
		Subject subject = null;
		String subject_name = "";//科目名
		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得


		//リクエストパラメータ―の取得 2
		subject_cd = req.getParameter("subject_cd");//科目コード
		subject_name = req.getParameter("subject_name");//科目名


		// ログインユーザーの学校コードをもとに科目の一覧を取得
		//検索
		List<Subject> list = sbDao.filter(teacher.getSchool());
		subject = sbDao.get(subject_cd,teacher.getSchool());
		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で手順4~5の内容が分岐

		System.out.println("クリエイト3");
		if (subject == null) {// 科目コードが未登録だった場合
			System.out.println("クリエイト開始");

		// 科目インスタンスを初期化
		subject = new Subject();
		// インスタンスに値をセット
		subject.setSubject_cd(subject_cd);
		subject.setName(subject_name);
		subject.setSchool(((Teacher)session.getAttribute("user")).getSchool());
		// 科目情報を保存
		sbDao.save(subject);
		System.out.println("クリエイト完了");

		} else{//入力された学番がDBに保存されていた場合

		System.out.println("クリエイトできてない");
		errors.put("list", "科目コードが重複しています");
		}

		//レスポンス値をセット 6
		//JSPへフォワード 7

		//System.out.println("3-2");
		if(!errors.isEmpty()){

			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("subject_cd", subject_cd);
			req.setAttribute("subject_name", subject_name);
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			return;
		}
		req.setAttribute("sblist", list);
		req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
		System.out.println("クリエイトend");
	}
}

