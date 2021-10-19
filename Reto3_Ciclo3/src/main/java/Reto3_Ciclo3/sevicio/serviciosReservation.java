/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reto3_Ciclo3.sevicio;

import Reto3_Ciclo3.modelo.Reservation;
import Reto3_Ciclo3.repositorio.ReservationRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author solesito
 */
@Service
public class serviciosReservation {

    @Autowired
    private ReservationRepositorio metodosCrud;

    public List<Reservation> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<Reservation> getReservation(int idReservation) {
        return metodosCrud.getReservation(idReservation);
    }

    public Reservation save(Reservation reservations) {
        if (reservations.getIdReservation() == null) {
            return metodosCrud.save(reservations);
        } else {
            Optional<Reservation> evt = metodosCrud.getReservation(reservations.getIdReservation());
            if (evt.isEmpty()) {
                return metodosCrud.save(reservations);
            } else {
                return reservations;
            }

        }

    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> evento = metodosCrud.getReservation(reservation.getIdReservation());
            if (!evento.isEmpty()) {

                if (reservation.getStartDate() != null) {
                    evento.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    evento.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    evento.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(evento.get());
                return evento.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }

    public boolean deleteReservation(int idReservation) {
        Boolean delect = getReservation(idReservation).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return delect;
    }
}
