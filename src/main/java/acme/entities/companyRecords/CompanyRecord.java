
package acme.entities.companyRecords;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "stars")
})
public class CompanyRecord extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				companyName;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				ceoName;

	@NotBlank
	private String				activitiesDescription;

	@NotBlank
	@URL
	private String				webSite;

	@Range(min = 0, max = 5)
	private Integer				stars;

	@NotBlank
	@Pattern(regexp = "^\\+[0-9]{0,3} ?\\(?[0-9]{0,4}\\)? ?[0-9]{6,10}$", message = "{acme.validation.companyRecord.phoneNumer.pattern}")
	private String				contactPhone;

	@NotBlank
	@Email
	private String				contactEmail;

	@NotNull
	private Boolean				isIncorporated;

}
