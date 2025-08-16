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
/*
[-o /some/path] [-p result_] [-a] [-s/-f] file1.txt file2.txt

-p test6- -a -f C:\Users\асер\Desktop\koronatech_test\in1.txt C:\Users\асер\Desktop\koronatech_test\in2.txt C:\Users\асер\Desktop\koronatech_test\in3.txt

java -jar util.jar -a -f C:\Users\асер\Desktop\koronatech_test\in1.txt C:\Users\асер\Desktop\koronatech_test\in2.txt C:\Users\асер\Desktop\koronatech_test\in3.txt

java -jar util.jar -s -a -p sample- in1.txt in2.txt

-o output/ -s -a -p sample- C:\Users\асер\Desktop\koronatech_test\in1.txt C:\Users\асер\Desktop\koronatech_test\in2.txt C:\Users\асер\Desktop\koronatech_test\in3.txt

 По умолчанию будет складывать в проект, относительные пути относительно папки проекта, должны существовать
 Работает на абсолютные пути

 java -jar util.jar

 Java 12+

 */