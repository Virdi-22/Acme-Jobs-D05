
package acme.entities.banners;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.entities.creditCard.CreditCard;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ComercialBanner extends Banner {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				holder;

	@NotBlank
	private String				brand;

	@OneToOne(optional = false)
	@Valid
	private CreditCard			credirCard;
}
