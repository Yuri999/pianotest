package ru.bardashev.pianotest.stackexchange.services;

import ru.bardashev.pianotest.stackexchange.models.SearchRequest;
import ru.bardashev.pianotest.stackexchange.models.SearchResult;

public interface StackExchangeService {
	SearchResult Search(SearchRequest request);
}
