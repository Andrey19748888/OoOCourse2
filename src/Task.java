import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Task implements Frequency {


    private static int counter = 1;

    private String name;
    private String description;
    private Type type;
    private LocalDateTime dateTime;
    private int id;

    public Task(String name, String description, Type type, LocalDateTime dateTime) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.dateTime = dateTime;

        id = counter;
        counter++;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    @Override
    public abstract LocalDateTime nextDateTime();

}
