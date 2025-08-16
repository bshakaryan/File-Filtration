package org.example.service;

import org.example.model.Filter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class OutputService {
    private final Filter filter;
    private final BufferedWriter bufferedWriter;

    public OutputService(Filter filter, BufferedWriter bufferedWriter) {
        this.filter = filter;
        this.bufferedWriter = bufferedWriter;
    }

    public void writeIntegers(List<Long> longList) throws IOException {
        if (!longList.isEmpty()) {
            int total = longList.size();
            long mx = Long.MIN_VALUE;
            long mn = Long.MAX_VALUE;
            long sum = 0;
            double avg = 0.0;

            Path dir = filter.getPath().isEmpty() ? Path.of("") : Path.of(filter.getPath());
            Path fullPath = dir.resolve(filter.getPrefix() + "integers.txt");

            try (BufferedWriter bufferedInt = Files.newBufferedWriter(fullPath, StandardOpenOption.CREATE, (filter.isRewrite() ? StandardOpenOption.TRUNCATE_EXISTING : StandardOpenOption.APPEND))) {
                for (var it : longList) {
                    bufferedInt.write(it.toString());
                    bufferedInt.newLine();
                    mx = Math.max(mx, it);
                    mn = Math.min(mn, it);
                    sum += it;
                }
                avg = (double) sum / longList.size();
            }
            if (filter.getStatistics().equals(Filter.Statistics.SHORT)) {
                bufferedWriter.write(fullPath + "\n"
                        + "Добавлено " + total + " значений" + "\n");
            }
            if (filter.getStatistics().equals(Filter.Statistics.FULL)) {
                bufferedWriter.write(fullPath + "\n"
                        + "Добавлено " + total + " значений" + "\n"
                        + "Максимальное значение: " + mx + "\n"
                        + "Минимальное значение: " + mn + "\n"
                        + "Сумма всех значений: " + sum + "\n"
                        + "Среднее всех значений: " + avg + "\n");
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }

    public void writeFloats(List<Double> doubleList) throws IOException {
        if (!doubleList.isEmpty()) {
            int total = doubleList.size();
            double mx = Double.MIN_VALUE;
            double mn = Double.MAX_VALUE;
            double sum = 0.0;
            double avg = 0.0;

            Path dir = filter.getPath().isEmpty() ? Path.of("") : Path.of(filter.getPath());
            Path fullPath = dir.resolve(filter.getPrefix() + "floats.txt");

            try (BufferedWriter bufferedDouble = Files.newBufferedWriter(fullPath, StandardOpenOption.CREATE, (filter.isRewrite() ? StandardOpenOption.TRUNCATE_EXISTING : StandardOpenOption.APPEND))) {
                for (var it : doubleList) {
                    bufferedDouble.write(it.toString());
                    bufferedDouble.newLine();
                    mx = Math.max(mx, it);
                    mn = Math.min(mn, it);
                    sum += it;
                }
                avg = sum / doubleList.size();
            }
            if (filter.getStatistics().equals(Filter.Statistics.SHORT)) {
                bufferedWriter.write(fullPath + "\n"
                            + "Добавлено " + total + " значений" + "\n");
            }
            if (filter.getStatistics().equals(Filter.Statistics.FULL)) {
                bufferedWriter.write(fullPath + "\n"
                            + "Добавлено " + total + " значений" + "\n"
                            + "Максимальное значение: " + mx + "\n"
                            + "Минимальное значение: " + mn + "\n"
                            + "Сумма всех значений: " + sum + "\n"
                            + "Среднее всех значений: " + avg + "\n");
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }

    public void writeStrings(List<String> stringList) throws IOException {
        if (!stringList.isEmpty()) {
            int total = stringList.size();
            int mx = Integer.MIN_VALUE;
            int mn = Integer.MAX_VALUE;

            Path dir = filter.getPath().isEmpty() ? Path.of("") : Path.of(filter.getPath());
            Path fullPath = dir.resolve(filter.getPrefix() + "strings.txt");

            try (BufferedWriter bufferedString = Files.newBufferedWriter(fullPath, StandardOpenOption.CREATE, (filter.isRewrite() ? StandardOpenOption.TRUNCATE_EXISTING : StandardOpenOption.APPEND))) {
                for (var it : stringList) {
                    bufferedString.write(it);
                    bufferedString.newLine();
                    mx = Math.max(mx, it.length());
                    mn = Math.min(mn, it.length());
                }
            }
            if (filter.getStatistics().equals(Filter.Statistics.SHORT)) {
                bufferedWriter.write(fullPath + "\n"
                        + "Добавлено " + total + " значений" + "\n");
            }
            if (filter.getStatistics().equals(Filter.Statistics.FULL)) {
                bufferedWriter.write(fullPath + "\n"
                        + "Добавлено " + total + " значений" + "\n"
                        + "Длина самой короткой строки: " + mn + "\n"
                        + "Длина самой длинной строки: " + mx + "\n");
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }
}
