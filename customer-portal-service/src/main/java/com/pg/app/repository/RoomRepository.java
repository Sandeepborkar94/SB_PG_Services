package com.pg.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pg.app.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long>
{
    List<Room> findByBookedFalse();
}