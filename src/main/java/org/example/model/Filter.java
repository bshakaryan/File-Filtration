package org.example.model;

public class Filter {
    private String path = "";
    private String prefix = "";
    private boolean rewrite = true;
    private Statistics statistics = Statistics.NONE;

    public enum Statistics {
        SHORT,
        FULL,
        NONE
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setRewrite(boolean rewrite) {
        this.rewrite = rewrite;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public String getPath() {
        return path;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isRewrite() {
        return rewrite;
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
