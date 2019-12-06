
package acme.entities.banners;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ComercialBanner extends Banner {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@CreditCardNumber
	private String				creditCardNumber;

	@NotBlank
	@Pattern(regexp = "^(0[1-9]|1[0-2])\\/20[0-9]{2}$", message = "{acme.validation.comercialBanner.expirationDate.pattern}")
	private String				expirationDate;

	@NotBlank
	private String				holder;

	@NotBlank
	private String				brand;
}
