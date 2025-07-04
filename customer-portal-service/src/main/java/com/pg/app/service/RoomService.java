package com.pg.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pg.app.dto.RoomBookingRequest;
import com.pg.app.entity.Room;
import com.pg.app.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService 
{
    private final RoomRepository roomRepository;

    public List<Room> getAvailableRooms() 
    {
        return roomRepository.findByBookedFalse();
    }

    public String bookRoom(RoomBookingRequest request) {
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (room.isBooked()) {
            return "Room already booked";
        }

        room.setBooked(true);
        roomRepository.save(room);

        return "Room booked successfully for " + request.getCustomerName();
    }
}