package com.nhnent.guestbook;

import java.text.DateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nhnent.guestbook.BoardService;
import com.nhnent.guestbook.Guest;




/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BoardServiceImpl {
	
	private BoardService boardService;
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		
		List<Guest> list = boardService.getList();  // new BoardServiceImpl().getList();
		
		model.addAttribute("list",list);
		return "list";
	}
	

	
	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String write(Guest item,Model model) {
		
		
		
		
		model.addAttribute("name", item.getName());
		model.addAttribute("email", item.getEmail());
		model.addAttribute("pwd", item.getPwd());
		model.addAttribute("contents", item.getContents());
		
		//boardService.insert(item);
		
		System.out.println(item.getName());
		
		//new BoardServiceImpl().insert(item);
		return "list";
		
	   // return "redirect:/bbs/list?boardCd=" + article.getBoardCd();
	} 

	
}
