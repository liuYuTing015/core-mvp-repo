package com.core.app.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    List<Result> results = new ArrayList<>();
    Info info = new Info();

    public User(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleName() {
        return "dagger name diudiu11";
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String titleName;

    public class Info {

        public int getResults() {
            return results;
        }

        public void setResults(int results) {
            this.results = results;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getSeed() {
            return seed;
        }

        public void setSeed(String seed) {
            this.seed = seed;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int results;
        public int page;
        public String seed;
        public String version;

    }
}


