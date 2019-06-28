package ru.bardashev.pianotest.stackexchange.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import ru.bardashev.pianotest.serializers.JsonUnixEpochDeserializer;

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
	@JsonDeserialize(using = JsonUnixEpochDeserializer.class) 
	private LocalDateTime lastActivityDate;

	@JsonProperty("creation_date")
	@JsonDeserialize(using = JsonUnixEpochDeserializer.class)
	private LocalDateTime creationDate;

	@JsonProperty("last_edit_date")
	@JsonDeserialize(using = JsonUnixEpochDeserializer.class)
	private LocalDateTime lastEditDate;

	@JsonProperty("question_id")
	private int questionId;

	@JsonProperty("protected_date")
	@JsonDeserialize(using = JsonUnixEpochDeserializer.class)
	private LocalDateTime protectedDate;

	@JsonProperty("accepted_answer_id")
	private int acceptedAnswerId;

	private String link;

	private String title;
}
