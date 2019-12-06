
package acme.entities.banners;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NonComercialBanner extends Banner {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@URL
	private String				jingle;
}
