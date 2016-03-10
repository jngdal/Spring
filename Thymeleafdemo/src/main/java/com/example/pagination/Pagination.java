package com.example.pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.AwesomeThing;
import com.example.repositories.AwesomeThingRepository;
@Controller
public class Pagination {

	@Autowired
    private AwesomeThingRepository awesomeThingsService;

    @RequestMapping(value="/awesomeThings")
    public String bookingsRedirect(){
    	
        return "redirect:/awesomeThings/page/1";
    }

    @RequestMapping(value = "/awesomeThings/page/{pageNumber}", method = RequestMethod.GET)
    public String awesomeThings(@PathVariable Integer pageNumber,Model uiModel){

    	
        PageRequest newPgb =
                new PageRequest(pageNumber-1,20);

        Page<AwesomeThing> currentResults= awesomeThingsService.findAll(newPgb);
        currentResults.forEach(x->{
        	System.out.println(x.getDescription());
        });
        uiModel.addAttribute("currentResults", currentResults);
         
        //Pagination variables
       
        int current = currentResults.getNumber() + 1;
        
        int begin = Math.max(1, current - 5);
        
        int end = Math.min(begin + 10, currentResults.getTotalPages()); // how many pages to display in the pagination bar
       
        uiModel.addAttribute("beginIndex", begin);
        uiModel.addAttribute("endIndex", end);
        uiModel.addAttribute("currentIndex", current);

        return "pagination/pagination";
    }
}
