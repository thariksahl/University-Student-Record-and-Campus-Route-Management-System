public class ServiceRequest {
    private final String requestId;
    private final String studentId;
    private final String description;

    public ServiceRequest(
            String requestId,
            String studentId,
            String description
    ) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.description = description;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Request ID: " + requestId
                + " | Student ID: " + studentId
                + " | Description: " + description;
    }
}

