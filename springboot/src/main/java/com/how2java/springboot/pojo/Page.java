package com.how2java.springboot.pojo;

public class Page {
    public int start = 0;
    public int pre;
    public int next;
    public int first = 0;
    public int last;
    public int NumPerPage;

    public Page(int NumPerPage) {
        this.NumPerPage = NumPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.NumPerPage = numPerPage;
    }

    public int getNumPerPage() {
        return NumPerPage;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    public int getNext() {
        return next;
    }

    public int getPre() {
        return pre;
    }

    public void update(int start, int totalBlogs) {
        if(totalBlogs == 0) {
            this.start = 0;
            this.pre = 0;
            this.next = 0;
            this.last = 0;
            return;
        }
        this.start = start;
        this.pre = Math.max((start - this.NumPerPage), 0);
        this.next = (this.start + this.NumPerPage > totalBlogs - 1) ? start : this.start + this.NumPerPage;
        this.last = (totalBlogs % this.NumPerPage == 0) ? (totalBlogs - this.NumPerPage) :(totalBlogs - (totalBlogs % this.NumPerPage));
    }
}
