package pojo;

public class Page {
    public int start = 0;
    public int pre;
    public int next;
    public int first = 0;
    public int last;
    public int NumPerPage = 18;

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
        this.start = start;
        this.pre = (start - this.NumPerPage) < 0 ? 0 : (start - this.NumPerPage);
        this.next = (this.start + this.NumPerPage > totalBlogs - 1) ? start : this.start + this.NumPerPage;
        this.last = (totalBlogs % this.NumPerPage == 0) ? (totalBlogs - this.NumPerPage) :(totalBlogs - (totalBlogs % this.NumPerPage));
    }
}
