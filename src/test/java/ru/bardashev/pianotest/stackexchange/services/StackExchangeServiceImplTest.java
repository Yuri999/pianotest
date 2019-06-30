package ru.bardashev.pianotest.stackexchange.services;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import ru.bardashev.pianotest.stackexchange.models.SearchRequest;
import ru.bardashev.pianotest.stackexchange.models.SearchResult;

public class StackExchangeServiceImplTest {

	private final int WIRE_MOCK_PORT = 8089;
	private final String TEST_ENDPOINT = "http://localhost:" + WIRE_MOCK_PORT + "/" ;
	
	StackExchangeServiceImpl stackExchangeServiceImpl;
	
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        stackExchangeServiceImpl = new StackExchangeServiceImpl(TEST_ENDPOINT);
    }	
    
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.options().port(WIRE_MOCK_PORT), true);
    
    @Test
    public void Search() {
			stubFor(get(urlPathMatching("/"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                	.withBody("{\"items\":[{\"tags\":[\"java\",\"methods\",\"parameter-passing\",\"pass-by-reference\",\"pass-by-value\"],"
                    		+ "\"owner\":{\"reputation\":2350,\"user_id\":4315,\"user_type\":\"registered\",\"display_name\":\"user4315\","
                    		+ "\"link\":\"https://stackoverflow.com/users/4315/user4315\"},\"is_answered\":true,\"view_count\":1811515,"
                    		+ "\"protected_date\":1308938923,\"answer_count\":80,\"community_owned_date\":1347646975,\"score\":6012,"
                    		+ "\"last_activity_date\":1560981101,\"creation_date\":1220386469,\"last_edit_date\":1531308668,\"question_id\""
                    		+ ":40480,\"link\":\"https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value\","
                    		+ "\"title\":\"Is Java &quot;pass-by-reference&quot; or &quot;pass-by-value&quot;?\"},{\"tags\":[\"java\","
                    		+ "\"memory\",\"memory-leaks\"],\"owner\":{\"reputation\":12114,\"user_id\":465179,\"user_type\":\"registered\","
                    		+ "\"accept_rate\":99,\"profile_image\":\"https://www.gravatar.com/avatar/a2fa6a84c0fa7de188a4777dd2bce022"
                    		+ "?s=128&d=identicon&r=PG&f=1\",\"display_name\":\"Mat B.\",\"link\":\"https://stackoverflow.com/users/465179/mat-b\"},"
                    		+ "\"is_answered\":true,\"view_count\":594782,\"protected_date\":1366039525,\"accepted_answer_id\":6471947,"
                    		+ "\"answer_count\":54,\"community_owned_date\":1311330284,\"score\":2974,\"last_activity_date\":1553758231,"
                    		+ "\"creation_date\":1308931912,\"last_edit_date\":1548108038,\"question_id\":6470651,\"link\":"
                    		+ "\"https://stackoverflow.com/questions/6470651/creating-a-memory-leak-with-java\",\"title\":"
                    		+ "\"Creating a memory leak with Java\"}],"
                    		+ "\"has_more\":true,\"quota_max\":10000,\"quota_remaining\":9998,\"total\":431630}")));

    	
	   SearchRequest request = new SearchRequest();
       request.setIntitle("Java");
       request.setPage(1);
       request.setPageSize(2);
	   SearchResult result = stackExchangeServiceImpl.Search(request);    	
	    
	   assertEquals(2, result.getItems().size());
       assertEquals(true, result.getHasMore());
       assertEquals(9998, result.getQuotaRemaining());
       assertEquals(431630, result.getTotal());
       assertEquals("Is Java &quot;pass-by-reference&quot; or &quot;pass-by-value&quot;?", result.getItems().get(0).getTitle());
       assertEquals("user4315", result.getItems().get(0).getOwner().getDisplayName());
       assertEquals(true, result.getItems().get(0).getIsAnswered());
       assertEquals(LocalDateTime.ofEpochSecond(1220386469, 0, OffsetDateTime.now().getOffset()), result.getItems().get(0).getCreationDate());

       verify(getRequestedFor(urlEqualTo("/?site=stackoverflow&intitle=Java&sort=relevance&order=desc&filter=%219Z%28-x-Q%298&page=1&pageSize=2"))
               .withHeader("Content-Type", matching("application/json.*")));
    }    
	
}
