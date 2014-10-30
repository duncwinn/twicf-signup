package io.pivotal.thisweekincf;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class DateService {

	public LocalDate getDate() {
		return LocalDate.now();
	}

}
