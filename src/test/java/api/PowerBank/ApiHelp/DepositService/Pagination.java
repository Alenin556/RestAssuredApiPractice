package api.PowerBank.ApiHelp.DepositService;

public class Pagination {
    public int currentPage;
    public int lastPage;
    public int perPage;
    public int total;

    public Pagination(){
    }

    public Pagination(int currentPage, int lastPage, int perPage, int total) {
        this.currentPage = currentPage;
        this.lastPage = lastPage;
        this.perPage = perPage;
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getTotal() {
        return total;
    }
}
