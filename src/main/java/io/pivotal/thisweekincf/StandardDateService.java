package io.pivotal.thisweekincf;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
final class StandardDateService implements DateService {

	@Override
    public LocalDate getDate() {
		return LocalDate.now();
	}

}
