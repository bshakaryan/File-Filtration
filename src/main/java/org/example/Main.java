package org.example;

import org.example.service.FilterService;
import org.example.service.InputService;
import org.example.service.OutputService;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out))) {

            InputService inputService = new InputService(bufferedReader, bufferedWriter);
            inputService.read();

            FilterService filterService = new FilterService(inputService.getFiles());
            filterService.filter();

            OutputService outputService = new OutputService(inputService.getFilter(), bufferedWriter);
            outputService.writeIntegers(filterService.getLongList());
            outputService.writeFloats(filterService.getDoubleList());
            outputService.writeStrings(filterService.getStringList());

        } catch (IOException e) {
            System.out.println("Ошибка на сервере");
        }
    }
}