package org.example.service;

import org.example.model.Filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputService {
    private final static String ILLEGAL = "[\\\\/:*?\"<>|]";
    private final Filter filter = new Filter();
    private final List<String> files = new ArrayList<>();
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;

    public InputService(BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        this.bufferedReader = bufferedReader;
        this.bufferedWriter = bufferedWriter;
    }

    public void read() throws IOException {
        bufferedWriter.write("Введите строку формата: \n [-o /some/path] [-p result_] [-a] [-s/-f] file1.txt file2.txt\n");
        bufferedWriter.flush();

        String []parameters = bufferedReader.readLine().split(" ");
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isEmpty()) {
                continue;
            }
            if (parameters[i].equals("-o") && i + 1 < parameters.length) {
                Path path = Path.of(parameters[i + 1]);
                if (!path.isAbsolute()) {
                    path = Path.of(System.getProperty("user.dir"), parameters[i + 1]);
                }
                if (!Files.isDirectory(path)) {
                    throw new IOException("Указанный путь не существует или не является директорией");
                }
                filter.setPath(parameters[i + 1]);
                i++;
            }
            else if (parameters[i].equals("-p") && i + 1 < parameters.length) {
                if (parameters[i + 1].matches(".*" + ILLEGAL + ".*")) {
                    throw new IOException("Префикс содержит недопустимые символы \\ / : * ? \" < > |");
                }
                filter.setPrefix(parameters[i + 1]);
                i++;
            }
            else if (parameters[i].equals("-a")) {
                filter.setRewrite(false);
            }
            else if (parameters[i].equals("-s")) {
                filter.setStatistics(Filter.Statistics.SHORT);
            }
            else if (parameters[i].equals("-f")) {
                filter.setStatistics(Filter.Statistics.FULL);
            }
            else if (parameters[i].endsWith(".txt")) {
                files.add(parameters[i]);
            }
            else {
                throw new IOException("Неверный ввод");
            }
        }
    }

    public Filter getFilter() {
        return filter;
    }

    public List<String> getFiles() {
        return files;
    }
}
