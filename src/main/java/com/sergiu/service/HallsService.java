package com.sergiu.service;

import com.sergiu.dto.HallDTO;

import java.util.List;

public interface HallsService {
    List<HallDTO> getAllHalls();

    void createHall(HallDTO hallDTO);

    HallDTO getHallById(Integer id);

    HallDTO updateHall(Integer id, HallDTO hallDTO);

    void deleteHall(Integer id);
}
