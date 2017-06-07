package dtDAO;

import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, DateTime> {

	@Override
	public DateTime convertToDatabaseColumn(LocalDateTime arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDateTime convertToEntityAttribute(DateTime arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
