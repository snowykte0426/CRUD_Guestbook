package com.example.doroti.CRUD_guestbook.controller;

import com.example.doroti.CRUD_guestbook.dto.GuestbookDTO;
import com.example.doroti.CRUD_guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService guestbookService;

    @GetMapping("/")
    public String index() {
        log.info("GET request : /guestbook/");
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list() {
        log.info("GET request : /guestbook/list");
    }

    @GetMapping("/register")
    public void register() {
        log.info("GET request : /guestbook/register");
    }

    @PostMapping("/register")
    public String register(GuestbookDTO guestbookDTO, RedirectAttributes redirectAttributes) {
        log.info("POST request : /guestbook/register");
        Long registeredGno = guestbookService.register(guestbookDTO);
        log.info("registered gno : " + registeredGno);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/guestbook/list";
    }
}
