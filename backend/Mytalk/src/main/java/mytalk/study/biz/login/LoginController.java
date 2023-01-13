package mytalk.study.biz.login;

import java.util.HashMap;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mytalk.study.biz.user.userDAO;
import mytalk.study.biz.user.userVO;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginpage() {
		System.out.println("로그인 컨트롤러 get요청");
		
		return "loginpage";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signuppage() {
		System.out.println("signup get요청");
		
		return "signup";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/login/json", method = RequestMethod.POST)
	public String loginjson(@RequestBody HashMap<String, Object> map, userDAO userDao, HttpSession session) {
		System.out.println("로그인 post 요청");
		System.out.println(map);
		String userID= (String) map.get("user");
		String userPWD= (String) map.get("pwd");
		
		
		userVO vo = new userVO().setUser(userID)
									.setPwd(userPWD);
		
		userVO user = userDao.selectUser(vo);
		
		if(user != null) {
			System.out.println("로그인 성공");
			session.setAttribute("user", user);
			return "true";
		}
		else {
			System.out.println("로그인 실패");
			return "false";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/signup/json", method = RequestMethod.POST)
	public String signupjson(@RequestBody HashMap<String, Object> map, userDAO userDao) {
		System.out.println("회원가입 POST 요청");
		System.out.println(map);
		
		String userID = (String) map.get("ID");
		String userPWD = (String) map.get("PWD");
		
		userVO vo = new userVO().setUser(userID)
								.setPwd(userPWD);
		
		int row = userDao.insertUser(vo);
		System.out.println(row + " 행에 실행");
		
		if (row != 0) {
			return "true";
		}
		else {
			return "false";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/signup/selectID", method = RequestMethod.POST)
	public String selectIDjson(@RequestBody HashMap<String, Object> map, userDAO userDao) {
		System.out.println("selectID POST 요청");
		System.out.println(map);
		String userID = (String) map.get("userID");
		
		userVO vo = new userVO().setUser(userID);
		
		int result = userDao.selectID(vo);
		
		if (result != 1) {
			return "true";
		}
		else {
			return "false";
		}
		
		
	}
	
	@Autowired
	JavaMailSender mailSender;
	
	@ResponseBody
	@RequestMapping(value = "/signup/postEmail", method = RequestMethod.POST)
	public int toEmail(@RequestBody HashMap<String, Object> map) {
		System.out.println("toEmail");
		System.out.println(map);
		Random ran = new Random();
		int checkNum = ran.nextInt(899999) + 100000;
		
		final String setEmail = "inhyuk3800@daum.net";
		final String toMail = (String) map.get("userEmail");
		final String title = "가입인증 메일";
		final String content = "가입 인증 번호는 " + checkNum + " 입니다";
		
		
		
		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				helper.setFrom(setEmail);
				helper.setTo(toMail);
				helper.setSubject(title);
				helper.setText(content, true);
			}
		};
		try {
			mailSender.send(preparator);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return checkNum;
	}
	
}
