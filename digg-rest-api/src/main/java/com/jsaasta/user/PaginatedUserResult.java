package com.jsaasta.user;

import java.util.Set;

public class PaginatedUserResult {

    private Set<User> users;
    private int pages;
    private int currentPage;

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
