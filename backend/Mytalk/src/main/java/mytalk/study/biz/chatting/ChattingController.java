package mytalk.study.biz.chatting;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mytalk.study.biz.user.userVO;

@Controller
public class ChattingController {
	
	@RequestMapping(value = "/chatting", method = RequestMethod.GET)
	public String getChattingpage(HttpSession session, @RequestParam(value="chatnum") String chatNum) {
//	public String getChattingpage(HttpSession session, HttpServletRequest request) {
			
		System.out.println("채팅페이지");
//		String chatNum = request.getParameter("chatnum");
		userVO user = (userVO) session.getAttribute("user");
		System.out.println(user);
		System.out.println(chatNum);
		return "chattingpage";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/chatting/json", method = RequestMethod.POST)
	public chatVO chattingJson(@RequestBody HashMap<String, Object> map, HttpSession session, chatDAO chatDao) {
		System.out.println(map);
		String chatting = (String) map.get("chat");
		String sendTime = (String) map.get("sendTime");
//		userVO user = (userVO) session.getAttribute("user");
		userVO user = new userVO().setUser("유자");
		
		chatVO chat = new chatVO()
						.setChat(chatting)
						.setUser(user)
						.setType("text");
		
		System.out.println(chat.toString());
		
		int row = chatDao.InsertChatting(chat);
		
		if(row == 1) {
			System.out.println("저장 완료");
			return chat;
		}
		else {
			System.out.println("실패");
			chat = null;
			return chat;
		}
		
	}
}
