package ru.bardashev.pianotest.stackexchange;

import ru.bardashev.pianotest.stackexchange.models.SearchRequest;
import ru.bardashev.pianotest.stackexchange.models.SearchResult;

public interface StackExchangeService {
	SearchResult Search(SearchRequest request);
}
