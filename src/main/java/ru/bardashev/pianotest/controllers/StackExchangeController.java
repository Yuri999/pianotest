package ru.bardashev.pianotest.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.bardashev.pianotest.stackexchange.models.SearchRequest;
import ru.bardashev.pianotest.stackexchange.models.SearchResult;
import ru.bardashev.pianotest.stackexchange.services.StackExchangeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("stackexchange")
public class StackExchangeController {

	private final StackExchangeService stackExchangeService;

	public StackExchangeController(StackExchangeService stackExchangeService) {
		super();
		this.stackExchangeService = stackExchangeService;
	}

	@RequestMapping("search")
	public SearchResult search(
			String query, 
			@RequestParam(defaultValue = "1") int page, 
			@RequestParam(defaultValue = "10") int pageSize) 
	{
		SearchRequest request = new SearchRequest();
		request.setIntitle(query);
		request.setPage(page);
		request.setPageSize(pageSize);

		return stackExchangeService.Search(request);
	}

}
