package org.example.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FilterService {
    private final List<String> files;
    private final List <Long> longList = new ArrayList<>();
    private final List <String> stringList = new ArrayList<>();
    private final List <Double> doubleList = new ArrayList<>();

    public FilterService(List<String> files) {
        this.files = files;
    }

    public void filter() throws IOException {
        BufferedReader[]bufferedReaders = new BufferedReader[files.size()];
        for (int i = 0; i < files.size(); i++) {
            bufferedReaders[i] = Files.newBufferedReader(Path.of(files.get(i)));
        }
        while (true) {
            boolean check = true;
            for (int i = 0; i < files.size(); i++) {
                if (bufferedReaders[i].ready()) {
                    check = false;
                    String res = bufferedReaders[i].readLine();
                    try {
                        Long x = Long.parseLong(res);
                        longList.add(x);
                        continue;
                    } catch (NumberFormatException e) {}
                    try {
                        Double x = Double.parseDouble(res);
                        doubleList.add(x);
                        continue;
                    } catch (NumberFormatException e) {}
                    stringList.add(res);
                }
            }
            if (check) {
                break;
            }
        }
        for (int i = 0; i < files.size(); i++) {
            bufferedReaders[i].close();
        }
    }

    public List<Long> getLongList() {
        return longList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public List<Double> getDoubleList() {
        return doubleList;
    }
}
