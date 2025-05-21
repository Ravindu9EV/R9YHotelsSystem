package edu.icet.dto;

import edu.icet.util.BookingType;
import lombok.*;

import java.util.Objects;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Long id;
    private String roomNumber;
    private boolean is_booked;
    private Integer maxOccupancy;
    private double price;
//    private Long user_id;

    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj==null||getClass()!=obj.getClass()) return false;
        RoomDTO roomDTO=(RoomDTO) obj;
        return Objects.equals(id,roomDTO.getId())&& Objects.equals(roomNumber,roomDTO.getRoomNumber())&&is_booked== roomDTO.is_booked
                && Objects.equals(maxOccupancy,roomDTO.maxOccupancy)
                && Objects.equals(price,roomDTO.getPrice());

    }

    @Override
    public int hashCode(){
        return Objects.hash(id,roomNumber,is_booked,maxOccupancy,price);
    }
}
