package bg.hoteltrip.service.impl;

import bg.hoteltrip.repository.ReservationRepository;
import bg.hoteltrip.service.ReservationService;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;

    }
}
