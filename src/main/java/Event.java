public class Event extends Task {

    //The start time of the Event
    protected String startTime;

    /**
     * The constructor for the Event class
     * @param description The description of the event
     * @param startTime The start time of the event
     */
    public Event(String description, String startTime) {
        super(description);
        this.startTime = startTime;
    }

    /**
     * Overridden toString method for the Event class
     * @return String representation of the Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + startTime + ")";
    }
}
