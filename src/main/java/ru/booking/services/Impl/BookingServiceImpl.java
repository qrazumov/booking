package ru.booking.services.Impl;

import javassist.NotFoundException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import ru.booking.exception.BookingException;
import ru.booking.models.Booking;
import ru.booking.models.User;
import ru.booking.payload.request.BookingRequest;
import ru.booking.repository.BookingRepository;
import ru.booking.repository.UserRepository;
import ru.booking.services.BookingService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class BookingServiceImpl implements BookingService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setSessionFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    @SuppressWarnings("unchecked")
    public List<Booking> getAll() {
        List<Booking> result = null;
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Booking").getResultList();

    }

    @Override
    public Booking create(BookingRequest bookingRequest) throws BookingException, NotFoundException {
        Booking bookingSave = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userRepository.findByUsername(userName).
                orElseThrow(() -> new NotFoundException("The user with name: " + userName + " is not found"));

        if (hasDateTimeIntersection(bookingRequest.getEventStart(), bookingRequest.getEventEnd())) {
            if (hasMinMaxDuration(bookingRequest.getEventStart(), bookingRequest.getEventEnd())) {
                Booking booking = new Booking(
                        bookingRequest.getTitle(),
                        bookingRequest.getEventStart(),
                        bookingRequest.getEventEnd(),
                        user
                );
                bookingSave = bookingRepository.save(booking);
            } else {
                throw new BookingException("Минимальное время бронирования 30 мин, максимальное 24 часа");
            }
        } else {
            throw new BookingException("На данный промежуток времени уже забронировано событие");
        }

        return bookingSave;
    }

    private boolean hasDateTimeIntersection(Date eventStart, Date eventEnd) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "FROM Booking WHERE eventStart BETWEEN :eventStart AND :eventEnd OR eventEnd BETWEEN :eventStart AND :eventEnd";
        Query q = session.createQuery(sql);
        q.setParameter("eventStart", eventStart);
        q.setParameter("eventEnd", eventEnd);
        return q.getResultList().isEmpty();
    }

    private boolean hasMinMaxDuration(Date eventStart, Date eventEnd) {
        long diff = eventEnd.getTime() - eventStart.getTime();
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        if (diffInMinutes < 30 || diffInMinutes > 1440)
            return false;
        return true;
    }

}
