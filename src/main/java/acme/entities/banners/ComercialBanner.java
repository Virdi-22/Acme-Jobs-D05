
package acme.entities.banners;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.entities.creditCard.CreditCard;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ComercialBanner extends Banner {

	private static final long	serialVersionUID	= 1L;

	@ManyToOne(optional = false)
	@Valid
	private CreditCard			creditCard;
}
