package ru.bardashev.pianotest.stackexchange.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
	private ArrayList<Question> items;

	@JsonProperty("has_more")
	private Boolean hasMore;

	@JsonProperty("quota_max")
	private int quotaMax;

	@JsonProperty("quota_remaining")
	private int quotaRemaining;
}
