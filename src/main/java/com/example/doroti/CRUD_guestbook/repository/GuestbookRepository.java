package com.example.doroti.CRUD_guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.doroti.CRUD_guestbook.entity.Guestbook;

public interface GuestbookRepository extends JpaRepository<Guestbook,Long> {

}
