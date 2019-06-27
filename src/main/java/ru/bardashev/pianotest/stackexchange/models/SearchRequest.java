package ru.bardashev.pianotest.stackexchange.models;

import lombok.Data;

@Data
public class SearchRequest {

	private String intitle;

	private int page;

	private int pageSize;

}
