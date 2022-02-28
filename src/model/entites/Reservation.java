package model.entites;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private Integer rommNumber;
    private Date checkIn;
    private Date checkOut;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(Integer rommNumber, Date checkIn, Date checkOut) {
        if (!checkOut.after(checkIn))
            throw new DomainException("Check-ou date must be affter check-in date");

        this.rommNumber = rommNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRommNumber() {
        return rommNumber;
    }

    public void setRommNumber(Integer rommNumber) {
        this.rommNumber = rommNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public long duration() {
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MICROSECONDS);
    }

    public void updateDates(Date checkIn, Date checkOut) {
        Date now = new Date();
        if (checkIn.before(now) || checkOut.before(now))
            throw new DomainException("Reservation dates for update must be future dates");

        if (!checkOut.after(checkIn))
            throw new DomainException("Check-ou date must be affter check-in date");

        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return String.format("Room" + rommNumber
                + ", check-in: "
                + sdf.format(checkIn)
                + ", check-out: "
                + sdf.format(checkOut))
                + ", "
                + duration()
                + " nights";
    }
}
