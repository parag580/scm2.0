package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {
     @NotBlank(message="Username is required")
     @Size(min=3,message="Min 3 Characters is required")
     private String name;
     @NotBlank(message="Email is required")
     @Email(message="Invalid Email Address")
     private String email;
     @NotBlank(message="Password is required")
     @Size(min=6,message="Min 6 Characters is required")
     private String password;
     @NotBlank(message="About is required")
     private String about;
     @NotBlank(message="Phone number is required")
     @Pattern(regexp = "^[0-9]{10}$",
     message = "phone number must be of 10 to 12 length with no special characters")
     
     private String phoneNumber;
}
