package bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Builder
@Getter
public class Amount {
	private String value;
	private String currency;
}