package mytalk.study.biz.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ChatRoomController {

	@RequestMapping(value = "/chatroom", method = RequestMethod.GET)
	public String getchatroom() {
		System.out.println("챗룸 페이지");
	
		
		return "chatroom";
	}
	
	
	
	
}
