package com.travel.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserForm implements Serializable{

	@NotNull
    @Size(min = 1, max = 30)
    private String username;
    @NotNull
    @Size(min = 1, max = 100)
    private String password;
}
