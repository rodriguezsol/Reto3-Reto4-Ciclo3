/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reto3_Ciclo3.interfaces;

import Reto3_Ciclo3.modelo.Reservation;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author solesito
 */
public interface interfaceReservation extends CrudRepository<Reservation,Integer> {
    
    
}
