
package acme.entities.banners;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.creditCard.CreditCard;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ComercialBanner extends Banner {

	// Serialisation identifier ------------------------------------

	private static final long serialVersionUID = 1L;

	// Attributes --------------------------------------------------

	// Relationships -----------------------------------------------

	@NotNull
	@ManyToOne(optional = false)
	@Valid
	private CreditCard creditCard;
}
