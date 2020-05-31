public abstract class Movie implements Comparable<Movie> {
    //Must declare first six fields
    private int id;
    private String name;
    private Time startTime;
    private int runtime;
    private double price;

    public int getTicketsLeft() {
        return ticketsLeft;
    }

    protected int ticketsLeft;

    public double getSaled_money() {
        return saled_money;
    }

    protected double saled_money = 0;
    //You can add other fields that you think are necessary.

    //Start time of movies in all test cases
    // are between 00:00 and 24:00, viz.
    // all operations take place in one day only.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public double getPrice() {
        return price;
    }

    public Movie(int id, String name, Time startTime, int runtime, double price, int ticketsLeft) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.runtime = runtime;
        this.price = price;
        this.ticketsLeft = ticketsLeft;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract double purchase(int arg);

    @Override
    public String toString() {
        return
                "id=" + id + ", name='" + name +
                        "', startTime:" + startTime +
                        ", runtime=" + runtime +
                        ", price=" + price +
                        ", ticketsLeft=" + ticketsLeft;
    }

    @Override
    public int compareTo(Movie o) {
        return this.getStartTime().compareTo(o.getStartTime());
    }
}
