package pl.java.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller // thx temu moge przejśc do tworzenia kontrolerów
public class ProductController {
    List<String>databases= new ArrayList<>();
           ProductController(){
            databases.add( "iPhone11");
            databases.add( "iPhone12");
            databases.add( "iPhone13");
            databases.add( "iPhone14");
    }
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
    @GetMapping("/saveProduct")
    public String saveProduct(@RequestParam("productName") String productName, Model model){
        // odbieranie danych
//    public String saveProduct(@RequestParam String productName){ // odbieranie danych mozna uzunąc o ile nazwa jest taka sama
        if(!databases.contains(productName)) {
            databases.add(productName);
            model.addAttribute("db", databases);
        }
        return "redirect:/";
    }
    @GetMapping("/removeProduct")
    public String removeProduct(@RequestParam("productName") String productName){
        databases.removeIf(s -> s.equals(productName));
        return "redirect:/";
    }
}
