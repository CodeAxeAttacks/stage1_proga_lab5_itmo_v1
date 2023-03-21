public class MinByMinimalPointCommand implements Command {
    private final LabWorkCollection collection;

    public MinByMinimalPointCommand(LabWorkCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute() {
        // Find the LabWork object with the minimal minimalPoint value
        LabWork minLabWork = null;
        float minPoint = Integer.MAX_VALUE;
        for (LabWork labWork : collection.getAll()) {
            if (labWork.getMinimalPoint() < minPoint) {
                minPoint = labWork.getMinimalPoint();
                minLabWork = labWork;
            }
        }

        // Print the LabWork object to standard output
        if (minLabWork != null) {
            System.out.println("LabWork with minimal minimalPoint:");
            System.out.println("ID: " + minLabWork.getId());
            System.out.println("Name: " + minLabWork.getName());
            System.out.println("Coordinates: (" + minLabWork.getCoordinates().getX() + ", " + minLabWork.getCoordinates().getY() + ")");
            System.out.println("Creation Date: " + minLabWork.getCreationDate());
            System.out.println("Minimal Point: " + minLabWork.getMinimalPoint());
            System.out.println("Difficulty: " + minLabWork.getDifficulty());
            System.out.println("Discipline: " + minLabWork.getDiscipline().getName() + " (self-study hours: " + minLabWork.getDiscipline().getSelfStudyHours() + ")");
        } else {
            System.out.println("Collection is empty.");
        }
    }

    @Override
    public String getDescription() {
        return "min_by_minimal_point";
    }
}
