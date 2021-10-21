
package Reto3_Ciclo3.sevicio;

import Reto3_Ciclo3.repositorio.RoomRepositorio;
import Reto3_Ciclo3.modelo.Room;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @author solecito
 */
@Service
public class serviciosRoom {
    @Autowired
    private RoomRepositorio metodosCrud;
    
    public List<Room> getAll(){
         return metodosCrud.getAll();
    }
    
    public Optional<Room> getRooms(int idRoom){
        return metodosCrud.getRoom(idRoom);
    }
    
    
    public Room save(Room rooms){
        if(rooms.getId()==null){
            return metodosCrud.save(rooms);
        }else{
            Optional<Room> evt=metodosCrud.getRoom(rooms.getId());
            if(evt.isEmpty()){
            return metodosCrud.save(rooms);
            }else{
                return rooms;
            }
        
        
        }
    
    }
   public Room update(Room rooms){
        if(rooms.getId()!=null){
            Optional<Room> evento=metodosCrud.getRoom(rooms.getId());
            if(!evento.isEmpty()){
                if(rooms.getName()!=null){
                    evento.get().setName(rooms.getName());
                }
                if(rooms.getHotel()!=null){
                    evento.get().setHotel(rooms.getHotel());
                }
                if(rooms.getStars()!=null){
                    evento.get().setStars(rooms.getStars());
                }
                if(rooms.getDescription()!=null){
                    evento.get().setDescription(rooms.getDescription());
                }
                if(rooms.getCategory()!=null){
                    evento.get().setCategory(rooms.getCategory());
                }
                metodosCrud.save(evento.get());
                return evento.get();
            }else{
                return rooms;
            }
        }else{
            return rooms;
        }
    }


    public boolean deleteRoom(int idRoom) {
        Boolean delect = getRooms(idRoom).map(rooms -> {
            metodosCrud.delete(rooms);
            return true;
        }).orElse(false);
        return delect;
    }
 
}
