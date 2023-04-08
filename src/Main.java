import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner, service);
                            service.printMap();
                            break;
                        case 2:
                            // todo: обрабатываем пункт меню 2
                            System.out.println(" Какую задачу вы хотите удалить? Ведите id.");
                            int id;
                            id = scanner.nextInt();
                            service.delete(id);
                            service.printMap();
                            break;
                        case 3:
                            service.printMap();

//                            getTodayTasks();
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }


    private static void getTodayTasks(Service service, LocalDate date) {

        Task task;

        for (int i = 1; i <= service.getMapSize(); i++) {
            task = service.getTaskById(i);

            if (task.getDateTime().toLocalDate().equals(date)) {
                System.out.println(task.getName());
            }

        }
    }

    private static void inputTask(Scanner scanner, Service service) {
        System.out.println("Введите название задачи: ");
        String taskName = scanner.nextLine();
        System.out.println("Введите описание задачи: ");
        String description = scanner.nextLine();
        System.out.println("Задача личная (1) или рабочая (2): ");
        String taskType = scanner.nextLine();
        System.out.println("Задача одноразовая (1), еженедельная (2), ежемесячная (3) или ежегодная (4)?: ");
        String taskFrequency = scanner.nextLine();

        System.out.println("Когда нужно выполнить задачу в первый раз? Введите в формате: YYYY-MM-ddThh:mm:ss");
        String dateTime = scanner.nextLine();

        Type type = null;

        if (taskType.equals(1)) {
            type = Type.PERSONAL;
        } else if ( taskType.equals(2) ){
            type = Type.WORK;
        }


        Task task = null;

        switch (taskFrequency) {
            case "1":       // задача одноразовая
                task = new SingleTask(taskName,description,type,LocalDateTime.parse(dateTime));
                break;
            case "2":       // задача еженедельная
                task = new WeeklyTask(taskName,description,type,LocalDateTime.parse(dateTime));
                break;
            case "3":
                task = new MonthlyTask(taskName,description,type,LocalDateTime.parse(dateTime));
                break;
            case "4":
                task = new YearlyTask(taskName,description,type,LocalDateTime.parse(dateTime));
                break;
        }

        service.add(task);  // [1,task1; 2,task2; 3,task3]
    }



    private static void printMenu() {
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Получить задачу на указанный день");
        System.out.println("0. Выход");
    }
}