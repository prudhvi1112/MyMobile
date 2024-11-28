package com.mymobile.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class UserData {
	@Id
	@Size(min = 3, max = 35, message = "Username must be between 3 and 50 characters")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$", message = "User ID must be alphanumeric and contain at least one letter and one digit.")
	private String userId;

	@NotNull(message = "Username cannot be null")
	private String userName;

	@NotNull(message = "Password cannot be null")
	// @Size(min = 8, max = 15, message = "Password must be between 8 and 15
	// characters")
//	    @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#%$&])[a-zA-Z0-9@#%$&]+$", message = "User ID must be alphanumeric and contain at least one special character (@, #, %, $, &) and one digit.")
	private String userPassword;

	@NotNull(message = "Confirm Password cannot be null")
	// @Size(min = 8, max = 15, message = "Password must be between 8 and 15
	// characters")
	// @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#%$&])[a-zA-Z0-9@#%$&]+$", message =
	// "User ID must be alphanumeric and contain at least one special character (@,
	// #, %, $, &) and one digit.")
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

	@NotNull(message = "Register Date cannot be null")
	private LocalDateTime userRegsiterDate;

	private LocalDateTime userLastLoginIn;

	@NotNull(message = "PAN Number cannot be null")
	@Pattern(regexp = "^[A-Za-z0-9]{10}$", message = "Invalid input. The value must be 10 alphanumeric characters.")
	private String userPanNumber;

	@NotNull(message = "GST Number cannot be null")
	private String userGstNumber;

	@OneToMany(mappedBy = "userData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Product> products;

//	    @OneToOne(mappedBy = "userData", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	    @JsonIgnore
//	    private Cart cart; 

}
