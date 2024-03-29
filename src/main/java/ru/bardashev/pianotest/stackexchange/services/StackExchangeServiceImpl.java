package ru.bardashev.pianotest.stackexchange.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.message.GZipEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import ru.bardashev.pianotest.stackexchange.models.SearchRequest;
import ru.bardashev.pianotest.stackexchange.models.SearchResult;

@Service
public class StackExchangeServiceImpl implements StackExchangeService {

	public final static String BASE_URL = "http://api.stackexchange.com/2.2/search";
	
	private final String SEARCH_SITE = "stackoverflow";
	private final String DEFAULT_SORT = "relevance";
	private final String DEFAULT_SORT_DIR = "desc";
	private final String FILTER_OPTIONS = "!9Z(-x-Q)8";
	
	private final String endpoint;
	
	public StackExchangeServiceImpl() {
		endpoint = BASE_URL;
	}
	
	public StackExchangeServiceImpl(String endpoint) {
		this.endpoint = endpoint;
	}
	
	@Override
	public SearchResult Search(SearchRequest request) {
		Client client = ClientBuilder.newClient(
				new ClientConfig().register(JacksonJsonProvider.class).register(GZipEncoder.class));

		WebTarget webTarget = MapParameters(client.target(endpoint), request);

		return webTarget
				.request(MediaType.APPLICATION_JSON)
				.header("Content-Type", "application/json;charset=UTF-8")
				.get(SearchResult.class);
	}

	protected WebTarget MapParameters(WebTarget webTarget, SearchRequest request) {
		return webTarget.queryParam("site", SEARCH_SITE)
				.queryParam("intitle", request.getIntitle())
				.queryParam("sort", DEFAULT_SORT)
				.queryParam("order", DEFAULT_SORT_DIR)
				.queryParam("filter", FILTER_OPTIONS)
				.queryParam("page", request.getPage())
				.queryParam("pageSize", request.getPageSize());
	}

}
