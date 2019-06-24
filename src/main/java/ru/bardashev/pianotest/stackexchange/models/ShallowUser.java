package ru.bardashev.pianotest.stackexchange.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShallowUser {
	private int reputation;

	@JsonProperty("user_id")
	private int userId;

	@JsonProperty("user_type")
	private String userType;

	@JsonProperty("profile_image")
	private String profileImage;

	@JsonProperty("display_name")
	private String displayName;

	@JsonProperty("accept_rate")
	private int acceptRate;

	private String link;
}
