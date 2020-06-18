package com.api.cleverit.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cleverit.app.entity.Auto;

public interface ICarro extends JpaRepository<Auto, Integer>{

}
