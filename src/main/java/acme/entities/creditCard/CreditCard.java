
package acme.entities.creditCard;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import acme.entities.roles.Sponsor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditCard extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@CreditCardNumber
	private String				creditCardNumber;

	@NotBlank
	@Pattern(regexp = "^(0[1-9]|1[0-2])\\/20[0-9]{2}$", message = "{acme.validation.creditCard.expirationDate.pattern}")
	private String				expirationDate;

	@NotBlank
	private String				brand;

	@NotBlank
	private String				holder;

	@Valid
	@ManyToOne(optional = true)
	private Sponsor				sponsor;

}
