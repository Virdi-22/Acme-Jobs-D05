
package acme.entities.banners;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

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
}
