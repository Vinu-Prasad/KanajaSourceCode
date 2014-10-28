package com.kanaja.web.controller;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kanaja.document.dto.DocumentScore;
import com.kanaja.search.helper.AdvancedSearchCriteria;
import com.kanaja.search.service.SearchService;


@Controller
public class SearchController {
	@Resource(name="searchService")
	public SearchService searchService ;
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public @ResponseBody List<DocumentScore> search(@RequestParam(value="query",required=false) String query,@RequestParam(value="in",required=false)String searchIn,@RequestParam(value="author",required=false)
	String author, @RequestParam(value="type",required=false) String type, @RequestParam(value="category",required=false) String category, @RequestParam(value="fromDate",required=false) String fromDate, @RequestParam(value="toDate",required=false) String toDate) throws ParseException{
		AdvancedSearchCriteria criteria = new AdvancedSearchCriteria(author,type,category,fromDate,toDate) ;
		List<DocumentScore> documentScoreList = new ArrayList<DocumentScore>();
		if(query!=null && searchIn!=null)
			documentScoreList = searchService.search(query, criteria,searchIn);
		else
			documentScoreList = searchService.search(criteria);
		return documentScoreList ;
		
	}
	
	@ExceptionHandler(ParseException.class)
    public ModelAndView exception(ParseException e) {
        ModelAndView ret = new ModelAndView("exception");
        ret.addObject("exception", e.getMessage() + " The required format is dd-mm-yyyy");
        return ret;
    }   

}
