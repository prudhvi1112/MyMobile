package com.mymobile.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.mymobile.entity.Product;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserDataDto 
{
	@Id
    @Size(min = 3, max = 35, message = "Username must be between 3 and 50 characters")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$", message = "User ID must be alphanumeric and contain at least one letter and one digit.")
    private String userId;

    @NotNull(message = "Username cannot be null")
    private String userName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#%$&])[a-zA-Z0-9@#%$&]+$", message = "User ID must be alphanumeric and contain at least one special character (@, #, %, $, &) and one digit.")
    private String userPassword;


    @NotNull(message = "Confirm Password cannot be null")
    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#%$&])[a-zA-Z0-9@#%$&]+$", message = "User ID must be alphanumeric and contain at least one special character (@, #, %, $, &) and one digit.")
    private String userConfirmPassword;

    @NotNull(message = "Address cannot be null")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String userAddress;

    @NotNull(message = "Role cannot be null")
    private String userRole;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String userEmail;

    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String userNumber;

    @NotNull(message = "Pincode cannot be null")
    private Integer userPincode;

   

    @NotNull(message = "PAN Number cannot be null")
    @Size(max = 10, message = "Password must be between 8 and 15 characters")
    private String userPanNumber;

    @NotNull(message = "GST Number cannot be null")
    private String userGstNumber;




}
