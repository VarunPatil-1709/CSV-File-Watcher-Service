package com.student_app.dtos;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {
	@NotBlank(message = "Name is required")
	private String name;
	@Min(value = 1, message = "Age must be greater than 1")
	@Max(value = 80, message = "Age must be not greate than 80")
	private Integer age;
	private String nickname;

}
