package com.mymobile.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class UserData
{
	    @Id
	    @Size(min = 3, max = 35, message = "Username must be between 3 and 50 characters")
	    @Pattern(regexp = "^[a-zA-Z0-9@#%$&]+$", message = "User ID must be alphanumeric or contain @, #, %, $, &")
	    private String userId;

	    @NotNull(message = "Username cannot be null")
	    private String userName;

	    @NotNull(message = "Password cannot be null")
	    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
	    @Pattern(regexp = "^[a-zA-Z0-9@#%$&]+$", message = "User ID must be alphanumeric or contain @, #, %, $, &")
	    private String userPassword;

	    @NotNull(message = "Confirm Password cannot be null")
	    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
	    @Pattern(regexp = "^[a-zA-Z0-9@#%$&]+$", message = "User ID must be alphanumeric or contain @, #, %, $, &")
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
	    private Long userNumber;

	    @NotNull(message = "Pincode cannot be null")
	    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
	    private Integer userPincode;

	    @NotNull(message = "Register Date cannot be null")
	    private LocalDate userRegsiterDate;

	    private LocalDate userLastLoginIn;

	    @NotNull(message = "PAN Number cannot be null")
	    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN number format")
	    private String userPanNumber;

	    @NotNull(message = "GST Number cannot be null")
	    @Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[A-Z0-9]{1}[Z]{1}[A-Z0-9]{1}$", message = "Invalid GST number format")
	    private String userGstNumber;

	    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Product> products;




}
