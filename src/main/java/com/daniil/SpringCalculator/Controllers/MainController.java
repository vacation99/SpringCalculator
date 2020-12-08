package com.daniil.SpringCalculator.Controllers;

import com.daniil.SpringCalculator.Models.History;
import com.daniil.SpringCalculator.Repo.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {
    @Autowired
    HistoryRepo historyRepo;

    private double result;
    private Date date = new Date();

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/main")
    public String main(Model model) {

        model.addAttribute("model", result);
        Iterable<History> histories = historyRepo.findAll();
        model.addAttribute("history", histories);

        return "main";
    }

    @PostMapping("/main")
    public String result(@RequestParam String firstNumber, @RequestParam String secondNumber, @RequestParam String action) {

        int firstNumInt = Integer.parseInt(firstNumber);
        int secondNumInt = Integer.parseInt(secondNumber);
        double num1 = Double.parseDouble(firstNumber), num2 = Double.parseDouble(secondNumber);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = simpleDateFormat.format(date);

        History history;

        switch (action) {
            case "Сложение":
                result = 0;
                result = firstNumInt + secondNumInt;
                history = new History(currentDate, result, firstNumber + " + " + secondNumber);
                historyRepo.save(history);
                break;
            case "Вычитание":
                result = 0;
                result = firstNumInt - secondNumInt;
                history = new History(currentDate, result, firstNumber + " - " + secondNumber);
                historyRepo.save(history);
                break;
            case "Умножение":
                result = 0;
                result = firstNumInt * secondNumInt;
                history = new History(currentDate, result, firstNumber + " * " + secondNumber);
                historyRepo.save(history);
                break;
            case "Деление":
                result = 0;
                result = firstNumInt / (double)secondNumInt;
                history = new History(currentDate, result, firstNumber + " / " + secondNumber);
                historyRepo.save(history);
                break;
            case "Возведение в степень":
                result = 0;
                result = Math.pow(num1, num2);
                history = new History(currentDate, result, firstNumber + "^" + secondNumber);
                historyRepo.save(history);
                break;
            case "Извлечение из корня":
                result = 0;
                result = Math.sqrt(num1);
                history = new History(currentDate, result, firstNumber);
                historyRepo.save(history);
                break;
        }

        return "redirect:main";
    }

    @GetMapping("/clearHistory")
    public String cleatHistory() {
        historyRepo.deleteAll();

        return "redirect:main";
    }
}
