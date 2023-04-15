package pl.java.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller // thx temu moge przejśc do tworzenia kontrolerów
public class ProductController {
    List<String>databases=List.of(
            "iPhone11",
            "iPhone12",
            "iPhone13",
            "iPhone14"
    );
    @GetMapping ("/")// MA ODWOŁANIE DO GŁOWNEJ STRONY to jest nasz root
    public String ShowPrducts(Model model){
        // ten model to te bebechy springa 
        // jaką metodą będziemy odpytywać
        // skoro klientem jest przeglodarka więc pyta przez get => cały czas

        //jak chcemy odpowiedziec na zadane pytanie
        // te pliku które zwracamy trzeba je dawać w templets
        
        // wskrzykiwanie danych przez model
        model.addAttribute("title","Our products");
        model.addAttribute("db",databases);
        return "Products";



    }
}
