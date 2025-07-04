package com.pg.app.dto;

import lombok.Data;

@Data
public class RoomBookingRequest 
{
	private Long roomId;
	private String customerName;
}