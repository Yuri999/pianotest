package ru.bardashev.pianotest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.bardashev.pianotest.stackexchange.StackExchangeService;
import ru.bardashev.pianotest.stackexchange.models.SearchRequest;
import ru.bardashev.pianotest.stackexchange.models.SearchResult;

@Component
public class Test implements CommandLineRunner {

	private final StackExchangeService stackExchangeService;

	public Test(StackExchangeService stackExchangeService) {
		super();
		this.stackExchangeService = stackExchangeService;
	}

	@Override
	public void run(String... args) throws Exception {

		SearchRequest request = new SearchRequest();
		request.setIntitle("Disable Automatic Reference");

		SearchResult result = stackExchangeService.Search(request);

		System.out.println(result.getItems().size());

	}

}
