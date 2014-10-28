package com.kanaja.web.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kanaja.category.model.Category;
import com.kanaja.category.model.CategoryImpl;
import com.kanaja.document.model.Document;
import com.kanaja.index.service.IndexService;

@Controller
public class IndexController {
	@Resource(name="indexService")
	IndexService indexService ;
	
	@RequestMapping(value="/index" ,method=RequestMethod.POST)
	public @ResponseBody String indexDocument(@RequestParam("name")String name,@RequestParam("author")String author,@RequestParam("content")String content,
			@RequestParam("url")String url,@RequestParam("categories") String categories,@RequestParam("date") String Date,@RequestParam("type") Boolean type){
		Document document = indexService.getDocumentByURL(url);
		if(document!=null){
			//perform reindexing
			indexService.removeDocument(document, author);
			
		}
		String [] categoryList ;
		if(categories!=null){
			categoryList = categories.split(",");
		}
		else
			categoryList = new String[0];
		indexService.IndexDocument(name, content, url, new Date(), type, author, categoryList);
		/*String _name = "darren young" ;
		String _content = "hi this is a மலர்கள் sample text for மலர்" ;
		String _author = "forrester" ;
		indexService.IndexDocument(_name, _content,url, new Date(), true, _author,categoryList);
		String _name1 = "titus o neil";
		String _content1 = "hello how are you மலர்கள் doing apple maple" ;
		String _author1 = "gartner" ;
		indexService.IndexDocument(_name1, _content1,url,new Date(), true, _author1,categoryList);
		String _name2 = "colloseum";
		String _content2 = "index how are you மலர் doing apple maple" ;
		String _author2 = "chipmunk" ;
		indexService.IndexDocument(_name2, _content2,url,new Date(), true, _author2,categoryList);*/
		
		return "success" ;
	}
	
}
