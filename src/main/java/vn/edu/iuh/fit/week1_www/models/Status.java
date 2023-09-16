package vn.edu.iuh.fit.week1_www.models;

public enum Status {
    active(1),
    deactive(0),
    delete(-1);
    private final int value;

    private Status(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Status receiveStatus(int value){
        for (Status status : Status.values()){
            if (status.getValue() == value)
                return status;
        }
        throw new IllegalArgumentException("Invalid status: "+ value);
    }
}
