package com.load.balancer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MovieFilePath {

	@JsonProperty(value = "imgUrl")
	private String imgUrl;
	private String videoPath;


}
