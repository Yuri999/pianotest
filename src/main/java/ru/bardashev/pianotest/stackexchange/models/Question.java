package ru.bardashev.pianotest.stackexchange.models;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

	private ArrayList<String> tags;

	private ShallowUser owner;

	@JsonProperty("is_answered")
	private Boolean isAnswered;

	@JsonProperty("view_count")
	private int viewCount;

	@JsonProperty("answer_count")
	private int answerCount;

	private int score;

	@JsonProperty("last_activity_date")
	private Date lastActivityDate;

	@JsonProperty("creation_date")
	private Date creationDate;

	@JsonProperty("last_edit_date")
	private Date lastEditDate;

	@JsonProperty("question_id")
	private int questionId;

	@JsonProperty("protected_date")
	private Date protectedDate;

	@JsonProperty("accepted_answer_id")
	private int acceptedAnswerId;

	private String link;

	private String title;
}
