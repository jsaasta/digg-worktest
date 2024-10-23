package com.jsaasta.user;

import java.util.Set;

public class PaginatedUserResult {

    public Set<User> users;
    public int pages;
    public int currentPage;

    public PaginatedUserResult() {
    }

    public PaginatedUserResult(Set<User> users, int pages, int currentPage) {
        this.users = users;
        this.pages = pages;
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PaginatedUserResult{" +
                "users=" + users +
                ", pages=" + pages +
                ", currentPage=" + currentPage +
                '}';
    }
}
